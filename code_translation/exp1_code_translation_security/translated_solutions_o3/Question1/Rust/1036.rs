// src/main.rs
use std::char;

/// Encrypts a string with a numeric key.
///
/// Each character is shifted by `key` code-points forward.
fn encrypt(input: &str, key: i32) -> String {
    input
        .chars()
        .map(|c| {
            let shifted = c as i32 + key;
            // SAFETY: for this demo we assume all shifted values remain valid
            char::from_u32(shifted as u32).unwrap_or(c)
        })
        .collect()
}

/// Decrypts a string that was encrypted with the same numeric key.
///
/// Each character is shifted by `key` code-points backward.
fn decrypt(input: &str, key: i32) -> String {
    input
        .chars()
        .map(|c| {
            let shifted = c as i32 - key;
            char::from_u32(shifted as u32).unwrap_or(c)
        })
        .collect()
}

fn main() {
    let text = "Hello World!";
    let key = 5;

    let encrypted = encrypt(text, key);
    let decrypted = decrypt(&encrypted, key);

    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}