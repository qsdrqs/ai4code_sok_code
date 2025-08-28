use std::io;

/// Encrypts a string using a given key.
///
/// # Arguments
///
/// * `string` - The string to be encrypted.
/// * `key` - The encryption key.
///
/// # Returns
///
/// The encrypted string.
fn encrypt(string: &str, key: u8) -> String {
    string
        .chars()
        .map(|c| ((c as u8 + key) as char))
        .collect()
}

/// Decrypts a string using a given key.
///
/// # Arguments
///
/// * `string` - The string to be decrypted.
/// * `key` - The decryption key.
///
/// # Returns
///
/// The decrypted string.
fn decrypt(string: &str, key: u8) -> String {
    string
        .chars()
        .map(|c| ((c as u8 - key) as char))
        .collect()
}

fn main() {
    let string = "Hello World!";
    let key: u8 = 5;
    let encrypted = encrypt(string, key);
    let decrypted = decrypt(&encrypted, key);
    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}