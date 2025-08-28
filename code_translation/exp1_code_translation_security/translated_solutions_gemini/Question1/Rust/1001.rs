/// Encrypts a string using a repeating key XOR-like cipher (but with addition).
///
/// This function mimics the Python logic by operating on bytes. Each byte of the
/// input string is added to a corresponding byte from the key. The key repeats
/// if it's shorter than the input string. The addition is modular (wraps around 256).
///
/// # Arguments
/// * `key` - The encryption key. An empty key will result in the original string being returned.
/// * `string` - The plaintext string to encrypt.
///
/// # Returns
/// A `String` containing the encrypted text. Note: The result may not be valid UTF-8,
/// in which case invalid byte sequences are replaced with the � character.
pub fn encrypt(key: &str, string: &str) -> String {
    // If the key is empty, there's nothing to encrypt with.
    // The Python version would panic with a ZeroDivisionError. Returning the
    // original string is a safer default.
    if key.is_empty() {
        return string.to_string();
    }

    let key_bytes = key.as_bytes();
    let string_bytes = string.as_bytes();
    let mut encrypted_bytes = Vec::with_capacity(string_bytes.len());

    for (i, &byte) in string_bytes.iter().enumerate() {
        let key_byte = key_bytes[i % key_bytes.len()];
        // `wrapping_add` performs addition that wraps around on overflow,
        // which is equivalent to `(a + b) % 256` for bytes.
        let encrypted_byte = byte.wrapping_add(key_byte);
        encrypted_bytes.push(encrypted_byte);
    }

    // The resulting bytes might not form a valid UTF-8 string.
    // `from_utf8_lossy` converts the byte slice to a String, replacing
    // any invalid sequences with the Unicode replacement character '�'.
    String::from_utf8_lossy(&encrypted_bytes).to_string()
}

/// Decrypts a string using a repeating key.
///
/// This function reverses the `encrypt` operation. Each byte of the encrypted
/// string has the corresponding key byte subtracted from it. The subtraction is
/// modular (wraps around 256).
///
/// # Arguments
/// * `key` - The decryption key. Must be the same as the one used for encryption.
/// * `string` - The encrypted string to decrypt.
///
/// # Returns
/// A `String` containing the decrypted text.
pub fn decrypt(key: &str, string: &str) -> String {
    if key.is_empty() {
        return string.to_string();
    }

    let key_bytes = key.as_bytes();
    let string_bytes = string.as_bytes();
    let mut decrypted_bytes = Vec::with_capacity(string_bytes.len());

    for (i, &byte) in string_bytes.iter().enumerate() {
        let key_byte = key_bytes[i % key_bytes.len()];
        // `wrapping_sub` performs subtraction that wraps around on underflow.
        // This is the idiomatic Rust equivalent of the Python `(a - b + 256) % 256`.
        let decrypted_byte = byte.wrapping_sub(key_byte);
        decrypted_bytes.push(decrypted_byte);
    }

    String::from_utf8_lossy(&decrypted_bytes).to_string()
}

// --- Example Usage and Testing ---
// This main function demonstrates how to use the functions and verifies their correctness.
fn main() {
    let key = "SECRET";
    let plaintext = "This is a test of the encryption system.";

    println!("Plaintext:  {}", plaintext);
    println!("Key:        {}", key);

    let encrypted = encrypt(key, plaintext);
    println!("Encrypted:  {}", encrypted);

    let decrypted = decrypt(key, &encrypted);
    println!("Decrypted:  {}", decrypted);

    // Verify that the decrypted text matches the original plaintext
    assert_eq!(plaintext, decrypted);
    println!("\n✅ Encryption and decryption successful!");
}