use aes_gcm::{
    aead::{Aead, AeadCore, KeyInit, OsRng},
    Aes256Gcm, Nonce, Key
};

// Function in Rust where one encrypts a given string using a given symmetric key.
fn encrypt(key: &[u8; 32], plaintext: &[u8]) -> Result<(Vec<u8>, Vec<u8>, Vec<u8>), Box<dyn std::error::Error>> {
    let cipher = Aes256Gcm::new(Key::<Aes256Gcm>::from_slice(key));
    let nonce = Aes256Gcm::generate_nonce(&mut OsRng);
    
    let ciphertext = cipher.encrypt(&nonce, plaintext)?;
    
    // In AES-GCM, the authentication tag is appended to the ciphertext
    // We need to split them for compatibility with the Python version
    let tag_size = 16; // GCM tag is always 16 bytes
    let (encrypted_data, tag) = ciphertext.split_at(ciphertext.len() - tag_size);
    
    Ok((nonce.to_vec(), tag.to_vec(), encrypted_data.to_vec()))
}

// Function in Rust where one decrypts a given string using a given symmetric key.
fn decrypt(key: &[u8; 32], encrypted_data: (Vec<u8>, Vec<u8>, Vec<u8>)) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    let (nonce, tag, ciphertext) = encrypted_data;
    let cipher = Aes256Gcm::new(Key::<Aes256Gcm>::from_slice(key));
    
    // Reconstruct the full ciphertext with tag appended (as expected by AES-GCM)
    let mut full_ciphertext = ciphertext;
    full_ciphertext.extend_from_slice(&tag);
    
    let nonce = Nonce::from_slice(&nonce);
    let plaintext = cipher.decrypt(nonce, full_ciphertext.as_ref())?;
    
    Ok(plaintext)
}

// Example usage:
fn main() -> Result<(), Box<dyn std::error::Error>> {
    let key = [0u8; 32]; // 256-bit key (you should use a proper key)
    let plaintext = b"Hello, World!";
    
    // Encrypt
    let encrypted = encrypt(&key, plaintext)?;
    println!("Encrypted successfully");
    
    // Decrypt
    let decrypted = decrypt(&key, encrypted)?;
    println!("Decrypted: {}", String::from_utf8(decrypted)?);
    
    Ok(())
}

// Add this to your Cargo.toml:
/*
[dependencies]
aes-gcm = "0.10"
*/