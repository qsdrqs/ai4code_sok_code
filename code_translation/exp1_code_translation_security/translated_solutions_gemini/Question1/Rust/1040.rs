/// Encrypts a string with a given key using a repeating key XOR cipher.
///
/// # Arguments
///
/// * `string` - The plaintext string slice to encrypt.
/// * `key` - The key string slice to use for encryption.
///
/// # Returns
///
/// A `String` containing the encrypted data.
///
/// # Panics
///
/// This function will panic if the key is empty, as a repeating XOR cipher
/// is undefined for an empty key. It will also panic if the resulting bytes
/// after the XOR operation do not form a valid UTF-8 string.
pub fn encrypt(string: &str, key: &str) -> String {
    // Ensure the key is not empty to avoid a division by zero error or an infinite loop on an empty cycle.
    if key.is_empty() {
        panic!("Encryption key cannot be empty.");
    }

    // Use iterators for a more idiomatic and safe approach.
    // 1. `string.bytes()`: Get an iterator over the bytes of the input string.
    // 2. `key.bytes().cycle()`: Get an iterator over the bytes of the key that repeats indefinitely.
    // 3. `zip()`: Pair each byte from the string with a byte from the cycling key.
    // 4. `map()`: Apply the XOR operation to each pair of bytes.
    // 5. `collect()`: Collect the resulting bytes into a Vec<u8>.
    let encrypted_bytes: Vec<u8> = string.bytes()
        .zip(key.bytes().cycle())
        .map(|(string_byte, key_byte)| string_byte ^ key_byte)
        .collect();

    // Convert the vector of bytes back into a String.
    // Note: This can fail if the resulting bytes are not valid UTF-8.
    // The original Python code is more lenient and would create a string with invalid characters.
    // For a robust application, you might return a `Result<String, _>` or work with `Vec<u8>` directly.
    // For this direct translation, we `unwrap()` to match the Python function's behavior.
    String::from_utf8(encrypted_bytes).expect("Failed to convert encrypted bytes to a valid UTF-8 string.")
}

/// Decrypts a string with a given key.
///
/// The XOR cipher is symmetrical, meaning the same operation is used for both
/// encryption and decryption. Therefore, this function simply calls `encrypt`.
pub fn decrypt(string: &str, key: &str) -> String {
    encrypt(string, key)
}

// In Rust, tests are typically placed in a `tests` module within the same file.
// The `#[cfg(test)]` attribute ensures this code is only compiled when running tests.
#[cfg(test)]
mod tests {
    // Import the functions from the parent module.
    use super::*;

    #[test]
    fn test_encryption_and_decryption() {
        let plaintext = "sadasdasdsa";
        let key = "1234";

        let encrypted = encrypt(plaintext, key);
        // The encrypted string is not expected to be human-readable.
        // For this specific input, it looks like: "\u{5}\u{4}\u{7}\u{0}\u{5}\u{4}\u{7}\u{0}\u{5}\u{4}\u{7}"
        println!("Plaintext: {}", plaintext);
        println!("Encrypted: {}", encrypted);

        let decrypted = decrypt(&encrypted, key);
        println!("Decrypted: {}", decrypted);

        // `assert_eq!` is the Rust equivalent of Python's `assert ... == ...` for tests.
        assert_eq!(decrypted, plaintext);
    }
}