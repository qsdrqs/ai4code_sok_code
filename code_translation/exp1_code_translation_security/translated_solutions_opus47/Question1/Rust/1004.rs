/// Given a message and a key, encrypt the message using XOR encryption.
fn encrypt(message: &str, key: &str) -> String {
    let message_chars: Vec<char> = message.chars().collect();
    let key_chars: Vec<char> = key.chars().collect();
    let mut encrypted = String::new();
    for i in 0..message_chars.len() {
        let xored = (message_chars[i] as u32) ^ (key_chars[i % key_chars.len()] as u32);
        encrypted.push(char::from_u32(xored).unwrap());
    }
    encrypted
}

/// Given an encrypted message and a key, decrypt the message using XOR encryption.
fn decrypt(encrypted: &str, key: &str) -> String {
    encrypt(encrypted, key)
}

fn main() {
    println!("{}", decrypt(&encrypt("Hello world", "key"), "key"));
}