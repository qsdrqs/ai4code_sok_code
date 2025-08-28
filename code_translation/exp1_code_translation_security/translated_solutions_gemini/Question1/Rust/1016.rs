/// Encrypts a given string using a given symmetric key (XOR cipher).
///
/// This function takes a string slice and a key slice and performs a byte-wise
/// XOR operation. The key is repeated if it's shorter than the input string.
pub fn encrypt(string: &str, key: &str) -> String {
    // Handle the edge case of an empty key to avoid a panic when cycling.
    // In this case, we return the original string as no encryption can be performed.
    if key.is_empty() {
        return string.to_string();
    }

    // The core logic uses iterators for an idiomatic Rust approach.
    // 1. `.as_bytes()`: Get the byte representation of the strings.
    // 2. `.iter()`: Create an iterator over the bytes.
    // 3. `.cycle()`: Create an iterator that repeats the key's bytes endlessly.
    // 4. `.zip()`: Pair each byte of the input string with a byte from the cycling key.
    // 5. `.map()`: For each pair, perform the bitwise XOR operation (`^`).
    // 6. `.collect()`: Gather the resulting bytes into a new Vec<u8>.
    let result_bytes: Vec<u8> = string.as_bytes()
        .iter()
        .zip(key.as_bytes().iter().cycle())
        .map(|(s_byte, k_byte)| s_byte ^ k_byte)
        .collect();

    // The result of XORing bytes may not produce valid UTF-8 characters.
    // `from_utf8_lossy` safely converts the byte vector to a String,
    // replacing any invalid sequences with the Unicode replacement character '�'.
    String::from_utf8_lossy(&result_bytes).into_owned()
}

/// Decrypts a given string using a given symmetric key.
///
/// For a simple XOR cipher, encryption and decryption are the exact same operation.
/// (A XOR B) XOR B = A.
pub fn decrypt(string: &str, key: &str) -> String {
    // The decryption process is identical to the encryption process.
    encrypt(string, key)
}

// Main function to demonstrate the usage of the encrypt and decrypt functions.
// This part is equivalent to a script's main execution block in Python.
fn main() {
    let secret_message = "This is a secret message.";
    let secret_key = "my-super-secret-key";

    println!("Original:  '{}'", secret_message);
    println!("Key:       '{}'", secret_key);

    let encrypted_message = encrypt(secret_message, secret_key);
    // Note: The encrypted string may not print cleanly to the console
    // because it can contain non-printable or control characters.
    println!("Encrypted: '{}'", encrypted_message);

    let decrypted_message = decrypt(&encrypted_message, secret_key);
    println!("Decrypted: '{}'", decrypted_message);

    // Assert that the decryption was successful
    assert_eq!(secret_message, decrypted_message);
    println!("\n✅ Decryption successful!");
}