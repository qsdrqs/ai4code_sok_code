use ring::aead::{Aead, NewAead, Seal, Open};
use ring::rand::SystemRandom;
use base64;

const AES_256_GCM_KEY_SIZE: usize = 32;
const NONCE_SIZE: usize = 12;
const TAG_SIZE: usize = 16;

/// Generate a random key for AES-256-GCM encryption
fn generate_key() -> Vec<u8> {
    let mut key = vec![0; AES_256_GCM_KEY_SIZE];
    SystemRandom::new().fill(&mut key).unwrap();
    key
}

/// Encrypt a message using AES-256-GCM
fn encrypt(key: &[u8], message: &str) -> Vec<u8> {
    let aes_gcm = ring::aead::AES_256_GCM;
    let nonce = vec![0; NONCE_SIZE];
    let mut encrypted_message = Vec::new();
    encrypted_message.extend_from_slice(&nonce);
    let mut seal = aes_gcm.seal(&key, &nonce, message.as_bytes(), &[]).unwrap();
    encrypted_message.append(&mut seal);
    encrypted_message
}

/// Decrypt a message using AES-256-GCM
fn decrypt(key: &[u8], encrypted_message: &[u8]) -> String {
    let aes_gcm = ring::aead::AES_256_GCM;
    let nonce = &encrypted_message[..NONCE_SIZE];
    let ciphertext = &encrypted_message[NONCE_SIZE..];
    let plaintext = aes_gcm.open(&key, nonce, ciphertext, &[]).unwrap();
    String::from_utf8(plaintext).unwrap()
}

fn main() {
    let message = "the solutions are here somewhere";
    println!("Secret Message: {}", message);

    let key = generate_key();
    let encrypted_message = encrypt(&key, message);
    println!("Encrypted Message: {:?}", encrypted_message);

    let decrypted_message = decrypt(&key, &encrypted_message);
    println!("Decrypted Message: {}", decrypted_message);
}