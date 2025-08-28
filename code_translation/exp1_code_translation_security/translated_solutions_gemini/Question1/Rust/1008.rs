/// Encrypts plaintext with a repeating key.
///
/// This function mimics the Python version by operating on the Unicode
/// code points of the characters. Each character from the plaintext is
/// combined with a character from the key using modular addition.
///
/// # Arguments
///
/// * `key` - The encryption key. It will be repeated if shorter than the plaintext.
/// * `plaintext` - The string to encrypt.
///
/// # Panics
///
/// Panics if the key is empty, as this would cause a division by zero.
fn encrypt(key: &str, plaintext: &str) -> String {
    if key.is_empty() {
        panic!("Encryption key cannot be empty.");
    }

    // Collect key characters into a Vec for efficient random access.
    // In Python, string indexing is fast, but for Rust's UTF-8 strings,
    // iterating is preferred. Collecting to a Vec allows for efficient indexing.
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();

    plaintext
        .chars()
        .enumerate()
        .map(|(i, plain_char)| {
            // Get the corresponding key character, repeating the key as needed.
            // This is equivalent to `key[i % len(key)]`.
            let key_char = key_chars[i % key_len];

            // Perform modular addition on the character's code points.
            // `char as u32` is the Rust equivalent of Python's `ord()`.
            // The modulo 256 ensures the result is a byte-sized value (0-255).
            let cipher_val = (plain_char as u32 + key_char as u32) % 256;

            // Convert the resulting integer back to a character.
            // `std::char::from_u32()` is the Rust equivalent of Python's `chr()`.
            // We can safely `unwrap()` because the value is guaranteed to be in
            // the 0-255 range, which are all valid Unicode code points.
            std::char::from_u32(cipher_val).unwrap()
        })
        .collect() // Collect the resulting characters into a new String.
}

/// Decrypts ciphertext with a repeating key.
///
/// This function reverses the encryption process by using modular subtraction.
///
/// # Arguments
///
/// * `key` - The decryption key. Must be the same as the one used for encryption.
/// * `ciphertext` - The string to decrypt.
///
/// # Panics
///
/// Panics if the key is empty.
fn decrypt(key: &str, ciphertext: &str) -> String {
    if key.is_empty() {
        panic!("Decryption key cannot be empty.");
    }

    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();

    ciphertext
        .chars()
        .enumerate()
        .map(|(i, cipher_char)| {
            let key_char = key_chars[i % key_len];

            // Perform modular subtraction. Adding 256 before the modulo operation
            // handles potential negative results from the subtraction, ensuring
            // the result is always positive, just like in the Python code.
            let plain_val = (256 + cipher_char as u32 - key_char as u32) % 256;

            // Convert the resulting integer back to a character.
            std::char::from_u32(plain_val).unwrap()
        })
        .collect()
}

fn main() {
    let key = "123";
    let message = "helloworld";

    // Encrypt the original message
    let encrypted_message = encrypt(key, message);

    // Decrypt the encrypted message
    let decrypted_message = decrypt(key, &encrypted_message);

    // Print the final decrypted result to verify it matches the original
    println!("{}", decrypted_message);

    // For automated testing, we can assert that the decrypted message
    // is identical to the original message.
    assert_eq!(message, decrypted_message);
}