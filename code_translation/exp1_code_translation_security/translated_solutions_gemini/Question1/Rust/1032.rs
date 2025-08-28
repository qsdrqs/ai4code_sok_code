//! This module provides functions to encrypt and decrypt a string using a simple
//! character-shifting (Caesar cipher) method with a symmetric key.

/// Encrypts a string using a given key by shifting each character's Unicode value.
///
/// # Arguments
///
/// * `s` - A string slice to be encrypted.
/// * `key` - An integer representing the shift value.
///
/// # Returns
///
/// A new `String` containing the encrypted text.
fn encrypt(s: &str, key: i32) -> String {
    s.chars()
        .map(|c| {
            // Cast the character to an i32 to perform the addition.
            let shifted_val = (c as i32) + key;
            // Convert the new integer value back to a character.
            // unwrap() is used here for simplicity, assuming the key will not
            // shift the character out of the valid Unicode range.
            std::char::from_u32(shifted_val as u32).unwrap_or(c)
        })
        .collect() // Collect the iterator of characters into a new String.
}

/// Decrypts a string using a given key by reversing the character shift.
///
/// # Arguments
///
/// * `s` - A string slice to be decrypted.
/// * `key` - The same integer used for encryption.
///
/// # Returns
///
/// A new `String` containing the decrypted text.
fn decrypt(s: &str, key: i32) -> String {
    s.chars()
        .map(|c| {
            // Cast the character to an i32 to perform the subtraction.
            let shifted_val = (c as i32) - key;
            // Convert the new integer value back to a character.
            std::char::from_u32(shifted_val as u32).unwrap_or(c)
        })
        .collect()
}

/// Main function to demonstrate the encryption and decryption process.
fn main() {
    let string = "Hello World!";
    let key = 5;

    let encrypted = encrypt(string, key);
    // We pass `&encrypted` because the decrypt function expects a string slice (&str).
    let decrypted = decrypt(&encrypted, key);

    println!("Original:  {}", string);
    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}