use sha2::{Sha256, Digest};
use secp256k1::{Secp256k1, SecretKey, Message};
use hex;

/// Sign a message using a given ECDSA private key
fn sign_message(message: &str, private_key: &[u8]) -> secp256k1::ecdsa::Signature {
    // Hash the message
    let mut hasher = Sha256::new();
    hasher.update(message.as_bytes());
    let message_hash = hasher.finalize();

    // Create secp256k1 context
    let secp = Secp256k1::new();
    
    // Create a private key object from the byte array
    let secret_key = SecretKey::from_slice(private_key)
        .expect("Invalid private key");
    
    // Create a message object from the hash
    let msg = Message::from_slice(&message_hash)
        .expect("Invalid message hash");
    
    // Sign the message hash
    let signature = secp.sign_ecdsa(&msg, &secret_key);
    
    signature
}

fn main() {
    // Test function
    let message = "Hello, world!";
    let private_key_hex = "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725";
    let private_key = hex::decode(private_key_hex)
        .expect("Invalid hex string");
    
    let signature = sign_message(message, &private_key);
    println!("{:?}", signature);
}