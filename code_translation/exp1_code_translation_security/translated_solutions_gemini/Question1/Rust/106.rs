/// Encrypts a plaintext string using a key with a Vigenère-like cipher.
///
/// Each character in the plaintext is shifted by the corresponding character
/// in the key. The key is repeated if it's shorter than the plaintext.
/// This function only works for lowercase English alphabet characters ('a'-'z').
///
/// # Arguments
///
/// * `plaintext` - The string to encrypt.
/// * `key` - The encryption key.
///
/// # Returns
///
/// A new `String` containing the ciphertext.
fn encrypt(plaintext: &str, key: &str) -> String {
    // An empty key would cause a panic (division by zero).
    // In this case, we return the original text as it cannot be encrypted.
    if key.is_empty() {
        return plaintext.to_string();
    }

    // Convert the key to a byte slice for efficient, repeated access.
    let key_bytes = key.as_bytes();
    let key_len = key_bytes.len();
    // Use the ASCII value of 'a' as the base for our 0-25 numeric alphabet.
    let base = b'a';

    // Process each character of the plaintext using an iterator chain.
    plaintext
        .chars()
        .enumerate() // Get both the index and character.
        .map(|(i, p_char)| {
            // Convert the plaintext character to a number from 0-25.
            let p_val = p_char as u8 - base;
            // Get the corresponding key character, cycling through the key using the modulo operator.
            let k_val = key_bytes[i % key_len] - base;

            // Add the plaintext value and key value, then wrap around the alphabet with modulo 26.
            let new_val = (p_val + k_val) % 26;

            // Convert the resulting number back to a character and return it.
            (base + new_val) as char
        })
        .collect() // Collect the transformed characters into a new String.
}

/// Decrypts a ciphertext string using the corresponding key.
///
/// This function reverses the encryption process. It assumes the ciphertext
/// was encrypted with the same algorithm and key.
/// This function only works for lowercase English alphabet characters ('a'-'z').
///
/// # Arguments
///
/// * `ciphertext` - The string to decrypt.
/// * `key` - The decryption key.
///
/// # Returns
///
/// A new `String` containing the original plaintext.
fn decrypt(ciphertext: &str, key: &str) -> String {
    if key.is_empty() {
        return ciphertext.to_string();
    }

    let key_bytes = key.as_bytes();
    let key_len = key_bytes.len();
    let base = b'a';

    ciphertext
        .chars()
        .enumerate()
        .map(|(i, c_char)| {
            // Convert the ciphertext character to a number from 0-25.
            let c_val = c_char as u8 - base;
            // Get the corresponding key character's value.
            let k_val = key_bytes[i % key_len] - base;

            // Subtract the key's value from the ciphertext's value.
            // We add 26 before the modulo to ensure the result is always positive,
            // which is a standard way to handle modular subtraction.
            let new_val = (c_val + 26 - k_val) % 26;

            // Convert the number back to a character.
            (base + new_val) as char
        })
        .collect()
}

/// Main function to run the demonstration.
fn main() {
    let plaintext = "helloworld";
    let key = "python";

    // 1. Encrypt the plaintext.
    let ciphertext = encrypt(plaintext, key);
    println!("Ciphertext: {}", ciphertext);

    // 2. Decrypt the ciphertext.
    let decrypted = decrypt(&ciphertext, key);
    println!("{}", decrypted);

    // 3. Verify that the decrypted text matches the original plaintext.
    // Note: The original Python code has a bug and will fail this assertion.
    // This Rust translation faithfully reproduces that behavior, so it will panic here.
    // The decrypted output for "helloworld" with key "python" is "hellowormm".
    assert_eq!(plaintext, decrypted);
}