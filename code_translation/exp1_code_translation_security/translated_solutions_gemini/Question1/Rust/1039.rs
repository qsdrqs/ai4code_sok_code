use fernet::{Fernet, MultiFernet}; // We only need Fernet, but MultiFernet is often used too.
use std::error::Error;

/// Encrypts a message using a base64-encoded key.
///
/// # Arguments
/// * `message` - The string slice to encrypt.
/// * `key` - The base64-encoded encryption key.
///
/// # Returns
/// A `Result` containing the encrypted token as a `String` on success,
/// or an error if the key is invalid.
fn encrypt(message: &str, key: &str) -> Result<String, Box<dyn Error>> {
    // In Rust, creating the Fernet instance can fail if the key is invalid.
    // The `?` operator propagates the error, returning it from the function.
    let fernet = Fernet::new(key)?;

    // The `encrypt` method takes a byte slice `&[u8]`.
    // We convert our string message to bytes using `.as_bytes()`.
    let enc_message = fernet.encrypt(message.as_bytes());

    // The function returns a Result, so we wrap the successful value in `Ok()`.
    Ok(enc_message)
}

/// Decrypts a token using a base64-encoded key.
///
/// # Arguments
/// * `token` - The encrypted token to decrypt.
/// * `key` - The base64-encoded encryption key.
///
/// # Returns
/// A `Result` containing the decrypted message as a vector of bytes (`Vec<u8>`) on success,
/// or an error if the key is invalid or the token cannot be decrypted.
fn decrypt(token: &str, key: &str) -> Result<Vec<u8>, Box<dyn Error>> {
    let fernet = Fernet::new(key)?;

    // The `decrypt` method can fail (e.g., invalid token, wrong key, expired TTL).
    // The `?` operator will propagate the error if decryption fails.
    let dec_message = fernet.decrypt(token)?;

    Ok(dec_message)
}

fn main() {
    // 1. Generate a new secret key. In a real application, you would store
    //    this securely and reuse it.
    let key = Fernet::generate_key();
    let original_message = "This is a secret message.";

    println!("Original Message: {}", original_message);
    println!("Encryption Key:   {}", key);

    // 2. Encrypt the message
    match encrypt(original_message, &key) {
        Ok(encrypted_token) => {
            println!("Encrypted Token:  {}", encrypted_token);

            // 3. Decrypt the token
            match decrypt(&encrypted_token, &key) {
                Ok(decrypted_bytes) => {
                    // The result of decryption is bytes (`Vec<u8>`), because not all
                    // encrypted data is guaranteed to be valid text. We must convert it.
                    let decrypted_message = String::from_utf8(decrypted_bytes)
                        .expect("Decrypted data was not valid UTF-8");
                    
                    println!("Decrypted Message: {}", decrypted_message);

                    assert_eq!(original_message, decrypted_message);
                    println!("\nSuccess! The original and decrypted messages match.");
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