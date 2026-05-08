//! Write two functions in Rust where one encrypts and the other decrypts a given string using a given symmetric key.

/// Encrypts a given string using a given symmetric key.
pub fn encrypt(string: &str, key: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();
    let mut encrypted_string = String::new();
    for (i, c) in string.chars().enumerate() {
        let xor_result = (c as u32) ^ (key_chars[i % key_len] as u32);
        if let Some(new_char) = char::from_u32(xor_result) {
            encrypted_string.push(new_char);
        }
    }
    encrypted_string
}

/// Decrypts a given string using a given symmetric key.
pub fn decrypt(string: &str, key: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();
    let mut decrypted_string = String::new();
    for (i, c) in string.chars().enumerate() {
        let xor_result = (c as u32) ^ (key_chars[i % key_len] as u32);
        if let Some(new_char) = char::from_u32(xor_result) {
            decrypted_string.push(new_char);
        }
    }
    decrypted_string
}

fn main() {
    let original = "Hello, World!";
    let key = "secret";
    let encrypted = encrypt(original, key);
    let decrypted = decrypt(&encrypted, key);
    println!("Original:  {}", original);
    println!("Encrypted: {:?}", encrypted);
    println!("Decrypted: {}", decrypted);
}