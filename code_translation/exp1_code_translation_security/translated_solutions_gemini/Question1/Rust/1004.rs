/// Given a message and a key, encrypt the message using a repeating-key XOR cipher.
///
/// Note: This is a simple educational example. For any real-world use, please use
/// a robust, authenticated encryption library from a standard cryptography crate.
fn encrypt(message: &str, key: &str) -> String {
    // If the key is empty, encryption is not possible. In the original Python,
    // this would cause a ZeroDivisionError. Here, we return the message unchanged.
    if key.is_empty() {
        return message.to_string();
    }

    // We perform the XOR operation on the Unicode code points of the characters.
    // 1. `message.chars()`: Creates an iterator over the characters of the message.
    // 2. `key.chars().cycle()`: Creates an iterator that repeats the key's characters endlessly.
    // 3. `zip()`: Pairs each message character with a key character.
    // 4. `map()`: Applies the XOR logic to each pair.
    // 5. `collect()`: Gathers the resulting characters into a new String.
    message.chars()
        .zip(key.chars().cycle())
        .map(|(msg_char, key_char)| {
            // Cast characters to u32 to perform the bitwise XOR operation.
            let xor_val = (msg_char as u32) ^ (key_char as u32);
            // Convert the resulting integer back to a character.
            // `unwrap()` is used for simplicity; a more robust solution would handle
            // potential errors if the `xor_val` is not a valid Unicode scalar value.
            std::char::from_u32(xor_val).unwrap()
        })
        .collect()
}

/// Given an encrypted message and a key, decrypt the message.
///
/// For a simple XOR cipher, the decryption process is identical to the encryption process.
fn decrypt(encrypted: &str, key: &str) -> String {
    // Applying the same XOR operation with the same key reverses the encryption.
    encrypt(encrypted, key)
}

fn main() {
    // Encrypt the message and then immediately decrypt it to verify the functions.
    // The output should be the original message: "Hello world".
    let original_message = "Hello world";
    let secret_key = "key";
    
    let encrypted_message = encrypt(original_message, secret_key);
    let decrypted_message = decrypt(&encrypted_message, secret_key);

    println!("{}", decrypted_message);
}