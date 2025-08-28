use aes_gcm::{Aes256Gcm, Key, Nonce};
use rand::Rng;
use std::convert::TryInto;

fn encrypt_string(message: &str, key: &[u8], nonce: &[u8]) -> Vec<u8> {
    let key = Key::from_slice(key);
    let nonce = Nonce::from_slice(nonce);
    let cipher = Aes256Gcm::new(key);
    let encrypted = cipher.encrypt(nonce, message.as_bytes()).unwrap();
    encrypted
}

fn decrypt_string(ciphertext: &[u8], key: &[u8], nonce: &[u8]) -> String {
    let key = Key::from_slice(key);
    let nonce = Nonce::from_slice(nonce);
    let cipher = Aes256Gcm::new(key);
    let decrypted = cipher.decrypt(nonce, ciphertext).unwrap();
    String::from_utf8(decrypted).unwrap()
}

fn main() {
    let key = b"This is a key123";
    let iv = b"This is an IV456";
    let message = "The answer is no";

    let encrypted = encrypt_string(message, key, iv);
    let decrypted = decrypt_string(&encrypted, key, iv);

    println!("Original Message: {}", message);
    println!("Encrypted: {:?}", encrypted);
    println!("Decrypted: {}", decrypted);
}