use aes::{Aes128, Aes192, Aes256};
use cipher::{BlockCipher, KeyInit};
use generic_array::GenericArray;
use typenum::{U16, U24, U32};

/// Encrypts the given data using AES in ECB mode.
///
/// # Arguments
///
/// * `data` - A slice of data to encrypt. Must be a multiple of 16 bytes.
/// * `key` - A slice of the encryption key. Must be 16, 24, or 32 bytes.
///
/// # Returns
///
/// A `Vec<u8>` containing the encrypted data.
pub fn encrypt_bytes_aes(data: &[u8], key: &[u8]) -> Vec<u8> {
    assert!(data.len() % 16 == 0, "Data length must be a multiple of 16 bytes");

    let key_len = key.len();
    let mut output = data.to_vec();
    let mut block = GenericArray::<u8, U16>::default();

    match key_len {
        16 => {
            let key_arr = GenericArray::<u8, U16>::from_slice(key);
            let cipher = Aes128::new(key_arr);
            for chunk in output.chunks_mut(16) {
                block.copy_from_slice(chunk);
                cipher.encrypt_block(&mut block);
                chunk.copy_from_slice(&block);
            }
        }
        24 => {
            let key_arr = GenericArray::<u8, U24>::from_slice(key);
            let cipher = Aes192::new(key_arr);
            for chunk in output.chunks_mut(16) {
                block.copy_from_slice(chunk);
                cipher.encrypt_block(&mut block);
                chunk.copy_from_slice(&block);
            }
        }
        32 => {
            let key_arr = GenericArray::<u8, U32>::from_slice(key);
            let cipher = Aes256::new(key_arr);
            for chunk in output.chunks_mut(16) {
                block.copy_from_slice(chunk);
                cipher.encrypt_block(&mut block);
                chunk.copy_from_slice(&block);
            }
        }
        _ => panic!("Invalid key length: {}", key_len),
    }

    output
}

/// Decrypts the given data using AES in ECB mode.
///
/// # Arguments
///
/// * `data` - A slice of data to decrypt. Must be a multiple of 16 bytes.
/// * `key` - A slice of the decryption key. Must be 16, 24, or 32 bytes.
///
/// # Returns
///
/// A `Vec<u8>` containing the decrypted data.
pub fn decrypt_bytes_aes(data: &[u8], key: &[u8]) -> Vec<u8> {
    assert!(data.len() % 16 == 0, "Data length must be a multiple of 16 bytes");

    let key_len = key.len();
    let mut output = data.to_vec();
    let mut block = GenericArray::<u8, U16>::default();

    match key_len {
        16 => {
            let key_arr = GenericArray::<u8, U16>::from_slice(key);
            let cipher = Aes128::new(key_arr);
            for chunk in output.chunks_mut(16) {
                block.copy_from_slice(chunk);
                cipher.decrypt_block(&mut block);
                chunk.copy_from_slice(&block);
            }
        }
        24 => {
            let key_arr = GenericArray::<u8, U24>::from_slice(key);
            let cipher = Aes192::new(key_arr);
            for chunk in output.chunks_mut(16) {
                block.copy_from_slice(chunk);
                cipher.decrypt_block(&mut block);
                chunk.copy_from_slice(&block);
            }
        }
        32 => {
            let key_arr = GenericArray::<u8, U32>::from_slice(key);
            let cipher = Aes256::new(key_arr);
            for chunk in output.chunks_mut(16) {
                block.copy_from_slice(chunk);
                cipher.decrypt_block(&mut block);
                chunk.copy_from_slice(&block);
            }
        }
        _ => panic!("Invalid key length: {}", key_len),
    }

    output
}