use aes::{Aes128, Aes192, Aes256, NewCipher};
use block_cipher::BlockCipher;

pub fn encrypt_bytes_aes(data: &[u8], key: &[u8]) -> Result<Vec<u8>, &'static str> {
    match key.len() {
        16 => {
            let cipher = Aes128::new_from_slice(key).map_err(|_| "Invalid key size for AES-128")?;
            encrypt_aes(data, cipher)
        }
        24 => {
            let cipher = Aes192::new_from_slice(key).map_err(|_| "Invalid key size for AES-192")?;
            encrypt_aes(data, cipher)
        }
        32 => {
            let cipher = Aes256::new_from_slice(key).map_err(|_| "Invalid key size for AES-256")?;
            encrypt_aes(data, cipher)
        }
        _ => Err("Invalid key size. Supported sizes are 16, 24, and 32 bytes."),
    }
}

fn encrypt_aes<C: NewCipher + BlockCipher>(data: &[u8], cipher: C) -> Result<Vec<u8>, &'static str> {
    let mut encrypted_data = vec![0; data.len()];
    let block_size = cipher.block_size();

    for i in (0..data.len()).step_by(block_size) {
        let block = &data[i..std::cmp::min(i + block_size, data.len())];
        let mut block = block.to_vec();
        block.resize(block_size, 0);
        cipher.encrypt_block(&mut block);
        encrypted_data[i..i + block_size].copy_from_slice(&block);
    }

    Ok(encrypted_data)
}

pub fn decrypt_bytes_aes(data: &[u8], key: &[u8]) -> Result<Vec<u8>, &'static str> {
    match key.len() {
        16 => {
            let cipher = Aes128::new_from_slice(key).map_err(|_| "Invalid key size for AES-128")?;
            decrypt_aes(data, cipher)
        }
        24 => {
            let cipher = Aes192::new_from_slice(key).map_err(|_| "Invalid key size for AES-192")?;
            decrypt_aes(data, cipher)
        }
        32 => {
            let cipher = Aes256::new_from_slice(key).map_err(|_| "Invalid key size for AES-256")?;
            decrypt_aes(data, cipher)
        }
        _ => Err("Invalid key size. Supported sizes are 16, 24, and 32 bytes."),
    }
}

fn decrypt_aes<C: NewCipher + BlockCipher>(data: &[u8], cipher: C) -> Result<Vec<u8>, &'static str> {
    let mut decrypted_data = vec![0; data.len()];
    let block_size = cipher.block_size();

    for i in (0..data.len()).step_by(block_size) {
        let block = &data[i..std::cmp::min(i + block_size, data.len())];
        let mut block = block.to_vec();
        cipher.decrypt_block(&mut block);
        decrypted_data[i..i + block_size].copy_from_slice(&block);
    }

    // Remove trailing zeros
    while let Some(&0) = decrypted_data.last() {
        decrypted_data.pop();
    }

    Ok(decrypted_data)
}

fn main() {
    let key = [0; 32]; // Example 256-bit key
    let data = [1; 16]; // Example 128-bit data

    match encrypt_bytes_aes(&data, &key) {
        Ok(encrypted) => println!("Encrypted: {:?}", encrypted),
        Err(e) => println!("Encryption error: {}", e),
    }

    match decrypt_bytes_aes(&encrypt_bytes_aes(&data, &key).unwrap(), &key) {
        Ok(decrypted) => println!("Decrypted: {:?}", decrypted),
        Err(e) => println!("Decryption error: {}", e),
    }
}