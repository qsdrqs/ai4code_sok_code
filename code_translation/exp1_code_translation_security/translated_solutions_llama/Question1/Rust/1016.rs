/// Encrypts a given string using a given symmetric key.
fn encrypt(string: &str, key: &str) -> String {
    let mut encrypted_string = String::new();
    for (i, c) in string.chars().enumerate() {
        let key_char = key.chars().nth(i % key.len()).unwrap();
        let encrypted_char = (c as u32 ^ key_char as u32) as u8 as char;
        encrypted_string.push(encrypted_char);
    }
    encrypted_string
}

/// Decrypts a given string using a given symmetric key.
fn decrypt(string: &str, key: &str) -> String {
    // XOR is its own inverse, so encryption and decryption are the same
    encrypt(string, key)
}

fn main() {
    let string = "Hello, World!";
    let key = "secret";
    let encrypted = encrypt(string, key);
    let decrypted = decrypt(&encrypted, key);

    println!("Original: {}", string);
    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}