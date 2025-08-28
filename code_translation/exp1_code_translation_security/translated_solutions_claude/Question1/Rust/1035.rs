use aes_gcm_siv::{
    aead::{Aead, KeyInit, OsRng},
    Aes128GcmSiv, Nonce,
};
use rand::RngCore;

fn encrypt(key: &[u8], msg: &[u8]) -> Result<(Vec<u8>, Vec<u8>), Box<dyn std::error::Error>> {
    let cipher = Aes128GcmSiv::new_from_slice(key)?;
    
    // Generate a random nonce
    let mut nonce_bytes = [0u8; 12];
    OsRng.fill_bytes(&mut nonce_bytes);
    let nonce = Nonce::from_slice(&nonce_bytes);
    
    // Encrypt and get ciphertext with authentication tag
    let ciphertext = cipher.encrypt(nonce, msg)?;
    
    Ok((ciphertext, nonce_bytes.to_vec()))
}

fn decrypt(
    key: &[u8], 
    ciphertext: &[u8], 
    nonce: &[u8]
) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    let cipher = Aes128GcmSiv::new_from_slice(key)?;
    let nonce = Nonce::from_slice(nonce);
    
    // Decrypt and verify authentication tag in one step
    let plaintext = cipher.decrypt(nonce, ciphertext)?;
    
    Ok(plaintext)
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    let key = b"Sixteen byte key";
    
    let (ctext, nonce) = encrypt(key, b"message")?;
    let decrypted = decrypt(key, &ctext, &nonce)?;
    
    println!("{}", String::from_utf8(decrypted)?);
    
    Ok(())
}