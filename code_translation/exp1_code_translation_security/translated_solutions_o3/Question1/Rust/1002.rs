use openssl::error::ErrorStack;
use openssl::symm::{Cipher, Crypter, Mode};
use rand::RngCore;

/// Encrypt `data` with AES-CBC using the supplied `key` and `iv`.
///
/// The function mirrors the Python implementation:  
/// • it performs *raw* AES-CBC (no automatic padding)  
/// • the key length selects AES-128/192/256 automatically
pub fn encrypt(data: &[u8], key: &[u8], iv: &[u8]) -> Result<Vec<u8>, ErrorStack> {
    let cipher = cipher_from_key_len(key.len())?;
    let mut crypter = Crypter::new(cipher, Mode::Encrypt, key, Some(iv))?;
    crypter.pad(false);                // Disable PKCS#7 padding – Python code had none

    // Allocate a little extra; `finalize` can add up to one full block
    let mut out = vec![0u8; data.len() + cipher.block_size()];
    let mut count = crypter.update(data, &mut out)?;
    count += crypter.finalize(&mut out[count..])?;
    out.truncate(count);
    Ok(out)
}

/// Decrypt the ciphertext produced by `encrypt`.
pub fn decrypt(data: &[u8], key: &[u8], iv: &[u8]) -> Result<Vec<u8>, ErrorStack> {
    let cipher = cipher_from_key_len(key.len())?;
    let mut crypter = Crypter::new(cipher, Mode::Decrypt, key, Some(iv))?;
    crypter.pad(false);

    let mut out = vec![0u8; data.len() + cipher.block_size()];
    let mut count = crypter.update(data, &mut out)?;
    count += crypter.finalize(&mut out[count..])?;
    out.truncate(count);
    Ok(out)
}

/// Choose the proper AES-CBC variant from the key length.
fn cipher_from_key_len(len: usize) -> Result<Cipher, ErrorStack> {
    match len {
        16 => Ok(Cipher::aes_128_cbc()),
        24 => Ok(Cipher::aes_192_cbc()),
        32 => Ok(Cipher::aes_256_cbc()),
        _  => Err(ErrorStack::get()),   // “invalid key length”
    }
}

/// Re-implementation of the Python `test` function.
fn test() -> Result<(), ErrorStack> {
    let data = b"a secret message";                     // 16 bytes (one AES block)

    // Generate 32-byte key and 16-byte IV (like `os.urandom`)
    let mut key = [0u8; 32];
    let mut iv  = [0u8; 16];
    rand::rngs::OsRng.fill_bytes(&mut key);
    rand::rngs::OsRng.fill_bytes(&mut iv);

    let ct = encrypt(data, &key, &iv)?;
    let pt = decrypt(&ct, &key, &iv)?;
    assert_eq!(pt, data);
    println!("Success: decrypted text matches the original.");
    Ok(())
}

fn main() {
    test().unwrap();
}