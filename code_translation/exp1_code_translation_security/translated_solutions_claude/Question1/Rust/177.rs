use aes_gcm::{Aes256Gcm, Key, Nonce, KeyInit};
use aes_gcm::aead::{Aead, AeadCore, OsRng};

fn encrypt(m: &[u8], sk: &[u8; 32]) -> Result<(Vec<u8>, [u8; 12]), Box<dyn std::error::Error>> {
    let key = Key::<Aes256Gcm>::from_slice(sk);
    let cipher = Aes256Gcm::new(key);
    
    let nonce = Aes256Gcm::generate_nonce(&mut OsRng);
    let ciphertext = cipher.encrypt(&nonce, m)?;
    
    let nonce_array: [u8; 12] = nonce.as_slice().try_into()?;
    Ok((ciphertext, nonce_array))
}

fn decrypt(cn: (Vec<u8>, [u8; 12]), sk: &[u8; 32]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    let (c, nonce_array) = cn;
    let key = Key::<Aes256Gcm>::from_slice(sk);
    let cipher = Aes256Gcm::new(key);
    
    let nonce = Nonce::from_slice(&nonce_array);
    let plaintext = cipher.decrypt(nonce, c.as_ref())?;
    
    Ok(plaintext)
}

// Dependencies to add to Cargo.toml:
// [dependencies]
// aes-gcm = "0.10"
// rand_core = { version = "0.6", features = ["std"] }