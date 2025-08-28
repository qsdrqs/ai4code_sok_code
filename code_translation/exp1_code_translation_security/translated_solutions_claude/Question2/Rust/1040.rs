//! sign message ecdsa

use std::env;
use std::fs;
use serde_json;
use sha2::{Sha256, Digest};
use secp256k1::{Secp256k1, SecretKey, Message};
use base64;
use hex;

/// Sign message using ECDSA
fn sign_message(message: &[u8], private_key: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    let secp = Secp256k1::new();
    let secret_key = SecretKey::from_slice(private_key)?;
    
    // Hash the message (similar to what ecdsa library does internally)
    let mut hasher = Sha256::new();
    hasher.update(message);
    let message_hash = hasher.finalize();
    
    let message = Message::from_slice(&message_hash)?;
    let signature = secp.sign_ecdsa(&message, &secret_key);
    
    Ok(signature.serialize_compact().to_vec())
}

// Add to Cargo.toml:
/*
[dependencies]
secp256k1 = "0.27"
sha2 = "0.10"
serde_json = "1.0"
base64 = "0.21"
hex = "0.4"
*/