use aes_gcm::{
    aead::{Aead, AeadCore, KeyInit, OsRng},
    Aes128Gcm, Nonce, Key
};

/// Function to encrypt a string using a symmetric key
fn encrypt(key: &[u8; 16], data: &str) -> Result<(Vec<u8>, [u8; 12]), Box<dyn std::error::Error>> {
    // Convert the data to bytes
    let data_bytes = data.as_bytes();
    
    // Create a cipher object using the key
    let key = Key::<Aes128Gcm>::from_slice(key);
    let cipher = Aes128Gcm::new(key);
    
    // Generate a random nonce
    let nonce = Aes128Gcm::generate_nonce(&mut OsRng);
    
    // Encrypt the data
    let ciphertext = cipher.encrypt(&nonce, data_bytes)?;
    
    // Return the ciphertext and the nonce
    Ok((ciphertext, nonce.into()))
}

/// Function to decrypt a string
fn decrypt(key: &[u8; 16], nonce: &[u8; 12], ciphertext: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    // Create a cipher object using the key
    let key = Key::<Aes128Gcm>::from_slice(key);
    let cipher = Aes128Gcm::new(key);
    
    // Create nonce from the provided bytes
    let nonce = Nonce::from_slice(nonce);
    
    // Decrypt the data
    let data = cipher.decrypt(nonce, ciphertext)?;
    
    // Return the decrypted data
    Ok(data)
}

/// Function to generate a symmetric key
fn generate_key() -> [u8; 16] {
    // Generate a random key
    let mut key = [0u8; 16];
    use rand::RngCore;
    rand::thread_rng().fill_bytes(&mut key);
    key
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    // Example usage
    let key = generate_key();
    let test_data = "Jim's test";
    let (encrypted, nonce) = encrypt(&key, test_data)?;
    let restored_data = decrypt(&key, &nonce, &encrypted)?;
    let restored_string = String::from_utf8(restored_data)?;
    println!("{}", restored_string);
    
    Ok(())
}

// Add this to your Cargo.toml:
/*
[dependencies]
aes-gcm = "0.10"
rand = "0.8"
*/