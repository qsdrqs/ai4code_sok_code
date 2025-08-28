fn encrypt(string: &str, key: i32) -> String {
    /// Encrypts a string using a given key.
    let mut encrypted = String::new();
    for ch in string.chars() {
        let new_char = char::from_u32((ch as u32 as i32 + key) as u32).unwrap_or(ch);
        encrypted.push(new_char);
    }
    encrypted
}

fn decrypt(string: &str, key: i32) -> String {
    /// Decrypts a string using a given key.
    let mut decrypted = String::new();
    for ch in string.chars() {
        let new_char = char::from_u32((ch as u32 as i32 - key) as u32).unwrap_or(ch);
        decrypted.push(new_char);
    }
    decrypted
}

fn main() {
    /// Main function.
    let string = "Hello World!";
    let key = 5;
    let encrypted = encrypt(string, key);
    let decrypted = decrypt(&encrypted, key);
    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}