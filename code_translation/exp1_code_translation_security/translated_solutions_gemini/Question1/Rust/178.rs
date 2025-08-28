// No external dependencies are needed for this code.
// Everything used is part of the Rust standard library.

/// Encrypts a string using a Caesar cipher.
///
/// # Arguments
/// * `input` - The string slice to encrypt.
/// * `key` - The integer offset to shift characters by.
///
/// This version handles lowercase letters and spaces, and correctly wraps around the alphabet.
fn encrypt(input: &str, key: usize) -> String {
    // Create the alphabet programmatically. This is more robust than a hardcoded list.
    let alpha: Vec<char> = ('a'..='z').collect();
    let alpha_len = alpha.len();
    let mut new_string = String::new();

    for c in input.chars() {
        // Preserve spaces
        if c == ' ' {
            new_string.push(' ');
            continue;
        }

        // Find the character's position in the alphabet.
        // .position() returns an Option, which is None if the character is not found.
        if let Some(alpha_index) = alpha.iter().position(|&x| x == c) {
            // Calculate the new index with wrap-around using the modulo operator.
            // This is the correct and standard way to implement a Caesar cipher.
            let new_index = (alpha_index + key) % alpha_len;
            new_string.push(alpha[new_index]);
        } else {
            // If the character is not in our alphabet (e.g., punctuation, numbers),
            // we'll just append it as-is.
            new_string.push(c);
        }
    }

    new_string
}

/// Decrypts a string that was encrypted with a Caesar cipher.
///
/// # Arguments
/// * `input` - The string slice to decrypt.
/// * `key` - The same integer offset used to encrypt.
///
/// This version handles lowercase letters and spaces.
fn decrypt(input: &str, key: usize) -> String {
    let alpha: Vec<char> = ('a'..='z').collect();
    let alpha_len = alpha.len();
    let mut new_string = String::new();

    for c in input.chars() {
        // Preserve spaces (this was a bug in the original Python code)
        if c == ' ' {
            new_string.push(' ');
            continue;
        }

        if let Some(alpha_index) = alpha.iter().position(|&x| x == c) {
            // Calculate the new index for decryption.
            // We add `alpha_len` before the modulo to handle potential "negative" results
            // if `key` is larger than `alpha_index`.
            let new_index = (alpha_index + alpha_len - (key % alpha_len)) % alpha_len;
            new_string.push(alpha[new_index]);
        } else {
            new_string.push(c);
        }
    }

    new_string
}

fn main() {
    // The original Python code used "worlz", which highlighted a bug in its
    // wrap-around logic. We'll use "world" to show the correct behavior.
    let original_text = "hello world";
    let key = 3;

    println!("Original: {}", original_text);
    println!("Key: {}", key);

    let encrypted = encrypt(original_text, key);
    println!("Encrypted: {}", encrypted);

    let decrypted = decrypt(&encrypted, key);
    println!("Decrypted: {}", decrypted);
}