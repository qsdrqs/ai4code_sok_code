use aes_gcm::aead::{Aead, KeyInit, OsRng};
use aes_gcm::{Aes256Gcm, Key, Nonce}; // Or `Aes128Gcm`
use rand::RngCore;
use std::fs;

fn encrypt(data: &[u8], key: &[u8]) -> Vec<u8> {
    // Create a new AES-GCM cipher instance
    let cipher = Aes256Gcm::new(Key::from_slice(key));

    // Generate a random nonce
    let mut nonce_bytes = [0u8; 12];
    OsRng.fill_bytes(&mut nonce_bytes);
    let nonce = Nonce::from_slice(&nonce_bytes);

    // Encrypt the data
    let encrypted = cipher
        .encrypt(nonce, data)
        .expect("Encryption failed");

    // Combine nonce and encrypted data for storage
    [nonce_bytes.to_vec(), encrypted].concat()
}

fn decrypt(data: &[u8], key: &[u8]) -> Vec<u8> {
    // Create a new AES-GCM cipher instance
    let cipher = Aes256Gcm::new(Key::from_slice(key));

    // Extract the nonce and encrypted data
    let (nonce_bytes, encrypted_data) = data.split_at(12);
    let nonce = Nonce::from_slice(nonce_bytes);

    // Decrypt the data
    cipher
        .decrypt(nonce, encrypted_data)
        .expect("Decryption failed")
}

fn main() {
    // Generate a random 256-bit key
    let mut key = [0u8; 32];
    OsRng.fill_bytes(&mut key);

    // Read the file to encrypt
    let file_path = "/safedir/test.txt";
    let data = fs::read(file_path).expect("Failed to read file");

    // Encrypt the file data
    let encrypted_data = encrypt(&data, &key);

    // For demonstration, decrypt the data back
    let decrypted_data = decrypt(&encrypted_data, &key);

    // Print the decrypted data as a string
    println!(
        "Decrypted data: {}",
        String::from_utf8(decrypted_data).expect("Failed to convert to string")
    );
}