use base64::{Engine as _, engine::general_purpose};
use aes_gcm::{
    aead::{Aead, KeyInit},
    Aes256Gcm, Nonce,
};
use hkdf::Hkdf;
use sha2::Sha256;
use rand::RngCore;

pub struct Fernet {
    cipher: Aes256Gcm,
}

impl Fernet {
    pub fn new(key: &[u8]) -> Result<Self, Box<dyn std::error::Error>> {
        // Decode the base64 key (Fernet uses 32-byte base64-encoded keys)
        let decoded_key = general_purpose::URL_SAFE_NO_PAD.decode(key)?;
        
        if decoded_key.len() != 32 {
            return Err("Invalid key length".into());
        }
        
        // Use HKDF to derive encryption key from the Fernet key
        let hk = Hkdf::<Sha256>::new(None, &decoded_key);
        let mut encryption_key = [0u8; 32];
        hk.expand(b"encryption", &mut encryption_key)
            .map_err(|_| "HKDF expansion failed")?;
        
        let cipher = Aes256Gcm::new_from_slice(&encryption_key)?;
        
        Ok(Fernet { cipher })
    }
    
    pub fn encrypt(&self, data: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
        // Generate random nonce
        let mut nonce_bytes = [0u8; 12];
        rand::thread_rng().fill_bytes(&mut nonce_bytes);
        let nonce = Nonce::from_slice(&nonce_bytes);
        
        // Encrypt the data
        let ciphertext = self.cipher.encrypt(nonce, data)?;
        
        // Combine nonce + ciphertext and encode as base64
        let mut result = Vec::new();
        result.extend_from_slice(&nonce_bytes);
        result.extend_from_slice(&ciphertext);
        
        Ok(general_purpose::URL_SAFE_NO_PAD.encode(&result).into_bytes())
    }
    
    pub fn decrypt(&self, encrypted_data: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
        // Decode from base64
        let decoded = general_purpose::URL_SAFE_NO_PAD.decode(encrypted_data)?;
        
        if decoded.len() < 12 {
            return Err("Invalid encrypted data length".into());
        }
        
        // Split nonce and ciphertext
        let (nonce_bytes, ciphertext) = decoded.split_at(12);
        let nonce = Nonce::from_slice(nonce_bytes);
        
        // Decrypt
        let plaintext = self.cipher.decrypt(nonce, ciphertext)?;
        
        Ok(plaintext)
    }
}

pub fn encrypt(input: &str, key: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    let fernet = Fernet::new(key)?;
    fernet.encrypt(input.as_bytes())
}

pub fn decrypt(input: &[u8], key: &[u8]) -> Result<String, Box<dyn std::error::Error>> {
    let fernet = Fernet::new(key)?;
    let decrypted = fernet.decrypt(input)?;
    Ok(String::from_utf8(decrypted)?)
}

// Add this to your Cargo.toml:
/*
[dependencies]
base64 = "0.21"
aes-gcm = "0.10"
hkdf = "0.12"
sha2 = "0.10"
rand = "0.8"
*/