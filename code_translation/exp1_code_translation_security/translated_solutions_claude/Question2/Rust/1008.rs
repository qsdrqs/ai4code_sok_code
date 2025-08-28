use secp256k1::{Secp256k1, Message, SecretKey};
use sha2::{Sha256, Digest};

/// Sign message using ECDSA key
fn sign_message(message: &str, private_key: &str) -> secp256k1::ecdsa::Signature {
    // Convert message to bytes
    let message_bytes = message.as_bytes();
    
    // Hash the message (ECDSA typically requires a hash)
    let mut hasher = Sha256::new();
    hasher.update(message_bytes);
    let message_hash = hasher.finalize();
    
    // Convert private key string to bytes and create SecretKey
    let private_key_bytes = private_key.as_bytes();
    
    // Ensure we have exactly 32 bytes for the private key
    let mut key_bytes = [0u8; 32];
    let len = std::cmp::min(private_key_bytes.len(), 32);
    key_bytes[..len].copy_from_slice(&private_key_bytes[..len]);
    
    let secret_key = SecretKey::from_slice(&key_bytes)
        .expect("Invalid private key");
    
    // Create secp256k1 context
    let secp = Secp256k1::new();
    
    // Create message from hash
    let message = Message::from_slice(&message_hash)
        .expect("Invalid message hash");
    
    // Sign the message
    secp.sign_ecdsa(&message, &secret_key)
}

fn main() {
    let signature = sign_message("hello world", "12345678901234567890123456789012");
    println!("{:?}", signature);
}