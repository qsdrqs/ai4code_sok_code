//! This module provides functions to encrypt and decrypt a string using a symmetric key
//! based on a repeating key XOR cipher.

/// Encrypts a given string using a given symmetric key.
///
/// This function performs a repeating key XOR operation between the characters of the
/// input string and the key. If the key is shorter than the string, it will be

/// repeated as necessary.
///
/// # Arguments
///
/// * `string` - The plaintext string to encrypt (`&str`).
/// * `key` - The symmetric key to use for encryption (`&str`).
///
/// # Returns
///
/// A `String` containing the encrypted ciphertext.
///
/// # Panics
///
/// This function will panic if the provided `key` is empty, as encryption with an
/// empty key is undefined and would cause a crash.
pub fn encrypt(string: &str, key: &str) -> String {
    // Ensure the key is not empty to prevent a panic on `cycle()` and for security.
    if key.is_empty() {
        panic!("Encryption key cannot be empty.");
    }

    // 1. `string.chars()`: Get an iterator over the characters of the input string.
    // 2. `key.chars().cycle()`: Get an iterator over the key's characters that repeats indefinitely.
    // 3. `zip()`: Pair up each character from the string with a character from the cycling key.
    // 4. `map()`: For each pair, perform the XOR operation.
    //    - `s_char as u32` and `k_char as u32`: Cast characters to their Unicode integer values.
    //    - `^`: The bitwise XOR operator.
    //    - `std::char::from_u32(...)`: Safely convert the resulting integer back to a character.
    //    - `unwrap_or('�')`: If the XOR result is not a valid character, use the Unicode
    //      replacement character '�' instead of crashing. This is a robust approach.
    // 5. `collect()`: Collect the resulting characters into a new `String`.
    string.chars()
        .zip(key.chars().cycle())
        .map(|(s_char, k_char)| {
            std::char::from_u32((s_char as u32) ^ (k_char as u32))
                .unwrap_or('�')
        })
        .collect()
}

/// Decrypts a given string using a given symmetric key.
///
/// This function reverses the XOR encryption process. Due to the symmetric nature of the
/// XOR cipher (`(A ^ B) ^ B = A`), the decryption algorithm is identical to the
/// encryption algorithm.
///
/// # Arguments
///
/// * `string` - The ciphertext string to decrypt (`&str`).
/// * `key` - The symmetric key that was used for encryption (`&str`).
///
/// # Returns
///
/// A `String` containing the decrypted plaintext.
///
/// # Panics
///
/// This function will panic if the provided `key` is empty.
pub fn decrypt(string: &str, key: &str) -> String {
    // Decryption is the same operation as encryption for a simple XOR cipher.
    encrypt(string, key)
}

// Example of how to use the functions
fn main() {
    let original_string = "Hello, Rust! This is a test of the XOR cipher.";
    let key = "SuperSecretKey123";

    println!("Original:  '{}'", original_string);
    println!("Key:       '{}'", key);

    // Encrypt the string
    let encrypted_string = encrypt(original_string, key);
    println!("Encrypted: '{}'", encrypted_string);

    // Decrypt the string
    let decrypted_string = decrypt(&encrypted_string, key);
    println!("Decrypted: '{}'", decrypted_string);

    // Verify that the decrypted string matches the original
    assert_eq!(original_string, decrypted_string);
    println!("\n✅ Successfully encrypted and decrypted the message!");
}