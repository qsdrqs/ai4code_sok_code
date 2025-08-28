use aes_gcm::{Aes256Gcm, Key, Nonce};
use rand::Rng;
use base64;
use std::convert::TryInto;

// Function to generate a random 32-byte key for AES256
fn generate_key() -> Vec<u8> {
    let mut rng = rand::thread_rng();
    let mut key = vec![0; 32];
    rng.fill_bytes(&mut key);
    key
}

// Function to encrypt a message
fn encrypt(message: &str, key: &[u8]) -> Result<Vec<u8>, String> {
    let key: [u8; 32] = key.try_into().map_err(|_| "Invalid key length".to_string())?;
    let aes_key = Key::from_slice(&key);
    let gcm = Aes256Gcm::new(aes_key);

    let mut rng = rand::thread_rng();
    let nonce: [u8; 12] = rng.gen();
    let nonce = Nonce::from_slice(&nonce);

    let encrypted_message = gcm.encrypt(nonce, message.as_bytes()).map_err(|_| "Encryption failed".to_string())?;
    let mut result = Vec::new();
    result.extend_from_slice(&nonce);
    result.extend_from_slice(&encrypted_message);
    Ok(result)
}

// Function to decrypt a message
fn decrypt(encrypted_message: &[u8], key: &[u8]) -> Result<String, String> {
    let key: [u8; 32] = key.try_into().map_err(|_| "Invalid key length".to_string())?;
    let aes_key = Key::from_slice(&key);
    let gcm = Aes256Gcm::new(aes_key);

    if encrypted_message.len() < 12 {
        return Err("Invalid encrypted message length".to_string());
    }

    let nonce = &encrypted_message[..12];
    let encrypted_message = &encrypted_message[12..];
    let nonce = Nonce::from_slice(nonce);

    let decrypted_message = gcm.decrypt(nonce, encrypted_message).map_err(|_| "Decryption failed".to_string())?;
    Ok(String::from_utf8(decrypted_message).map_err(|_| "Invalid UTF-8".to_string())?)
}

fn main() {
    let key = generate_key();
    let message = "Hello, World!";
    let encrypted_message = encrypt(message, &key).unwrap();
    let base64_encrypted_message = base64::encode(&encrypted_message);
    println!("Base64 Encrypted Message: {}", base64_encrypted_message);

    let decoded_encrypted_message = base64::decode(base64_encrypted_message).unwrap();
    let decrypted_message = decrypt(&decoded_encrypted_message, &key).unwrap();
    println!("Decrypted Message: {}", decrypted_message);
}