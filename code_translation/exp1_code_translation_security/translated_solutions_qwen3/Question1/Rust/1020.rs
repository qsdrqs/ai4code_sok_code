extern crate rand;
extern crate base64;
extern crate aes;
extern crate block_modes;
extern crate cipher;

use aes::{Aes128, Aes192, Aes256};
use block_modes::{BlockMode, Cbc};
use cipher::generic_array::GenericArray;
use rand::Rng;
use std::error::Error;

// PKCS#7 padding implementation
pub fn pad(data: &[u8]) -> Vec<u8> {
    let pad_len = 16 - (data.len() % 16);
    let mut padded = data.to_vec();
    padded.resize(data.len() + pad_len, pad_len as u8);
    padded
}

// PKCS#7 unpadding implementation
pub fn unpad(data: &[u8]) -> Result<Vec<u8>, Box<dyn Error>> {
    if data.is_empty() {
        return Ok(vec![]);
    }

    let pad_byte = data[data.len() - 1];
    let pad_len = pad_byte as usize;

    if pad_len == 0 || pad_len > 16 {
        return Err("Invalid padding length".into());
    }

    if data.len() < pad_len {
        return Err("Invalid padding length".into());
    }

    for i in (data.len() - pad_len)..data.len() {
        if data[i] != pad_byte {
            return Err("Invalid padding bytes".into());
        }
    }

    Ok(data[..data.len() - pad_len].to_vec())
}

// AES-CBC Encryption
pub fn encrypt(key: &[u8], _: usize, plaintext: &str) -> Result<String, Box<dyn Error>> {
    let mut iv = [0u8; 16];
    rand::thread_rng().fill(&mut iv);

    let padded = pad(plaintext.as_bytes());

    let encrypted = match key.len() {
        16 => {
            let key_arr = GenericArray::from_slice(key);
            let cipher = Aes128::new(&key_arr);
            let mut cbc = Cbc::new(cipher, &iv);
            let mut buf = padded;
            cbc.encrypt_block_slice(buf.as_mut_slice())?;
            buf
        }
        24 => {
            let key_arr = GenericArray::from_slice(key);
            let cipher = Aes192::new(&key_arr);
            let mut cbc = Cbc::new(cipher, &iv);
            let mut buf = padded;
            cbc.encrypt_block_slice(buf.as_mut_slice())?;
            buf
        }
        32 => {
            let key_arr = GenericArray::from_slice(key);
            let cipher = Aes256::new(&key_arr);
            let mut cbc = Cbc::new(cipher, &iv);
            let mut buf = padded;
            cbc.encrypt_block_slice(buf.as_mut_slice())?;
            buf
        }
        _ => return Err("Invalid key length".into()),
    };

    let mut combined = iv.to_vec();
    combined.extend_from_slice(&encrypted);

    Ok(base64::encode(&combined))
}

// AES-CBC Decryption
pub fn decrypt(key: &[u8], _: usize, ciphertext: &str) -> Result<String, Box<dyn Error>> {
    let data = base64::decode(ciphertext)?;
    if data.len() < 16 {
        return Err("Invalid ciphertext length".into());
    }

    let mut iv = [0u8; 16];
    iv.copy_from_slice(&data[..16]);
    let ciphertext_bytes = &data[16..];

    let padded_plaintext = match key.len() {
        16 => {
            let key_arr = GenericArray::from_slice(key);
            let cipher = Aes128::new(&key_arr);
            let mut cbc = Cbc::new(cipher, &iv);
            let mut buf = ciphertext_bytes.to_vec();
            cbc.decrypt_block_slice(buf.as_mut_slice())?;
            buf
        }
        24 => {
            let key_arr = GenericArray::from_slice(key);
            let cipher = Aes192::new(&key_arr);
            let mut cbc = Cbc::new(cipher, &iv);
            let mut buf = ciphertext_bytes.to_vec();
            cbc.decrypt_block_slice(buf.as_mut_slice())?;
            buf
        }
        32 => {
            let key_arr = GenericArray::from_slice(key);
            let cipher = Aes256::new(&key_arr);
            let mut cbc = Cbc::new(cipher, &iv);
            let mut buf = ciphertext_bytes.to_vec();
            cbc.decrypt_block_slice(buf.as_mut_slice())?;
            buf
        }
        _ => return Err("Invalid key length".into()),
    };

    let decrypted = unpad(&padded_plaintext)?;
    Ok(String::from_utf8(decrypted)?)
}