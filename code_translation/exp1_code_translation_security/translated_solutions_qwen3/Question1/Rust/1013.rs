use pbkdf2::Pbkdf2;
use hmac::Hmac;
use sha2::Sha256;
use base64::prelude::{Base64UrlSafeNoPadding, Engine as _};
use fernet::Fernet;
use rand::RngCore;
use std::error::Error;

// Use Hmac with SHA256 for PBKDF2
type HmacSha256 = Hmac<Sha256>;

/// Derive a 32-byte key from a password and salt using PBKDF2-HMAC-SHA256
fn derive_key(password: &[u8], salt: &[u8], iterations: u32) -> Vec<u8> {
    let mut key = vec![0u8; 32];
    Pbkdf2::<HmacSha256>::new()
        .with_password(password)
        .with_salt(salt)
        .with_iterations(iterations)
        .with_output_length(32)
        .derive_into(&mut key)
        .expect("Failed to derive key");
    key
}

/// Encrypt a message using a password
fn encrypt(message: &str, password: &str, iterations: u32) -> String {
    let message_bytes = message.as_bytes();

    // Generate a 16-byte random salt
    let mut salt = [0u8; 16];
    rand::thread_rng().fill_bytes(&mut salt);

    // Derive the key
    let key_bytes = derive_key(password.as_bytes(), &salt, iterations);
    let key_str = Base64UrlSafeNoPadding::encode_string(&key_bytes);

    // Encrypt the message using Fernet
    let fernet = Fernet::new(&key_str).expect("Failed to create Fernet instance");
    let ciphertext_str = fernet.encrypt(message_bytes);

    // Decode the base64 ciphertext to raw bytes
    let ciphertext_bytes = Base64UrlSafeNoPadding::decode_vec(&ciphertext_str)
        .expect("Failed to decode ciphertext");

    // Prepare the final encrypted data: salt + iterations (4 bytes BE) + ciphertext
    let iter_bytes = iterations.to_be_bytes();
    let mut encrypted_data = Vec::with_capacity(salt.len() + iter_bytes.len() + ciphertext_bytes.len());
    encrypted_data.extend_from_slice(&salt);
    encrypted_data.extend_from_slice(&iter_bytes);
    encrypted_data.extend_from_slice(&ciphertext_bytes);

    // Base64 encode the final result
    Base64UrlSafeNoPadding::encode_string(&encrypted_data)
}

/// Decrypt a message using a password
fn decrypt(token: &str, password: &str) -> Result<Vec<u8>, Box<dyn Error>> {
    // Decode the base64 token
    let encrypted_data = Base64UrlSafeNoPadding::decode(token)?;
    if encrypted_data.len() < 20 {
        return Err("Token too short".into());
    }

    // Split into salt, iteration count, and ciphertext bytes
    let (salt, rest) = encrypted_data.split_at(16);
    let (iter_bytes, ciphertext_bytes) = rest.split_at(4);
    let iterations = u32::from_be_bytes(iter_bytes.try_into()?);

    // Re-derive the key
    let key_bytes = derive_key(password.as_bytes(), salt, iterations);
    let key_str = Base64UrlSafeNoPadding::encode_string(&key_bytes);

    // Reconstruct Fernet key and decrypt
    let fernet = Fernet::new(&key_str).expect("Failed to create Fernet instance");
    let ciphertext_str = Base64UrlSafeNoPadding::encode_string(ciphertext_bytes);
    let decrypted_bytes = fernet.decrypt(&ciphertext_str)?;

    Ok(decrypted_bytes)
}

fn main() {
    // Example usage
    let token = encrypt("nice123", "password123", 100_000);
    println!("Encrypted token: {}", token);

    match decrypt(&token, "password123") {
        Ok(decrypted) => println!("Decrypted message: {}", String::from_utf8_lossy(&decrypted)),
        Err(e) => println!("Decryption failed: {}", e),
    }
}