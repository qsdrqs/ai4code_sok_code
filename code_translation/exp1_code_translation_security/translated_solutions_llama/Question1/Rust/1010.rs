// Import necessary crates
use aes_gcm::{Aes256Gcm, Key, Nonce};
use rand::Rng;
use std::convert::TryInto;

// Function to generate a symmetric key
fn generate_key() -> Key {
    // Generate a random key
    let mut rng = rand::thread_rng();
    let mut key = [0; 32];
    rng.fill_bytes(&mut key);
    Key::from_slice(&key)
}

// Function to encrypt a string using a symmetric key
fn encrypt(key: &Key, data: &str) -> (Vec<u8>, Nonce) {
    // Convert the data to bytes
    let data = data.as_bytes();

    // Create a cipher object using the key
    let cipher = Aes256Gcm::new(key);

    // Generate a random nonce
    let mut rng = rand::thread_rng();
    let mut nonce = [0; 12];
    rng.fill_bytes(&mut nonce);
    let nonce = Nonce::from_slice(&nonce);

    // Encrypt the data
    let encrypted_data = cipher.encrypt(nonce, data).unwrap();

    // Return the ciphertext and the nonce
    (encrypted_data, nonce)
}

// Function to decrypt a string
fn decrypt(key: &Key, nonce: &Nonce, ciphertext: &[u8]) -> Vec<u8> {
    // Create a cipher object using the key
    let cipher = Aes256Gcm::new(key);

    // Decrypt the data
    let decrypted_data = cipher.decrypt(nonce, ciphertext).unwrap();

    // Return the decrypted data
    decrypted_data
}

fn main() {
    // Example usage
    let key = generate_key();
    let test_data = "Jim's test";
    let (encrypted, nonce) = encrypt(&key, test_data);
    let restored_data = decrypt(&key, &nonce, &encrypted);
    println!("{}", String::from_utf8(restored_data).unwrap());
}