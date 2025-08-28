use secp256k1::{Secp256k1, SecretKey, Message};
use sha2::{Sha256, Digest};

/// Sign a message using a given ECDSA signing key.
fn sign(message: &[u8], private_key: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    // Create a new secp256k1 context
    let secp = Secp256k1::new();
    
    // Create a secret key from the private key bytes
    let secret_key = SecretKey::from_slice(private_key)?;
    
    // Hash the message using SHA-256 (ECDSA typically requires a hash)
    let mut hasher = Sha256::new();
    hasher.update(message);
    let message_hash = hasher.finalize();
    
    // Create a Message object from the hash
    let msg = Message::from_slice(&message_hash)?;
    
    // Sign the message hash using the secret key
    let signature = secp.sign_ecdsa(&msg, &secret_key);
    
    // Return the signature as bytes
    Ok(signature.serialize_compact().to_vec())
}

// Example usage:
#[cfg(test)]
mod tests {
    use super::*;
    
    #[test]
    fn test_sign() {
        // Example private key (32 bytes for secp256k1)
        let private_key = [1u8; 32];
        let message = b"Hello, World!";
        
        match sign(message, &private_key) {
            Ok(signature) => {
                println!("Signature length: {}", signature.len());
                println!("Signature: {:?}", signature);
            }
            Err(e) => {
                println!("Error: {}", e);
            }
        }
    }
}