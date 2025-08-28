use secp256k1::{Secp256k1, SecretKey, Message};
use secp256k1::ecdsa::Signature;
use sha2::{Sha256, Digest};

// Function in Rust that signs a given message using a given ECDSA signing key
fn sign_message(message: &[u8], signing_key: &SecretKey) -> Signature {
    // Create secp256k1 context
    let secp = Secp256k1::new();
    
    // Hash the message (ECDSA typically requires a hash of the message)
    let mut hasher = Sha256::new();
    hasher.update(message);
    let hash = hasher.finalize();
    
    // Create a Message from the hash
    let msg = Message::from_slice(&hash).expect("32 bytes");
    
    // Sign the message using the signing key
    let signature = secp.sign_ecdsa(&msg, signing_key);
    
    // Return the signature
    signature
}

// Alternative version if you want to work with hex strings (more similar to Python usage)
fn sign_message_hex(message: &str, signing_key: &SecretKey) -> String {
    let secp = Secp256k1::new();
    
    // Hash the message
    let mut hasher = Sha256::new();
    hasher.update(message.as_bytes());
    let hash = hasher.finalize();
    
    // Create a Message from the hash
    let msg = Message::from_slice(&hash).expect("32 bytes");
    
    // Sign the message using the signing key
    let signature = secp.sign_ecdsa(&msg, signing_key);
    
    // Return the signature as hex string
    signature.to_string()
}