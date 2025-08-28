/// Encrypts a string using a given key by shifting character codes.
///
/// # Arguments
///
/// * `text` - The string slice to encrypt.
/// * `key` - The integer value to shift each character by.
///
/// # Returns
///
/// A new `String` containing the encrypted text.
fn encrypt(text: &str, key: u32) -> String {
    text.chars()
        .map(|c| {
            // Cast the character to a u32, add the key, and convert back to a char.
            // std::char::from_u32 returns an Option, so we unwrap it.
            // This will panic if the resulting code point is not a valid char,
            // similar to how Python's chr() would raise a ValueError.
            std::char::from_u32(c as u32 + key).unwrap()
        })
        .collect() // Collect the iterator of chars into a new String
}

/// Decrypts a string using a given key.
///
/// # Arguments
///
/// * `text` - The string slice to decrypt.
/// * `key` - The integer value that was used for encryption.
///
/// # Returns
///
/// A new `String` containing the decrypted text.
fn decrypt(text: &str, key: u32) -> String {
    text.chars()
        .map(|c| {
            // Cast the character to a u32, subtract the key, and convert back.
            std::char::from_u32(c as u32 - key).unwrap()
        })
        .collect()
}

/// Main function, the entry point of the program.
fn main() {
    let string = "Hello World!";
    let key = 5;

    let encrypted = encrypt(string, key);
    // We pass a reference to the `encrypted` String.
    // Rust can automatically convert `&String` to `&str` for the function argument.
    let decrypted = decrypt(&encrypted, key);

    println!("Original:  {}", string);
    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}