/// Function encrypt
/// 
/// Given a message and a key, encrypt the message using XOR encryption.
fn encrypt(message: &str, key: &str) -> String {
    let mut encrypted = String::new();
    let message_bytes = message.as_bytes();
    let key_bytes = key.as_bytes();
    
    for i in 0..message_bytes.len() {
        let encrypted_byte = message_bytes[i] ^ key_bytes[i % key_bytes.len()];
        encrypted.push(encrypted_byte as char);
    }
    
    encrypted
}

/// Function decrypt
/// 
/// Given an encrypted message and a key, decrypt the message using XOR encryption.
fn decrypt(encrypted: &str, key: &str) -> String {
    encrypt(encrypted, key)
}

fn main() {
    let original = "Hello world";
    let key = "key";
    let encrypted_message = encrypt(original, key);
    let decrypted_message = decrypt(&encrypted_message, key);
    println!("{}", decrypted_message);
}