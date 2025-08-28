use aes::Aes128;
use aes::cipher::{BlockEncrypt, BlockDecrypt, KeyInit, generic_array::GenericArray};

fn encrypt_bytes_aes(data: &[u8], key: &[u8]) -> Vec<u8> {
    // Create cipher instance based on key length
    match key.len() {
        16 => encrypt_with_key::<aes::Aes128>(data, key),
        24 => encrypt_with_key::<aes::Aes192>(data, key),
        32 => encrypt_with_key::<aes::Aes256>(data, key),
        _ => panic!("Invalid key length. Must be 16, 24, or 32 bytes"),
    }
}

fn decrypt_bytes_aes(data: &[u8], key: &[u8]) -> Vec<u8> {
    // Create cipher instance based on key length
    match key.len() {
        16 => decrypt_with_key::<aes::Aes128>(data, key),
        24 => decrypt_with_key::<aes::Aes192>(data, key),
        32 => decrypt_with_key::<aes::Aes256>(data, key),
        _ => panic!("Invalid key length. Must be 16, 24, or 32 bytes"),
    }
}

fn encrypt_with_key<T>(data: &[u8], key: &[u8]) -> Vec<u8>
where
    T: KeyInit + BlockEncrypt,
{
    let cipher = T::new(GenericArray::from_slice(key));
    let mut result = Vec::new();
    
    // Process data in 16-byte blocks (AES block size)
    for chunk in data.chunks(16) {
        let mut block = [0u8; 16];
        block[..chunk.len()].copy_from_slice(chunk);
        
        let mut block_array = GenericArray::from(block);
        cipher.encrypt_block(&mut block_array);
        result.extend_from_slice(&block_array);
    }
    
    result
}

fn decrypt_with_key<T>(data: &[u8], key: &[u8]) -> Vec<u8>
where
    T: KeyInit + BlockDecrypt,
{
    let cipher = T::new(GenericArray::from_slice(key));
    let mut result = Vec::new();
    
    // Process data in 16-byte blocks (AES block size)
    for chunk in data.chunks(16) {
        let mut block = [0u8; 16];
        block[..chunk.len()].copy_from_slice(chunk);
        
        let mut block_array = GenericArray::from(block);
        cipher.decrypt_block(&mut block_array);
        result.extend_from_slice(&block_array);
    }
    
    result
}

// Add this to your Cargo.toml:
// [dependencies]
// aes = "0.8"