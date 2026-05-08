use aes::cipher::{
    generic_array::GenericArray,
    BlockDecrypt, BlockEncrypt, KeyInit,
};
use aes::{Aes128, Aes192, Aes256};

pub fn encrypt_bytes_aes(data: &[u8], key: &[u8]) -> Vec<u8> {
    let mut result = data.to_vec();
    match key.len() {
        16 => {
            let cipher = Aes128::new(GenericArray::from_slice(key));
            for chunk in result.chunks_exact_mut(16) {
                cipher.encrypt_block(GenericArray::from_mut_slice(chunk));
            }
        }
        24 => {
            let cipher = Aes192::new(GenericArray::from_slice(key));
            for chunk in result.chunks_exact_mut(16) {
                cipher.encrypt_block(GenericArray::from_mut_slice(chunk));
            }
        }
        32 => {
            let cipher = Aes256::new(GenericArray::from_slice(key));
            for chunk in result.chunks_exact_mut(16) {
                cipher.encrypt_block(GenericArray::from_mut_slice(chunk));
            }
        }
        _ => panic!("Invalid AES key size: {} bytes (must be 16, 24, or 32)", key.len()),
    }
    result
}

pub fn decrypt_bytes_aes(data: &[u8], key: &[u8]) -> Vec<u8> {
    let mut result = data.to_vec();
    match key.len() {
        16 => {
            let cipher = Aes128::new(GenericArray::from_slice(key));
            for chunk in result.chunks_exact_mut(16) {
                cipher.decrypt_block(GenericArray::from_mut_slice(chunk));
            }
        }
        24 => {
            let cipher = Aes192::new(GenericArray::from_slice(key));
            for chunk in result.chunks_exact_mut(16) {
                cipher.decrypt_block(GenericArray::from_mut_slice(chunk));
            }
        }
        32 => {
            let cipher = Aes256::new(GenericArray::from_slice(key));
            for chunk in result.chunks_exact_mut(16) {
                cipher.decrypt_block(GenericArray::from_mut_slice(chunk));
            }
        }
        _ => panic!("Invalid AES key size: {} bytes (must be 16, 24, or 32)", key.len()),
    }
    result
}