use fernet::{Fernet, Error};

/// Encrypts a message using a base64-encoded key.
///
/// # Panics
/// This function will panic if the provided key is not valid base64 or
/// if the decoded key is not the correct length.
fn encrypt(msg: &[u8], key_b64: &str) -> String {
    // Create a Fernet instance from the base64-encoded key.
    // The `unwrap()` will cause a panic if the key is invalid,
    // similar to how the Python version would raise an exception.
    let f = Fernet::from_base64(key_b64).unwrap();
    
    // Encrypt the message.
    f.encrypt(msg)
}

/// Decrypts a token using a base64-encoded key.
///
/// Returns a `Result` containing the decrypted message (`Vec<u8>`) on success,
/// or an `Error` if the token is invalid (e.g., wrong key, tampered with).
///
/// # Panics
/// This function will panic if the provided key is not valid base64.
fn decrypt(token: &str, key_b64: &str) -> Result<Vec<u8>, Error> {
    // Create a Fernet instance from the base64-encoded key.
    let f = Fernet::from_base64(key_b64).unwrap();
    
    // Decrypt the token. This returns a Result which we pass on.
    f.decrypt(token)
}

fn main() {
    // Generate a new, random, base64-encoded 256-bit key.
    let key = fernet::generate_key();
    let msg = b"a much longer message with punctuation!";

    println!("Original message: {}", std::str::from_utf8(msg).unwrap());
    println!("Generated key: {}", key);

    // Encrypt the message
    let encrypted_token = encrypt(msg, &key);
    println!("\nEncrypted token: {}", encrypted_token);

    // Decrypt the token
    // In a real application, you should handle this Result properly (e.g., with a `match` statement)
    // instead of `unwrap()`-ing, as decryption can fail.
    let decrypted_bytes = decrypt(&encrypted_token, &key).unwrap();
    
    // Convert the resulting bytes back to a string for printing.
    let decrypted_msg = std::str::from_utf8(&decrypted_bytes).unwrap();
    println!("Decrypted message: {}", decrypted_msg);

    // Verify that the decrypted message matches the original
    assert_eq!(msg, decrypted_bytes.as_slice());
    println!("\nSuccessfully encrypted and decrypted the message!");
}