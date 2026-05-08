/// Encrypts a given string using a given symmetric key.
pub fn encrypt(string: &str, key: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();
    string
        .chars()
        .enumerate()
        .map(|(i, c)| {
            let xor_val = (c as u32) ^ (key_chars[i % key_len] as u32);
            char::from_u32(xor_val).expect("Invalid Unicode scalar value")
        })
        .collect()
}

/// Decrypts a given string using a given symmetric key.
pub fn decrypt(string: &str, key: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();
    string
        .chars()
        .enumerate()
        .map(|(i, c)| {
            let xor_val = (c as u32) ^ (key_chars[i % key_len] as u32);
            char::from_u32(xor_val).expect("Invalid Unicode scalar value")
        })
        .collect()
}

fn main() {
    let message = "Hello, World!";
    let key = "secret";

    let encrypted = encrypt(message, key);
    let decrypted = decrypt(&encrypted, key);

    println!("Original:  {}", message);
    println!("Encrypted: {:?}", encrypted);
    println!("Decrypted: {}", decrypted);
}