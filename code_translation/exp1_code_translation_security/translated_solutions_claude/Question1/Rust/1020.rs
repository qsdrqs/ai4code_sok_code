use aes::Aes256;
use block_modes::{BlockMode, Cbc};
use block_modes::block_padding::Pkcs7;
use base64::{Engine as _, engine::general_purpose};
use rand::RngCore;

type AesCbc = Cbc<Aes256, Pkcs7>;

fn encrypt(key: &[u8], _key_size: usize, plaintext: &[u8]) -> Result<String, Box<dyn std::error::Error>> {
    // Generate random IV
    let mut iv = [0u8; 16];
    rand::thread_rng().fill_bytes(&mut iv);
    
    // Create cipher
    let cipher = AesCbc::new_from_slices(key, &iv)?;
    
    // Encrypt the plaintext (padding is handled automatically by Pkcs7)
    let ciphertext = cipher.encrypt_vec(plaintext);
    
    // Combine IV and ciphertext, then base64 encode
    let mut result = Vec::with_capacity(16 + ciphertext.len());
    result.extend_from_slice(&iv);
    result.extend_from_slice(&ciphertext);
    
    Ok(general_purpose::STANDARD.encode(&result))
}

fn decrypt(key: &[u8], _key_size: usize, ciphertext: &str) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    // Base64 decode
    let ciphertext_bytes = general_purpose::STANDARD.decode(ciphertext)?;
    
    // Extract IV and ciphertext
    if ciphertext_bytes.len() < 16 {
        return Err("Ciphertext too short".into());
    }
    
    let iv = &ciphertext_bytes[..16];
    let encrypted_data = &ciphertext_bytes[16..];
    
    // Create cipher
    let cipher = AesCbc::new_from_slices(key, iv)?;
    
    // Decrypt (unpadding is handled automatically by Pkcs7)
    let plaintext = cipher.decrypt_vec(encrypted_data)?;
    
    Ok(plaintext)
}

// Example usage and test function
#[cfg(test)]
mod tests {
    use super::*;
    
    #[test]
    fn test_encrypt_decrypt() {
        let key = b"YELLOW SUBMARINE YELLOW SUBMARINE"; // 32 bytes for AES-256
        let plaintext = b"Hello, World! This is a test message.";
        
        let encrypted = encrypt(key, 256, plaintext).unwrap();
        println!("Encrypted: {}", encrypted);
        
        let decrypted = decrypt(key, 256, &encrypted).unwrap();
        println!("Decrypted: {}", String::from_utf8_lossy(&decrypted));
        
        assert_eq!(plaintext, &decrypted[..]);
    }
}