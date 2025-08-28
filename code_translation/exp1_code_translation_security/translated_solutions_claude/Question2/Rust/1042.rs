use secp256k1::{Secp256k1, SecretKey, Message};
use sha2::{Sha256, Digest};

fn sign_message(message: &[u8], private_key: &[u8]) -> Result<[u8; 64], Box<dyn std::error::Error>> {
    // Create secp256k1 context
    let secp = Secp256k1::new();
    
    // Create secret key from private key bytes
    let secret_key = SecretKey::from_slice(private_key)?;
    
    // Hash the message (ECDSA typically signs a hash of the message)
    let mut hasher = Sha256::new();
    hasher.update(message);
    let message_hash = hasher.finalize();
    
    // Create message object from hash
    let msg = Message::from_slice(&message_hash)?;
    
    // Sign the message
    let signature = secp.sign_ecdsa(&msg, &secret_key);
    
    // Return signature as bytes
    Ok(signature.serialize_compact())
}

// Alternative version that returns the signature in DER format (more similar to Python ecdsa)
fn sign_message_der(message: &[u8], private_key: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    let secp = Secp256k1::new();
    let secret_key = SecretKey::from_slice(private_key)?;
    
    let mut hasher = Sha256::new();
    hasher.update(message);
    let message_hash = hasher.finalize();
    
    let msg = Message::from_slice(&message_hash)?;
    let signature = secp.sign_ecdsa(&msg, &secret_key);
    
    // Return signature in DER format (similar to Python ecdsa default)
    Ok(signature.serialize_der().to_vec())
}