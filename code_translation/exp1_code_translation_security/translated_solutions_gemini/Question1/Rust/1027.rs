use aes::Aes256;
use block_modes::{BlockMode, Cbc, block_padding::Pkcs7};
use rand::{RngCore, OsRng};

// Type alias for the CBC mode with AES-256 and PKCS#7 padding
type Aes256Cbc = Cbc<Aes256, Pkcs7>;

// The block size for AES is 16 bytes
const IV_SIZE: usize = 16;

/// Encrypts a message using AES-256-CBC with a given key.
/// A random IV is generated and prepended to the ciphertext.
///
/// # Arguments
/// * `message` - The plaintext message to encrypt.
/// * `key` - A 32-byte key for AES-256.
///
/// # Returns
/// A `Result` containing the ciphertext (IV + encrypted data) or an error.
fn encrypt(message: &str, key: &[u8; 32]) -> Result<Vec<u8>, block_modes::InvalidKeyIvLength> {
    // Create a new AES-256 cipher instance
    let cipher = Aes256::new(key.into());

    // Generate a random 16-byte IV
    let mut iv = [0u8; IV_SIZE];
    OsRng.fill_bytes(&mut iv);

    // Create a CBC mode instance with the cipher and IV
    let cbc = Aes256Cbc::new(cipher, &iv.into());

    // Convert message to bytes and encrypt it
    let ciphertext = cbc.encrypt_vec(message.as_bytes());

    // Prepend the IV to the ciphertext and return
    let mut result = Vec::with_capacity(IV_SIZE + ciphertext.len());
    result.extend_from_slice(&iv);
    result.extend_from_slice(&ciphertext);
    
    Ok(result)
}

/// Decrypts a ciphertext using AES-256-CBC with a given key.
/// It assumes the IV is prepended to the ciphertext.
///
/// # Arguments
/// * `ciphertext_with_iv` - The encrypted data, prefixed with the 16-byte IV.
/// * `key` - The 32-byte key used for encryption.
///
/// # Returns
/// A `Result` containing the decrypted plaintext string or an error.
fn decrypt(ciphertext_with_iv: &[u8], key: &[u8; 32]) -> Result<String, Box<dyn std::error::Error>> {
    // Ensure the ciphertext is long enough to contain the IV
    if ciphertext_with_iv.len() < IV_SIZE {
        return Err("Ciphertext is too short to contain an IV".into());
    }

    // Split the IV and the actual ciphertext
    let (iv, ciphertext) = ciphertext_with_iv.split_at(IV_SIZE);

    // Create a new AES-256 cipher instance
    let cipher = Aes256::new(key.into());
    
    // Create a CBC mode instance with the cipher and the extracted IV
    let cbc = Aes256Cbc::new(cipher, iv.into());

    // Decrypt the message and handle potential padding errors
    let decrypted_bytes = cbc.decrypt_vec(ciphertext)?;

    // Convert the decrypted bytes back to a UTF-8 string
    let message = String::from_utf8(decrypted_bytes)?;

    Ok(message)
}

// --- Example Usage ---
fn main() {
    // IMPORTANT: Use a secure, randomly generated key in a real application.
    // This key is for demonstration purposes only. It must be 32 bytes for AES-256.
    let key = b"this_is_a_very_secret_key_1234"; // 32 bytes
    let message = "Hello, Rust! This is a secret message.";

    println!("Original Message: {}", message);
    println!("Key (hex): {}", hex::encode(key));
    println!("-----------------------------------");

    // Encrypt the message
    match encrypt(message, key) {
        Ok(ciphertext) => {
            println!("Encrypted (hex): {}", hex::encode(&ciphertext));

            // Decrypt the message
            match decrypt(&ciphertext, key) {
                Ok(decrypted_message) => {
                    println!("Decrypted Message: {}", decrypted_message);
                    assert_eq!(message, decrypted_message);
                    println!("\n✅ Encryption and Decryption successful!");
                }
                Err(e) => {
                    eprintln!("Decryption failed: {}", e);
                }
            }
        }
        Err(e) => {
            eprintln!("Encryption failed: {}", e);
        }
    }
}