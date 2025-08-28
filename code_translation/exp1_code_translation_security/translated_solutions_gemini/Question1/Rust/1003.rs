// Import necessary components from the crates.
// `aes` provides the core AES block cipher.
// `block_modes` provides modes of operation, like ECB.
use aes::Aes128;
use aes::Aes192;
use aes::Aes256;
use block_modes::block_padding::Pkcs7;
use block_modes::{BlockMode, Ecb};

// Define type aliases for ECB mode with PKCS#7 padding for each key size.
// This makes the code cleaner and easier to read.
type Aes128Ecb = Ecb<Aes128, Pkcs7>;
type Aes192Ecb = Ecb<Aes192, Pkcs7>;
type Aes256Ecb = Ecb<Aes256, Pkcs7>;

/// Encrypts data using AES in ECB mode.
///
/// The key length determines the AES variant:
/// - 16 bytes for AES-128
/// - 24 bytes for AES-192
/// - 32 bytes for AES-256
///
/// # Arguments
/// * `data` - The data to encrypt.
/// * `key` - The encryption key.
///
/// # Returns
/// A `Result` containing the encrypted data as a `Vec<u8>` or an error string.
///
/// # Warning
/// ECB mode is not semantically secure and should not be used for most applications.
/// It is provided here for direct translation purposes.
pub fn encrypt_bytes_aes(data: &[u8], key: &[u8]) -> Result<Vec<u8>, String> {
    let ciphertext = match key.len() {
        16 => {
            let cipher = Aes128Ecb::new_from_slices(key, &[])
                .map_err(|e| e.to_string())?;
            cipher.encrypt_vec(data)
        }
        24 => {
            let cipher = Aes192Ecb::new_from_slices(key, &[])
                .map_err(|e| e.to_string())?;
            cipher.encrypt_vec(data)
        }
        32 => {
            let cipher = Aes256Ecb::new_from_slices(key, &[])
                .map_err(|e| e.to_string())?;
            cipher.encrypt_vec(data)
        }
        _ => return Err("Invalid key length. Must be 16, 24, or 32 bytes.".to_string()),
    };
    Ok(ciphertext)
}

/// Decrypts data using AES in ECB mode.
///
/// The key length determines the AES variant:
/// - 16 bytes for AES-128
/// - 24 bytes for AES-192
/// - 32 bytes for AES-256
///
/// # Arguments
/// * `data` - The encrypted data to decrypt.
/// * `key` - The decryption key.
///
/// # Returns
/// A `Result` containing the decrypted data as a `Vec<u8>` or an error string.
/// An error can occur if the key is invalid or the padding is incorrect.
pub fn decrypt_bytes_aes(data: &[u8], key: &[u8]) -> Result<Vec<u8>, String> {
    let decrypted_bytes = match key.len() {
        16 => {
            let cipher = Aes128Ecb::new_from_slices(key, &[])
                .map_err(|e| e.to_string())?;
            cipher.decrypt_vec(data).map_err(|e| e.to_string())?
        }
        24 => {
            let cipher = Aes192Ecb::new_from_slices(key, &[])
                .map_err(|e| e.to_string())?;
            cipher.decrypt_vec(data).map_err(|e| e.to_string())?
        }
        32 => {
            let cipher = Aes256Ecb::new_from_slices(key, &[])
                .map_err(|e| e.to_string())?;
            cipher.decrypt_vec(data).map_err(|e| e.to_string())?
        }
        _ => return Err("Invalid key length. Must be 16, 24, or 32 bytes.".to_string()),
    };
    Ok(decrypted_bytes)
}

// --- Example Usage ---
fn main() {
    // A 16-byte key for AES-128
    let key = b"my-secret-key123"; // 16 bytes
    let data_to_encrypt = b"This is a secret message.";

    println!("Original data: {:?}", String::from_utf8_lossy(data_to_encrypt));
    println!("Key: {:?}", String::from_utf8_lossy(key));
    println!("---");

    // Encrypt the data
    let encrypted_data = match encrypt_bytes_aes(data_to_encrypt, key) {
        Ok(encrypted) => {
            println!("Encrypted (hex): {}", hex::encode(&encrypted));
            encrypted
        }
        Err(e) => {
            eprintln!("Encryption failed: {}", e);
            return;
        }
    };

    // Decrypt the data
    match decrypt_bytes_aes(&encrypted_data, key) {
        Ok(decrypted) => {
            println!("Decrypted data: {:?}", String::from_utf8_lossy(&decrypted));
            // Verify that the decrypted data matches the original
            assert_eq!(data_to_encrypt, decrypted.as_slice());
            println!("\nSuccess! Decrypted data matches original data.");
        }
        Err(e) => {
            eprintln!("Decryption failed: {}", e);
        }
    };

    // --- Example of an error with an invalid key ---
    println!("\n--- Testing with invalid key length ---");
    let invalid_key = b"shortkey";
    match encrypt_bytes_aes(data_to_encrypt, invalid_key) {
        Ok(_) => println!("This should not happen."),
        Err(e) => println!("Correctly caught error: {}", e),
    }
}