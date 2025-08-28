use base64::{engine::general_purpose, Engine as _};
use openssl::symm::{Cipher, Crypter, Mode};
use rand::RngCore;
use std::error::Error;

const BLOCK_SIZE: usize = 16;          // AES block size (bytes)

/* ---------- PKCS#7 helpers ---------- */

fn pad(data: &[u8]) -> Vec<u8> {
    let pad_len = BLOCK_SIZE - (data.len() % BLOCK_SIZE);
    let mut out = Vec::with_capacity(data.len() + pad_len);
    out.extend_from_slice(data);
    out.extend(std::iter::repeat(pad_len as u8).take(pad_len));
    out
}

fn unpad(data: &[u8]) -> Result<Vec<u8>, &'static str> {
    if data.is_empty() {
        return Err("input is empty");
    }
    let pad_len = *data.last().unwrap() as usize;
    if pad_len == 0 || pad_len > BLOCK_SIZE || pad_len > data.len() {
        return Err("invalid padding");
    }
    for &b in &data[data.len() - pad_len..] {
        if b as usize != pad_len {
            return Err("invalid padding");
        }
    }
    Ok(data[..data.len() - pad_len].to_vec())
}

/* ---------- API identical to Python version ---------- */

/// Encrypt `plaintext` (bytes) with the given key and AES-CBC.
/// `key_size` must be 128, 192 or 256.
pub fn encrypt(
    key: &[u8],
    key_size: usize,
    plaintext: &[u8],
) -> Result<String, Box<dyn Error>> {
    // Select the cipher that matches the desired key size
    let cipher = match key_size {
        128 => Cipher::aes_128_cbc(),
        192 => Cipher::aes_192_cbc(),
        256 => Cipher::aes_256_cbc(),
        _ => return Err("unsupported key size".into()),
    };

    // Secure random IV
    let mut iv = [0u8; BLOCK_SIZE];
    rand::thread_rng().fill_bytes(&mut iv);

    // PKCS#7 padding (Crypter is told *not* to pad)
    let padded = pad(plaintext);

    let mut crypter = Crypter::new(cipher, Mode::Encrypt, key, Some(&iv))?;
    crypter.pad(false);

    // ciphertext = AES_CBC(iv, padded)
    let mut cipher_buf = vec![0u8; padded.len() + cipher.block_size()];
    let mut written = crypter.update(&padded, &mut cipher_buf)?;
    written += crypter.finalize(&mut cipher_buf[written..])?;
    cipher_buf.truncate(written);

    // Concatenate iv || ciphertext and Base-64 encode, just like Python
    let mut iv_and_cipher = iv.to_vec();
    iv_and_cipher.extend_from_slice(&cipher_buf);
    Ok(general_purpose::STANDARD.encode(iv_and_cipher))
}

/// Decrypt Base-64 ciphertext produced by `encrypt`.
pub fn decrypt(
    key: &[u8],
    key_size: usize,
    b64_ciphertext: &str,
) -> Result<Vec<u8>, Box<dyn Error>> {
    let full = general_purpose::STANDARD.decode(b64_ciphertext)?;

    if full.len() < BLOCK_SIZE {
        return Err("ciphertext too short".into());
    }
    let (iv, ctext) = full.split_at(BLOCK_SIZE);

    let cipher = match key_size {
        128 => Cipher::aes_128_cbc(),
        192 => Cipher::aes_192_cbc(),
        256 => Cipher::aes_256_cbc(),
        _ => return Err("unsupported key size".into()),
    };

    let mut crypter = Crypter::new(cipher, Mode::Decrypt, key, Some(iv))?;
    crypter.pad(false);

    let mut out = vec![0u8; ctext.len() + cipher.block_size()];
    let mut written = crypter.update(ctext, &mut out)?;
    written += crypter.finalize(&mut out[written..])?;
    out.truncate(written);

    Ok(unpad(&out)?)
}

/* ---------- quick self-test ---------- */
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn roundtrip_works() {
        let key = [0x11u8; 32];                // dummy 256-bit key
        let msg = b"The quick brown fox jumps over the lazy dog";

        let enc = encrypt(&key, 256, msg).unwrap();
        let dec = decrypt(&key, 256, &enc).unwrap();
        assert_eq!(msg.to_vec(), dec);
    }
}