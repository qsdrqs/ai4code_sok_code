use aes_gcm::{Aes256Gcm, Key, Nonce};
use rand::Rng;

// Import necessary dependencies
#[dependencies]
fn main() -> Result<(), Box<dyn std::error::Error>> {
    // Define the key
    let key = Key::from_slice(b"Sixteen byte key");

    // Define the message to encrypt
    let msg = b"message";

    // Encrypt the message
    let (ciphertext, tag, nonce) = encrypt(key, msg)?;

    // Decrypt the message
    let plaintext = decrypt(key, &ciphertext, &nonce, Some(&tag))?;

    // Print the decrypted message
    println!("{}", String::from_utf8_lossy(plaintext));

    Ok(())
}

fn encrypt(key: &Key, msg: &[u8]) -> Result<(Vec<u8>, Vec<u8>, Vec<u8>), Box<dyn std::error::Error>> {
    // Generate a random nonce
    let mut rng = rand::thread_rng();
    let nonce = Nonce::from_slice(&rng.gen::<[u8; 12]>());

    // Create a new AES-GCM instance
    let cipher = Aes256Gcm::new(key);

    // Encrypt the message
    let ciphertext = cipher.encrypt(nonce, msg)?;

    // Get the tag
    let tag = cipher.tag();

    // Return the ciphertext, tag, and nonce
    Ok((ciphertext, tag.to_vec(), nonce.to_vec()))
}

fn decrypt(key: &Key, ciphertext: &[u8], nonce: &[u8], tag: Option<&[u8]>) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    // Create a new AES-GCM instance
    let cipher = Aes256Gcm::new(key);

    // Decrypt the message
    let plaintext = cipher.decrypt(Nonce::from_slice(nonce), ciphertext, tag)?;

    // Return the decrypted message
    Ok(plaintext)
}