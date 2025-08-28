use k256::ecdsa::{SigningKey, Signature};
use sha1::Digest;

/// Signs a message using ECDSA with SHA-1 hash.
pub fn sign_message(message: &[u8], private_key: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    // Hash the message using SHA-1
    let hash = Sha1::digest(message);
    
    // Create a signing key from the private key bytes
    let signing_key = SigningKey::from_bytes(private_key)?;
    
    // Sign the prehashed message (SHA-1 output)
    let signature = signing_key.sign_prehash(&hash)?;
    
    // Convert the signature to a vector of bytes (64 bytes: r || s)
    Ok(signature.to_vec())
}