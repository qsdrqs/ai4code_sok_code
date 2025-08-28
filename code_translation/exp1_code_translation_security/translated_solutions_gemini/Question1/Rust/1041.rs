// All necessary functionality is part of Rust's standard library, so no
// external dependencies (crates) are needed.

/// Encrypts a string by shifting each character's Unicode value by a given key.
///
/// # Arguments
/// * `text` - A string slice to encrypt.
/// * `key` - The integer value to shift each character by.
///
/// # Returns
/// A new `String` containing the encrypted text.
///
/// # Panics
/// This function will panic if `(char as u32) + key` results in an invalid
/// Unicode Scalar Value. This mirrors the behavior of Python's `chr()`,
/// which would raise a `ValueError`.
fn encrypt(text: &str, key: u32) -> String {
    text.chars()
        .map(|c| {
            // Convert char to its Unicode code point (u32), add the key,
            // and then convert it back to a char.
            let shifted_code = (c as u32) + key;
            std::char::from_u32(shifted_code).unwrap()
        })
        .collect()
}

/// Decrypts a string by shifting each character's Unicode value back by a given key.
///
/// # Arguments
/// * `text` - A string slice to decrypt.
/// * `key` - The integer value to shift each character by.
///
/// # Returns
/// A new `String` containing the decrypted text.
///
/// # Panics
/// This function will panic if `(char as u32) - key` results in an invalid
/// Unicode Scalar Value.
fn decrypt(text: &str, key: u32) -> String {
    text.chars()
        .map(|c| {
            // Convert char to its Unicode code point (u32), subtract the key,
            // and then convert it back to a char.
            let shifted_code = (c as u32) - key;
            std::char::from_u32(shifted_code).unwrap()
        })
        .collect()
}

fn main() {
    // Encrypt a message with a key of 2.
    let msg = encrypt("this is cool if it really works", 2);

    // Decrypt the resulting message with the same key.
    // We pass `&msg` here. Rust automatically converts the `&String` to a `&str`
    // for the function call (this is called Deref Coercion).
    let res = decrypt(&msg, 2);

    // Print the encrypted message.
    println!("{}", msg);

    // Print the decrypted result, which should match the original.
    println!("{}", res);
}