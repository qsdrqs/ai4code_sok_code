use secp256k1::{Secp256k1, SecretKey, Message};
use secp256k1::rand::rngs::OsRng;
use sha2::{Sha256, Digest};

fn sign_message(message: &[u8], curve_name: &str) -> Result<secp256k1::ecdsa::Signature, Box<dyn std::error::Error>> {
    // Create secp256k1 context
    let secp = Secp256k1::new();
    
    // Generate a random private key
    let mut rng = OsRng::new()?;
    let secret_key = SecretKey::new(&mut rng);
    
    // Hash the message using SHA-256 (required for secp256k1)
    let mut hasher = Sha256::new();
    hasher.update(message);
    let hash = hasher.finalize();
    
    // Create message from hash
    let message = Message::from_slice(&hash)?;
    
    // Sign the message
    let signature = secp.sign_ecdsa(&message, &secret_key);
    
    Ok(signature)
}

// Alternative version that returns the signature as bytes
fn sign_message_bytes(message: &[u8], curve_name: &str) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    let signature = sign_message(message, curve_name)?;
    Ok(signature.serialize_compact().to_vec())
}

// Example usage
fn main() -> Result<(), Box<dyn std::error::Error>> {
    let message = b"Hello, World!";
    let curve = "secp256k1"; // Note: secp256k1 crate only supports secp256k1 curve
    
    let signature = sign_message(message, curve)?;
    println!("Signature: {:?}", signature);
    
    let signature_bytes = sign_message_bytes(message, curve)?;
    println!("Signature bytes: {:?}", signature_bytes);
    
    Ok(())
}

#[cfg(test)]
mod tests {
    use super::*;
    
    #[test]
    fn test_sign_message() {
        let message = b"test message";
        let result = sign_message(message, "secp256k1");
        assert!(result.is_ok());
    }
}