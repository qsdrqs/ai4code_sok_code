use ring::pbkdf2;
use ring::rand::{SecureRandom, SystemRandom};
use base64::{engine::general_purpose::URL_SAFE, Engine as _};
use aes_gcm::{Aes256Gcm, Key, Nonce, KeyInit};
use aes_gcm::aead::{Aead, OsRng};
use std::num::NonZeroU32;

// Custom Fernet-like implementation using AES-GCM
struct Fernet {
    key: [u8; 32],
}

impl Fernet {
    fn new(key: &[u8]) -> Self {
        let mut key_array = [0u8; 32];
        key_array.copy_from_slice(&key[..32]);
        Self { key: key_array }
    }

    fn encrypt(&self, data: &[u8]) -> Vec<u8> {
        let cipher = Aes256Gcm::new(Key::<Aes256Gcm>::from_slice(&self.key));
        let nonce_bytes = {
            let mut nonce = [0u8; 12];
            OsRng.fill_bytes(&mut nonce);
            nonce
        };
        let nonce = Nonce::from_slice(&nonce_bytes);
        
        let ciphertext = cipher.encrypt(nonce, data).expect("encryption failure!");
        
        let mut result = Vec::new();
        result.extend_from_slice(&nonce_bytes);
        result.extend_from_slice(&ciphertext);
        result
    }

    fn decrypt(&self, data: &[u8]) -> Result<Vec<u8>, &'static str> {
        if data.len() < 12 {
            return Err("Invalid data length");
        }
        
        let cipher = Aes256Gcm::new(Key::<Aes256Gcm>::from_slice(&self.key));
        let nonce = Nonce::from_slice(&data[..12]);
        let ciphertext = &data[12..];
        
        cipher.decrypt(nonce, ciphertext).map_err(|_| "decryption failure!")
    }
}

fn derive_key(password: &[u8], salt: &[u8], iterations: u32) -> Vec<u8> {
    let mut key = [0u8; 32];
    pbkdf2::derive(
        pbkdf2::PBKDF2_HMAC_SHA256,
        NonZeroU32::new(iterations).unwrap(),
        salt,
        password,
        &mut key,
    );
    URL_SAFE.encode(&key).into_bytes()
}

fn encrypt(message: &str, password: &str, iterations: u32) -> Vec<u8> {
    let message_bytes = message.as_bytes();
    
    // Generate random salt
    let mut salt = [0u8; 16];
    let rng = SystemRandom::new();
    rng.fill(&mut salt).unwrap();
    
    let key = derive_key(password.as_bytes(), &salt, iterations);
    let key_decoded = URL_SAFE.decode(&key).unwrap();
    
    let fernet = Fernet::new(&key_decoded);
    let encrypted = fernet.encrypt(message_bytes);
    
    // Combine salt + iterations + encrypted_data
    let mut result = Vec::new();
    result.extend_from_slice(&salt);
    result.extend_from_slice(&iterations.to_be_bytes());
    result.extend_from_slice(&encrypted);
    
    URL_SAFE.encode(&result).into_bytes()
}

fn decrypt(token: &[u8], password: &str) -> Result<Vec<u8>, &'static str> {
    let token_str = std::str::from_utf8(token).map_err(|_| "Invalid UTF-8")?;
    let decoded = URL_SAFE.decode(token_str).map_err(|_| "Base64 decode error")?;
    
    if decoded.len() < 20 {
        return Err("Token too short");
    }
    
    let salt = &decoded[..16];
    let iter_bytes = &decoded[16..20];
    let encrypted_data = &decoded[20..];
    
    let iterations = u32::from_be_bytes([
        iter_bytes[0], iter_bytes[1], iter_bytes[2], iter_bytes[3]
    ]);
    
    let key = derive_key(password.as_bytes(), salt, iterations);
    let key_decoded = URL_SAFE.decode(&key).map_err(|_| "Key decode error")?;
    
    let fernet = Fernet::new(&key_decoded);
    fernet.decrypt(encrypted_data)
}

fn main() {
    let token = encrypt("nice123", "password123", 100_000);
    println!("{}", String::from_utf8_lossy(&token));
    
    match decrypt(&token, "password123") {
        Ok(decrypted) => println!("{}", String::from_utf8_lossy(&decrypted)),
        Err(e) => println!("Decryption error: {}", e),
    }
}