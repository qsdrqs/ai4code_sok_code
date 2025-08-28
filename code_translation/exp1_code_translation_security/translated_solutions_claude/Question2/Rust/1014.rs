//! Functions that signs a given message using a given ECDSA signing key

use sha2::{Sha256, Digest};
use p256::{ecdsa::{SigningKey, Signature, signature::Signer}, pkcs8::DecodePrivateKey};

/// Signs a given message using a given ECDSA signing key
pub fn sign_message(message: &[u8], signing_key: &SigningKey) -> Signature {
    // Hash the message
    let mut hasher = Sha256::new();
    hasher.update(message);
    let message_hash = hasher.finalize();

    // Sign the message hash
    let signature: Signature = signing_key.sign(&message_hash);

    signature
}

// Example usage and helper functions
impl SigningKey {
    /// Create a signing key from bytes (similar to Python's ecdsa usage)
    pub fn from_bytes(bytes: &[u8]) -> Result<Self, Box<dyn std::error::Error>> {
        SigningKey::from_slice(bytes).map_err(|e| e.into())
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    use p256::ecdsa::VerifyingKey;
    use p256::ecdsa::signature::Verifier;

    #[test]
    fn test_sign_message() {
        // Generate a random signing key
        let signing_key = SigningKey::random(&mut rand::thread_rng());
        let message = b"Hello, world!";
        
        // Sign the message
        let signature = sign_message(message, &signing_key);
        
        // Verify the signature
        let verifying_key = VerifyingKey::from(&signing_key);
        
        // Hash the message for verification
        let mut hasher = Sha256::new();
        hasher.update(message);
        let message_hash = hasher.finalize();
        
        assert!(verifying_key.verify(&message_hash, &signature).is_ok());
    }
}