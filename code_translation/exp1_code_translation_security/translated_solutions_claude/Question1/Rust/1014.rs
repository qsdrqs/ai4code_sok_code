use aes::Aes256;
use aes::cipher::{BlockDecrypt, BlockEncrypt, KeyInit};
use aes::cipher::generic_array::GenericArray;
use base64::{Engine as _, engine::general_purpose};
use rand::RngCore;
use sha2::{Sha256, Digest};

/// AES cipher for encrypting and decrypting strings using CBC mode
pub struct AESCipher {
    bs: usize,
    key: [u8; 32],
}

impl AESCipher {
    /// Create a new AESCipher with the given key
    pub fn new(key: &str) -> Self {
        let mut hasher = Sha256::new();
        hasher.update(key.as_bytes());
        let key_hash = hasher.finalize();
        
        let mut key_array = [0u8; 32];
        key_array.copy_from_slice(&key_hash);
        
        AESCipher {
            bs: 32,
            key: key_array,
        }
    }

    /// Encrypt a string and return base64 encoded result
    pub fn encrypt(&self, raw: &str) -> Result<String, Box<dyn std::error::Error>> {
        let padded = self.pad(raw);
        
        // Generate random IV
        let mut iv = [0u8; 16]; // AES block size is 16 bytes
        rand::thread_rng().fill_bytes(&mut iv);
        
        let encrypted = self.encrypt_cbc(&padded.into_bytes(), &iv)?;
        
        // Combine IV + encrypted data
        let mut result = Vec::new();
        result.extend_from_slice(&iv);
        result.extend_from_slice(&encrypted);
        
        Ok(general_purpose::STANDARD.encode(&result))
    }

    /// Decrypt a base64 encoded string
    pub fn decrypt(&self, enc: &str) -> Result<String, Box<dyn std::error::Error>> {
        let enc_bytes = general_purpose::STANDARD.decode(enc)?;
        
        if enc_bytes.len() < 16 {
            return Err("Invalid encrypted data".into());
        }
        
        let iv = &enc_bytes[..16];
        let ciphertext = &enc_bytes[16..];
        
        let decrypted = self.decrypt_cbc(ciphertext, iv)?;
        let unpadded = self.unpad(&decrypted)?;
        
        Ok(String::from_utf8(unpadded)?)
    }

    /// Pad string to block size using PKCS7 padding
    fn pad(&self, s: &str) -> String {
        let bytes = s.as_bytes();
        let pad_len = self.bs - (bytes.len() % self.bs);
        let pad_char = pad_len as u8 as char;
        
        let mut result = s.to_string();
        for _ in 0..pad_len {
            result.push(pad_char);
        }
        result
    }

    /// Remove PKCS7 padding
    fn unpad(&self, s: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
        if s.is_empty() {
            return Err("Empty data for unpadding".into());
        }
        
        let pad_len = s[s.len() - 1] as usize;
        if pad_len > s.len() || pad_len == 0 {
            return Err("Invalid padding".into());
        }
        
        Ok(s[..s.len() - pad_len].to_vec())
    }

    /// Encrypt data using AES-256-CBC
    fn encrypt_cbc(&self, data: &[u8], iv: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
        let cipher = Aes256::new(GenericArray::from_slice(&self.key));
        let mut blocks = Vec::new();
        let mut prev_block = *GenericArray::from_slice(iv);

        for chunk in data.chunks(16) {
            let mut block = [0u8; 16];
            block[..chunk.len()].copy_from_slice(chunk);
            let mut block_array = *GenericArray::from_slice(&block);
            
            // XOR with previous block (CBC mode)
            for i in 0..16 {
                block_array[i] ^= prev_block[i];
            }
            
            cipher.encrypt_block(&mut block_array);
            blocks.extend_from_slice(&block_array);
            prev_block = block_array;
        }

        Ok(blocks)
    }

    /// Decrypt data using AES-256-CBC
    fn decrypt_cbc(&self, data: &[u8], iv: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
        let cipher = Aes256::new(GenericArray::from_slice(&self.key));
        let mut result = Vec::new();
        let mut prev_block = *GenericArray::from_slice(iv);

        for chunk in data.chunks(16) {
            if chunk.len() != 16 {
                return Err("Invalid block size".into());
            }
            
            let mut block = *GenericArray::from_slice(chunk);
            let encrypted_block = block;
            
            cipher.decrypt_block(&mut block);
            
            // XOR with previous block (CBC mode)
            for i in 0..16 {
                block[i] ^= prev_block[i];
            }
            
            result.extend_from_slice(&block);
            prev_block = encrypted_block;
        }

        Ok(result)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_encrypt_decrypt() {
        let key = "mysecretpassword";
        let cipher = AESCipher::new(key);
        let message = "Secret Message A";
        
        let encrypted = cipher.encrypt(message).unwrap();
        let decrypted = cipher.decrypt(&encrypted).unwrap();
        
        println!("Encrypted: {}", encrypted);
        println!("Decrypted: {}", decrypted);
        
        assert_eq!(message, decrypted);
    }
}