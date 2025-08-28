use fernet::{Fernet, Key};

/// Encrypts a message using Fernet with the given base64-encoded key.
///
/// # Arguments
/// - `msg`: A byte slice containing the plaintext message.
/// - `key_str`: A base64-encoded string representing the 32-byte Fernet key.
///
/// # Returns
/// A `Vec<u8>` containing the base64-encoded encrypted token.
pub fn encrypt(msg: &[u8], key_str: &str) -> Vec<u8> {
    let key = Key::from_base64(key_str).expect("Invalid key");
    let fernet = Fernet::new(&key);
    fernet.encrypt(msg).into_bytes()
}

/// Decrypts a Fernet-encrypted token using the given base64-encoded key.
///
/// # Arguments
/// - `ciphertext`: A byte slice containing the base64-encoded encrypted token.
/// - `key_str`: A base64-encoded string representing the 32-byte Fernet key.
///
/// # Returns
/// A `Result<Vec<u8>, String>` containing the decrypted plaintext or an error message.
pub fn decrypt(ciphertext: &[u8], key_str: &str) -> Result<Vec<u8>, String> {
    let key = Key::from_base64(key_str).expect("Invalid key");
    let fernet = Fernet::new(&key);

    let token = std::str::from_utf8(ciphertext)
        .map_err(|_| "Invalid UTF-8 in ciphertext".to_string())?;

    fernet
        .decrypt(token)
        .map_err(|e| format!("Decryption failed: {:?}", e))
}