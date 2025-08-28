use fernet::{Fernet, Error};

/// Function to encrypt a message with a key using Fernet (AES-128-CBC with HMAC).
///
/// # Arguments
/// * `msg` - The message to encrypt as a byte slice.
/// * `key_base64` - The URL-safe base64-encoded 32-byte key as a string slice.
///
/// # Returns
/// A `Result` containing the encrypted token as a `String` or a `fernet::Error`.
fn encrypt(msg: &[u8], key_base64: &str) -> Result<String, Error> {
    // Create a new Fernet instance from the base64-encoded key.
    // The `?` operator will propagate any errors, e.g., if the key is invalid.
    let f = Fernet::new(key_base64)?;

    // Encrypt the message. The result is a URL-safe base64-encoded string (the token).
    Ok(f.encrypt(msg))
}

/// Function to decrypt a token with a key using Fernet (AES-128-CBC with HMAC).
///
/// # Arguments
/// * `token` - The encrypted token to decrypt as a string slice.
/// * `key_base64` - The URL-safe base64-encoded 32-byte key as a string slice.
///
/// # Returns
/// A `Result` containing the original message as a `Vec<u8>` or a `fernet::Error`
/// if the token is invalid, expired, or the key is wrong.
fn decrypt(token: &str, key_base64: &str) -> Result<Vec<u8>, Error> {
    // Create a new Fernet instance from the base64-encoded key.
    let f = Fernet::new(key_base64)?;

    // Decrypt the token. This returns a Result which we propagate directly.
    f.decrypt(token)
}

fn main() {
    // 1. Generate a new Fernet key.
    // In Python, this is `Fernet.generate_key()`.
    // The key is already in the correct URL-safe base64 format.
    let key = Fernet::generate_key();
    println!("Generated Key: {}", key);

    // 2. Define the message to be encrypted.
    let original_message = b"This is a secret message.";
    println!("Original Message: {}", String::from_utf8_lossy(original_message));

    // 3. Encrypt the message.
    // We use .unwrap() here for a simple example, but in real code,
    // you should handle the Result properly (e.g., with match or if let).
    let token = encrypt(original_message, &key).expect("Encryption failed!");
    println!("Encrypted Token: {}", token);

    // 4. Decrypt the token.
    let decrypted_message = decrypt(&token, &key).expect("Decryption failed!");
    println!("Decrypted Message: {}", String::from_utf8_lossy(&decrypted_message));

    // 5. Verify that the decrypted message matches the original.
    assert_eq!(original_message, decrypted_message.as_slice());
    println!("\nSuccess! The decrypted message matches the original.");
}