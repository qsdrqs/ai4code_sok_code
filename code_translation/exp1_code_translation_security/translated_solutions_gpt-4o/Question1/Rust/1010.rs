use aes::Aes128;
use aes::cipher::{KeyInit, BlockEncryptMut, BlockDecryptMut};
use aes_gcm::aead::{Aead, Key, Nonce, generic_array::GenericArray};
use aes_gcm::Aes128Gcm; // Or Aes256Gcm
use rand::Rng;

/// Function to generate a symmetric key
fn generate_key() -> Vec<u8> {
    // Generate a random 16-byte key
    let mut key = [0u8; 16];
    rand::thread_rng().fill_bytes(&mut key);
    key.to_vec()
}

/// Function to encrypt a string using a symmetric key
fn encrypt(key: &[u8], data: &str) -> (Vec<u8>, Vec<u8>) {
    // Convert the data to bytes
    let data_bytes = data.as_bytes();

    // Create a cipher object using the key
    let cipher = Aes128Gcm::new(GenericArray::from_slice(key));

    // Generate a random nonce
    let mut nonce = [0u8; 12];
    rand::thread_rng().fill_bytes(&mut nonce);

    // Encrypt the data
    let ciphertext = cipher.encrypt(Nonce::from_slice(&nonce), data_bytes)
        .expect("Encryption failed");

    // Return the ciphertext and the nonce
    (ciphertext, nonce.to_vec())
}

/// Function to decrypt a string
fn decrypt(key: &[u8], nonce: &[u8], ciphertext: &[u8]) -> String {
    // Create a cipher object using the key
    let cipher = Aes128Gcm::new(GenericArray::from_slice(key));

    // Decrypt the data
    let decrypted_data = cipher.decrypt(Nonce::from_slice(nonce), ciphertext)
        .expect("Decryption failed");

    // Convert the decrypted data to a string and return it
    String::from_utf8(decrypted_data).expect("Failed to convert decrypted data to string")
}

fn main() {
    // Example usage
    let key = generate_key();
    let test_data = "Jim's test";

    let (encrypted, nonce) = encrypt(&key, test_data);
    let restored_data = decrypt(&key, &nonce, &encrypted);

    println!("Original data: {}", test_data);
    println!("Restored data: {}", restored_data);
}