// This is a direct translation of the Python pseudo-cipher.
//
// NOTE on the original Python limitation:
// The original code requires the text and key to be of equal length.
// This Rust version using .zip() will also only encrypt up to the length
// of the SHORTEST of the two inputs (text or key).
//
// To make the key repeat for longer text (a more traditional Vigenère cipher),
// you could chain the .cycle() iterator like this:
// text.chars().zip(key.chars().cycle())

/// Encrypts text by adding the Unicode values of the key's characters.
fn encrypt(text: &str, key: &str) -> String {
    // .chars() creates an iterator over the characters of a string.
    // .zip() combines two iterators into a single iterator of pairs.
    // .map() transforms each pair of characters.
    // .collect() assembles the results from the iterator into a new String.
    text.chars()
        .zip(key.chars())
        .map(|(text_char, key_char)| {
            // Cast characters to u32 to perform arithmetic on their Unicode values.
            let new_char_code = text_char as u32 + key_char as u32;
            // Convert the resulting code back to a character.
            // std::char::from_u32 returns an Option, so we unwrap it.
            // This will panic if the code is not a valid Unicode Scalar Value.
            std::char::from_u32(new_char_code).unwrap()
        })
        .collect()
}

/// Decrypts text by subtracting the Unicode values of the key's characters.
fn decrypt(text: &str, key: &str) -> String {
    text.chars()
        .zip(key.chars())
        .map(|(text_char, key_char)| {
            // Cast characters to u32 to perform arithmetic.
            let new_char_code = text_char as u32 - key_char as u32;
            // Convert the code back to a character.
            std::char::from_u32(new_char_code).unwrap()
        })
        .collect()
}

fn main() {
    let key = "aaaaaaa";
    let decrypted_message = "abcdefg";

    // Encrypt the original message
    let encrypted_message = encrypt(decrypted_message, key);

    // Print the encrypted message
    println!("{}", encrypted_message);

    // Decrypt the message and print the result, which should be the original message
    println!("{}", decrypt(&encrypted_message, key));
}