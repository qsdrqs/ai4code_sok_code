/// Encrypts a string using a given key.
fn encrypt(string: &str, key: i32) -> String {
    let mut encrypted = String::new();
    for c in string.chars() {
        let new_code = (c as i32) + key;
        let new_char = char::from_u32(new_code as u32)
            .expect("Invalid Unicode code point after encryption");
        encrypted.push(new_char);
    }
    encrypted
}

/// Decrypts a string using a given key.
fn decrypt(string: &str, key: i32) -> String {
    let mut decrypted = String::new();
    for c in string.chars() {
        let new_code = (c as i32) - key;
        let new_char = char::from_u32(new_code as u32)
            .expect("Invalid Unicode code point after decryption");
        decrypted.push(new_char);
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