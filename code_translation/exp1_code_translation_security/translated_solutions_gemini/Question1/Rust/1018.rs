use fernet::{Fernet, DecryptError};

/// Encrypts a string slice using a provided key.
///
/// # Arguments
/// * `input` - The string slice to encrypt.
/// * `key` - A URL-safe base64-encoded 32-byte key.
///
/// # Returns
/// A `String` containing the Fernet token.
/// Panics if the key is invalid. For production code, consider returning a Result.
fn encrypt(input: &str, key: &str) -> String {
    // Create a new Fernet instance from the key.
    // In Python, an invalid key would raise an exception. Here, `new` returns a Result.
    // We use `unwrap()` for a direct translation, which will panic on failure.
    let fernet = Fernet::new(key).expect("Failed to create Fernet instance. Key is likely invalid.");

    // Encrypt the input. The `encrypt` method takes a byte slice `&[u8]`.
    // `input.as_bytes()` is the Rust equivalent of Python's `input.encode()`.
    fernet.encrypt(input.as_bytes())
}

/// Decrypts a Fernet token using a provided key.
///
/// # Arguments
/// * `input` - The Fernet token string to decrypt.
/// * `key` - The URL-safe base64-encoded 32-byte key used for encryption.
///
/// # Returns
/// A `Result` which is:
/// - `Ok(String)` containing the decrypted message if successful.
/// - `Err(Box<dyn std::error::Error>)` if decryption or UTF-8 decoding fails.
fn decrypt(input: &str, key: &str) -> Result<String, Box<dyn std::error::Error>> {
    // Create a new Fernet instance, same as in encrypt.
    let fernet = Fernet::new(key).expect("Failed to create Fernet instance. Key is likely invalid.");

    // Decrypt the token. This returns a `Result<Vec<u8>, DecryptError>`.
    // The `?` operator will propagate any decryption error.
    let decrypted_bytes = fernet.decrypt(input)?;

    // Convert the resulting byte vector back to a String.
    // This is the equivalent of Python's `.decode()`.
    // This can also fail if the bytes are not valid UTF-8, so we use `?` again.
    let decrypted_string = String::from_utf8(decrypted_bytes)?;

    Ok(decrypted_string)
}


fn main() {
    // Fernet requires a URL-safe base64-encoded 32-byte key.
    // The `fernet` crate provides a helper to generate one.
    let key = Fernet::generate_key();
    let message = "This is a secret message from Rust!";

    println!("Original message: {}", message);
    println!("Generated Key:    {}", key);

    // 1. Encrypt the message
    let encrypted_message = encrypt(message, &key);
    println!("Encrypted token:  {}", encrypted_message);

    // 2. Decrypt the message
    // We use `unwrap()` here because we know the key and token are valid.
    // In a real application, you would handle the `Err` case gracefully.
    match decrypt(&encrypted_message, &key) {
        Ok(decrypted_message) => {
            println!("Decrypted message: {}", decrypted_message);
            // Verify that the decrypted message matches the original
            assert_eq!(message, decrypted_message);
            println!("\n✅ Encryption and decryption successful!");
        }
        Err(e) => {
            eprintln!("\n❌ Decryption failed: {}", e);
        }
    }
}