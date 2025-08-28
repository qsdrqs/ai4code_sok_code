// No external dependencies are needed for this code,
// as all functionality is part of the Rust standard library.

/// Function to encrypt a given string by shifting character codes.
///
/// # Arguments
///
/// * `text` - The string slice to encrypt.
/// * `key` - The integer value to shift each character by.
fn encrypt(text: &str, key: i32) -> String {
    // Iterate over each character in the input string slice.
    text.chars()
        .map(|c| {
            // Cast the character to an i32 to perform safe arithmetic.
            // This prevents underflow/overflow issues that could occur with u32.
            let shifted_code = (c as i32) + key;

            // Convert the new integer code back to a character.
            // `from_u32` expects a u32, so we cast it.
            // Note: This will panic if `shifted_code` is negative or not a valid
            // Unicode scalar value. This behavior is equivalent to the Python
            // `chr()` function raising a ValueError.
            std::char::from_u32(shifted_code as u32)
                .expect("Invalid character code after encryption")
        })
        // Collect the resulting characters into a new, owned String.
        .collect()
}

/// Function to decrypt a given string by reversing the character code shift.
///
/// # Arguments
///
/// * `text` - The string slice to decrypt.
/// * `key` - The integer value that was used for encryption.
fn decrypt(text: &str, key: i32) -> String {
    text.chars()
        .map(|c| {
            // Cast the character to an i32 and subtract the key.
            let shifted_code = (c as i32) - key;

            // Convert back to a character, handling potential errors similarly to encrypt.
            std::char::from_u32(shifted_code as u32)
                .expect("Invalid character code after decryption")
        })
        .collect()
}

fn main() {
    // Encrypt the string "name" with a key of 1234.
    let encrypted_text = encrypt("name", 1234);
    println!("{}", encrypted_text);

    // Decrypt the string "ՀԳԿԷ" with a key of 1234.
    // The result should be the original "name".
    let decrypted_text = decrypt(&encrypted_text, 1234);
    println!("{}", decrypted_text);
}