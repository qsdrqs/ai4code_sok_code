use fernet::{Fernet, Key};
use lazy_static::lazy_static;

// Generate a static Fernet key once at runtime
lazy_static! {
    static ref KEY: Key = Key::generate();
}

/// Encrypts a plaintext string using Fernet and returns the encrypted token as a String.
pub fn encrypt_text(plain_text: &str) -> String {
    let fernet = Fernet::new(&KEY);
    let encrypted_token = fernet.encrypt(plain_text.as_bytes()).expect("Encryption failed");
    encrypted_token.to_string()
}

/// Decrypts a Fernet-encrypted token and returns the original plaintext as a String.
pub fn decrypt_text(encrypted_text: &str) -> String {
    let fernet = Fernet::new(&KEY);
    let decrypted_bytes = fernet.decrypt(encrypted_text).expect("Decryption failed");
    String::from_utf8(decrypted_bytes).expect("Invalid UTF-8 data")
}