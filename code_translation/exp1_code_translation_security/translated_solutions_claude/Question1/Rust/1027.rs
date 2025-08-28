use aes::Aes128;
use aes::cipher::{
    BlockEncrypt, BlockDecrypt, KeyInit,
    block_padding::Pkcs7,
    BlockEncryptMut, BlockDecryptMut,
};
use cbc::{Encryptor, Decryptor};
use rand::RngCore;

type Aes128CbcEnc = Encryptor<Aes128>;
type Aes128CbcDec = Decryptor<Aes128>;

fn encrypt(message: &str, key: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    // Convert message to bytes
    let message_bytes = message.as_bytes();
    
    // Generate random IV
    let mut iv = [0u8; 16];
    rand::thread_rng().fill_bytes(&mut iv);
    
    // Create cipher
    let cipher = Aes128CbcEnc::new(key.into(), &iv.into());
    
    // Encrypt message (padding is handled automatically)
    let ciphertext = cipher.encrypt_padded_vec_mut::<Pkcs7>(message_bytes);
    
    // Prepend IV to ciphertext (common practice)
    let mut result = Vec::with_capacity(16 + ciphertext.len());
    result.extend_from_slice(&iv);
    result.extend_from_slice(&ciphertext);
    
    Ok(result)
}

fn decrypt(ciphertext: &[u8], key: &[u8]) -> Result<String, Box<dyn std::error::Error>> {
    // Extract IV from the beginning of ciphertext
    if ciphertext.len() < 16 {
        return Err("Ciphertext too short".into());
    }
    
    let (iv, encrypted_data) = ciphertext.split_at(16);
    
    // Create cipher
    let cipher = Aes128CbcDec::new(key.into(), iv.into());
    
    // Decrypt message (unpadding is handled automatically)
    let decrypted = cipher.decrypt_padded_vec_mut::<Pkcs7>(encrypted_data)
        .map_err(|e| format!("Decryption failed: {}", e))?;
    
    // Convert message to string
    let message = String::from_utf8(decrypted)
        .map_err(|e| format!("UTF-8 conversion failed: {}", e))?;
    
    Ok(message)
}

// Example usage and test
#[cfg(test)]
mod tests {
    use super::*;
    
    #[test]
    fn test_encrypt_decrypt() {
        let key = b"sixteen byte key"; // 16 bytes for AES-128
        let message = "Hello, World!";
        
        let encrypted = encrypt(message, key).unwrap();
        let decrypted = decrypt(&encrypted, key).unwrap();
        
        assert_eq!(message, decrypted);
    }
}