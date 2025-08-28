use fernet::{Fernet, DecryptError};

// Fernet::generate_key() creates a new random key.
// In a real application, you would load this from a secure location
// (e.g., environment variable, secrets manager).
fn generate_example_key() -> String {
    Fernet::generate_key()
}

/// Encrypts a string slice input using the provided key.
/// The key must be a URL-safe base64-encoded 32-byte string.
/// Returns the encrypted message as a string.
fn encrypt(inp: &str, key: &str) -> String {
    // Fernet::new can fail if the key is invalid, but we assume a valid key here.
    // In production, you should handle the Result properly.
    let fernet = Fernet::new(key).expect("Failed to create Fernet instance from key");
    
    // The encrypt method takes a byte slice (&[u8]) and returns a String.
    fernet.encrypt(inp.as_bytes())
}

/// Decrypts an encrypted message string using the provided key.
/// Returns a Result containing the decrypted string on success,
/// or a DecryptError on failure (e.g., invalid key, tampered message).
fn decrypt(inp: &str, key: &str) -> Result<String, DecryptError> {
    // Create a new Fernet instance.
    // The `?` operator will propagate an error if the key is invalid.
    let fernet = Fernet::new(key)?;

    // Decrypt the message. This returns a Vec<u8> (a vector of bytes).
    // The `?` operator propagates any decryption errors.
    let decrypted_bytes = fernet.decrypt(inp)?;

    // Convert the resulting bytes back to a String.
    // This can fail if the original data was not valid UTF-8.
    // We convert the error type to be compatible with DecryptError for simplicity.
    String::from_utf8(decrypted_bytes)
        .map_err(|_| DecryptError::Payload) // Map UTF-8 error to a generic payload error
}

fn main() {
    // Generate a key for this example run.
    let ex_key = generate_example_key();

    // Encrypt the message.
    let encrypted = encrypt("Hello World!", &ex_key);
    println!("Encrypted: {}", encrypted);

    // Decrypt the message.
    // We use .unwrap() here because we expect decryption to succeed in this example.
    // In a real application, you should handle the Result properly (e.g., with match or if let).
    let decrypted = decrypt(&encrypted, &ex_key).unwrap();
    println!("Decrypted: {}", decrypted);
}