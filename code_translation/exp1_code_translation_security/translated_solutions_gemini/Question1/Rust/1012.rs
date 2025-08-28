use fernet::Fernet;
use lazy_static::lazy_static;
use std::error::Error;

// In Rust, global variables are handled carefully. `lazy_static` ensures
// that the key is generated exactly once, the first time it's needed.
// This is the idiomatic way to handle a shared, read-only global resource.
lazy_static! {
    static ref KEY: String = Fernet::generate_key();
}

/// Encrypts a plain text string.
///
/// # Arguments
/// * `plain_text` - A string slice containing the text to encrypt.
///
/// # Returns
/// A `String` containing the base64-encoded encrypted token.
pub fn encrypt_text(plain_text: &str) -> String {
    // Create a new Fernet instance with the global key.
    // .unwrap() is used here because we know the key, having just generated it, is valid.
    let f = Fernet::new(&KEY).unwrap();

    // The `encrypt` method takes a byte slice (`&[u8]`) and returns a `String` token.
    // `plain_text.as_bytes()` is the Rust equivalent of Python's `plain_text.encode("UTF-8")`.
    f.encrypt(plain_text.as_bytes())
}

/// Decrypts a Fernet token back to plain text.
///
/// NOTE: The original Python code had a bug where it tried to decrypt `plain_text`.
/// This version corrects the bug by decrypting the provided `encrypted_text`.
///
/// # Arguments
/// * `encrypted_text` - The base64-encoded token from the `encrypt_text` function.
///
/// # Returns
/// A `Result` which is:
/// * `Ok(String)` containing the decrypted text if successful.
/// * `Err(Box<dyn Error>)` if decryption or UTF-8 conversion fails.
pub fn decrypt_text(encrypted_text: &str) -> Result<String, Box<dyn Error>> {
    // Create a new Fernet instance with the global key.
    let f = Fernet::new(&KEY).unwrap();

    // The `decrypt` method takes the token and returns a `Result` with the original bytes.
    // We use the `?` operator to automatically propagate any decryption errors.
    let decrypted_bytes = f.decrypt(encrypted_text)?;

    // Convert the resulting bytes back into a String. This can also fail if the
    // original data was not valid UTF-8, so it returns a `Result`.
    // The `?` operator handles this error as well.
    let plain_text = String::from_utf8(decrypted_bytes)?;

    Ok(plain_text)
}

// A main function to demonstrate the usage.
fn main() {
    let original_message = "This is a secret message.";
    println!("Original: {}", original_message);

    // Encrypt the message
    let encrypted_message = encrypt_text(original_message);
    println!("Encrypted: {}", encrypted_message);

    // Decrypt the message
    match decrypt_text(&encrypted_message) {
        Ok(decrypted_message) => {
            println!("Decrypted: {}", decrypted_message);
        }
        Err(e) => {
            eprintln!("Decryption failed: {}", e);
        }
    }
}