use std::char;

/// Encrypts a message using XOR encryption with the given key.
///
/// Each character in the message is XORed with the corresponding character in the key,
/// cycling through the key as needed.
fn encrypt(message: &str, key: &str) -> String {
    if key.is_empty() {
        return message.to_string(); // Optional: handle empty key gracefully
    }

    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();

    message
        .chars()
        .enumerate()
        .map(|(i, c)| {
            let key_c = key_chars[i % key_len];
            let xor = c as u32 ^ key_c as u32;
            char::from_u32(xor).unwrap_or('\u{FFFD}') // Use replacement character on invalid Unicode
        })
        .collect()
}

/// Decrypts an encrypted message using XOR encryption.
///
/// XOR encryption is symmetric, so decryption is the same as encryption.
fn decrypt(encrypted: &str, key: &str) -> String {
    encrypt(encrypted, key)
}

fn main() {
    let message = "Hello world";
    let key = "key";

    let encrypted = encrypt(message, key);
    let decrypted = decrypt(&encrypted, key);

    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}