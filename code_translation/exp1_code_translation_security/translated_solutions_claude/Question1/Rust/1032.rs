/// Write two functions in Rust where one encrypts and the other decrypts a given string using a given symmetric key.

/// Encrypts a string using a given key.
fn encrypt(string: &str, key: u8) -> String {
    let mut encrypted = String::new();
    for ch in string.chars() {
        let encrypted_char = char::from_u32((ch as u32) + (key as u32))
            .unwrap_or(ch); // fallback to original char if conversion fails
        encrypted.push(encrypted_char);
    }
    encrypted
}

/// Decrypts a string using a given key.
fn decrypt(string: &str, key: u8) -> String {
    let mut decrypted = String::new();
    for ch in string.chars() {
        let decrypted_char = char::from_u32((ch as u32) - (key as u32))
            .unwrap_or(ch); // fallback to original char if conversion fails
        decrypted.push(decrypted_char);
    }
    decrypted
}

/// Main function.
fn main() {
    let string = "Hello World!";
    let key = 5;
    let encrypted = encrypt(string, key);
    let decrypted = decrypt(&encrypted, key);
    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}