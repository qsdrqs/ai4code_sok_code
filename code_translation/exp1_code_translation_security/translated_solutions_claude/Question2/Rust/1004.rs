use secp256k1::{Secp256k1, SecretKey, Message};
use sha2::{Sha256, Digest};

/// Function sign_message
/// 
/// Input: message and ECDSA key
/// Output: message signed using the provided key
fn sign_message(message: &[u8], private_key: &[u8]) -> Result<secp256k1::ecdsa::Signature, Box<dyn std::error::Error>> {
    // Create secp256k1 context
    let secp = Secp256k1::new();
    
    // Create secret key from the private key bytes
    // Note: SECP256k1 requires exactly 32 bytes for private key
    let mut key_bytes = [0u8; 32];
    let len = std::cmp::min(private_key.len(), 32);
    key_bytes[..len].copy_from_slice(&private_key[..len]);
    
    let secret_key = SecretKey::from_slice(&key_bytes)?;
    
    // Hash the message using SHA256 (required for secp256k1)
    let mut hasher = Sha256::new();
    hasher.update(message);
    let hash = hasher.finalize();
    
    // Create message from hash
    let msg = Message::from_slice(&hash)?;
    
    // Sign the message
    let signature = secp.sign_ecdsa(&msg, &secret_key);
    
    Ok(signature)
}

fn main() {
    match sign_message("hello".as_bytes(), "qwertyuiopasdfghjklzxcvbnmqwerty".as_bytes()) {
        Ok(signature) => println!("{:?}", signature),
        Err(e) => println!("Error: {}", e),
    }
}