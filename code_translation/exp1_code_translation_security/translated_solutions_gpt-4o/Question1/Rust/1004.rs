fn encrypt(message: &str, key: &str) -> String {
    let mut encrypted = String::new();
    for (i, ch) in message.chars().enumerate() {
        let key_char = key.chars().nth(i % key.len()).unwrap();
        let encrypted_char = (ch as u8) ^ (key_char as u8);
        encrypted.push(encrypted_char as char);
    }
    encrypted
}

fn decrypt(encrypted: &str, key: &str) -> String {
    encrypt(encrypted, key)
}

fn main() {
    let message = "Hello world";
    let key = "key";
    let encrypted_message = encrypt(message, key);
    let decrypted_message = decrypt(&encrypted_message, key);
    println!("{}", decrypted_message);
}