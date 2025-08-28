use aes::Aes128;
use aes::cipher::{BlockEncrypt, BlockDecrypt, KeyInit, generic_array::GenericArray};

pub struct AESCipher {
    key: Vec<u8>,
    blk_sz: usize,
}

impl AESCipher {
    pub fn new(key: Vec<u8>, blk_sz: usize) -> Self {
        AESCipher { key, blk_sz }
    }
    
    pub fn encrypt(&self, msg: &str) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
        let mut msg_bytes = msg.as_bytes().to_vec();
        
        // Padding
        let pad_len = self.blk_sz - (msg_bytes.len() % self.blk_sz);
        for _ in 0..pad_len {
            msg_bytes.push(pad_len as u8);
        }
        
        // Encryption
        let key = GenericArray::from_slice(&self.key);
        let cipher = Aes128::new(key);
        
        let mut encrypted = Vec::new();
        
        // Process each block
        for chunk in msg_bytes.chunks(16) {
            let mut block = GenericArray::clone_from_slice(chunk);
            cipher.encrypt_block(&mut block);
            encrypted.extend_from_slice(&block);
        }
        
        Ok(encrypted)
    }
    
    pub fn decrypt(&self, ciphertext: &[u8]) -> Result<String, Box<dyn std::error::Error>> {
        // Decryption
        let key = GenericArray::from_slice(&self.key);
        let cipher = Aes128::new(key);
        
        let mut decrypted = Vec::new();
        
        // Process each block
        for chunk in ciphertext.chunks(16) {
            let mut block = GenericArray::clone_from_slice(chunk);
            cipher.decrypt_block(&mut block);
            decrypted.extend_from_slice(&block);
        }
        
        // Remove padding
        if decrypted.is_empty() {
            return Err("Empty ciphertext".into());
        }
        
        let pad_len = decrypted[decrypted.len() - 1] as usize;
        if pad_len > decrypted.len() {
            return Err("Invalid padding".into());
        }
        
        decrypted.truncate(decrypted.len() - pad_len);
        
        Ok(String::from_utf8(decrypted)?)
    }
}

// Example usage:
#[cfg(test)]
mod tests {
    use super::*;
    
    #[test]
    fn test_aes_cipher() {
        let key = vec![0u8; 16]; // 128-bit key
        let cipher = AESCipher::new(key, 16);
        
        let message = "Hello, World!";
        let encrypted = cipher.encrypt(message).unwrap();
        let decrypted = cipher.decrypt(&encrypted).unwrap();
        
        assert_eq!(message, decrypted);
    }
}