// You don't need any external crates; everything used here is in std.

use std::char;

/// Encrypts `text` with `key`.  
/// Both `text` and `key` must have the same number of Unicode scalar
/// values (i.e. the same `chars().count()`).
fn encrypt(text: &str, key: &str) -> String {
    assert_eq!(
        text.chars().count(),
        key.chars().count(),
        "`text` and `key` must be the same length"
    );

    text.chars()
        .zip(key.chars())
        .map(|(t, k)| {
            let codepoint_sum = t as u32 + k as u32;
            // We unwrap here because the Python version would also crash
            // if the resulting code point were invalid.
            char::from_u32(codepoint_sum).unwrap()
        })
        .collect()
}

/// Decrypts `text` with `key`.
fn decrypt(text: &str, key: &str) -> String {
    assert_eq!(
        text.chars().count(),
        key.chars().count(),
        "`text` and `key` must be the same length"
    );

    text.chars()
        .zip(key.chars())
        .map(|(t, k)| {
            let codepoint_diff = t as u32 - k as u32;
            char::from_u32(codepoint_diff).unwrap()
        })
        .collect()
}

fn main() {
    let key = "aaaaaaa";
    let decrypted = "abcdefg";

    // Same sequence as the original Python snippet
    let encrypted = encrypt("abcdefg", key);

    println!("{}", encrypt(decrypted, key));  // prints the encrypted form
    println!("{}", decrypt(&encrypted, key)); // prints the decrypted form
}