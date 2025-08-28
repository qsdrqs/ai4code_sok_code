use p256::{
    ecdsa::{SigningKey, VerifyingKey, Signature, signature::Signer, signature::Verifier},
    SecretKey, PublicKey,
};
use sha2::{Sha256, Digest};

fn sign_nist256(message: &[u8], signing_key_raw: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    // Create secret key from raw bytes
    let secret_key = SecretKey::from_slice(signing_key_raw)?;
    let signing_key = SigningKey::from(secret_key);
    
    // Hash the message with SHA-256
    let mut hasher = Sha256::new();
    hasher.update(message);
    let hash = hasher.finalize();
    
    // Sign the hash
    let signature: Signature = signing_key.sign(&hash);
    
    // Return signature as bytes
    Ok(signature.to_bytes().to_vec())
}

fn verify_nist256(message: &[u8], signature: &[u8], verifying_key_raw: &[u8]) -> bool {
    // Create public key from raw bytes
    let public_key = match PublicKey::from_sec1_bytes(verifying_key_raw) {
        Ok(key) => key,
        Err(_) => return false,
    };
    let verifying_key = VerifyingKey::from(public_key);
    
    // Parse signature
    let signature = match Signature::from_bytes(signature.into()) {
        Ok(sig) => sig,
        Err(_) => return false,
    };
    
    // Hash the message with SHA-256
    let mut hasher = Sha256::new();
    hasher.update(message);
    let hash = hasher.finalize();
    
    // Verify signature
    verifying_key.verify(&hash, &signature).is_ok()
}

#[cfg(test)]
mod tests {
    use super::*;
    use p256::SecretKey;
    use rand::rngs::OsRng;

    #[test]
    fn test_sign_and_verify() {
        // Generate a random key pair for testing
        let secret_key = SecretKey::random(&mut OsRng);
        let public_key = secret_key.public_key();
        
        let message = b"Hello, world!";
        let signing_key_raw = secret_key.to_bytes();
        let verifying_key_raw = public_key.to_sec1_bytes();
        
        // Sign the message
        let signature = sign_nist256(message, &signing_key_raw).unwrap();
        
        // Verify the signature
        let is_valid = verify_nist256(message, &signature, &verifying_key_raw);
        assert!(is_valid);
        
        // Test with wrong message
        let wrong_message = b"Wrong message";
        let is_valid_wrong = verify_nist256(wrong_message, &signature, &verifying_key_raw);
        assert!(!is_valid_wrong);
    }
}