/// Encrypts a string using a given key.
fn encrypt(string: &str, key: u32) -> String {
    let mut encrypted = String::new();
    for c in string.chars() {
        encrypted.push(char::from_u32(c as u32 + key).unwrap());
    }
    encrypted
}

/// Decrypts a string using a given key.
fn decrypt(string: &str, key: u32) -> String {
    let mut decrypted = String::new();
    for c in string.chars() {
        decrypted.push(char::from_u32(c as u32 - key).unwrap());
    }
    decrypted
}

/// Main function.
fn main() {
    let string = "Hello World!";
    let key: u32 = 5;
    let encrypted = encrypt(string, key);
    let decrypted = decrypt(&encrypted, key);
    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}