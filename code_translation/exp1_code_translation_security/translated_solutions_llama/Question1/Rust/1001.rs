/// Dependencies
use std::string::String;

/// Encrypts a given string using a provided key.
///
/// The encryption process involves adding the ASCII values of the string character and the key character,
/// then taking the modulus of 256 to ensure the result stays within the ASCII range.
///
/// # Arguments
///
/// * `key`: The key to use for encryption.
/// * `string`: The string to encrypt.
///
/// # Returns
///
/// The encrypted string.
fn encrypt(key: &str, string: &str) -> String {
    let mut encrypted_text = String::new();
    for (i, c) in string.chars().enumerate() {
        let key_c = key.chars().nth(i % key.len()).unwrap();
        let encrypted_c = ((c as u8 + key_c as u8) % 256) as u8;
        encrypted_text.push(encrypted_c as char);
    }
    encrypted_text
}

/// Decrypts a given string using a provided key.
///
/// The decryption process involves subtracting the ASCII value of the key character from the string character,
/// adding 256 to handle negative results, and then taking the modulus of 256.
///
/// # Arguments
///
/// * `key`: The key to use for decryption.
/// * `string`: The string to decrypt.
///
/// # Returns
///
/// The decrypted string.
fn decrypt(key: &str, string: &str) -> String {
    let mut decrypted_text = String::new();
    for (i, c) in string.chars().enumerate() {
        let key_c = key.chars().nth(i % key.len()).unwrap();
        let decrypted_c = ((c as u8 - key_c as u8 + 256) % 256) as u8;
        decrypted_text.push(decrypted_c as char);
    }
    decrypted_text
}

fn main() {
    let key = "secret";
    let string = "Hello, World!";
    let encrypted = encrypt(key, string);
    println!("Encrypted: {}", encrypted);
    let decrypted = decrypt(key, &encrypted);
    println!("Decrypted: {}", decrypted);
}