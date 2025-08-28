use rsa::{RsaPrivateKey, pkcs1v15::{SigningKey, Signature}};
use sha2::{Sha256, Digest};
use rsa::signature::{Signer, SignatureEncoding};

/// Sign a message using RSA PKCS#1 v1.5 with SHA-256
fn sign(msg: &[u8], priv_key: &RsaPrivateKey) -> Vec<u8> {
    // Create a signing key from the private key
    let signing_key = SigningKey::<Sha256>::new(priv_key.clone());
    
    // Sign the message (hashing is handled internally)
    let signature = signing_key.sign(msg);
    
    // Return the signature as bytes
    signature.to_bytes().as_ref().to_vec()
}

// Example usage (you would need to generate or load a private key)
#[cfg(test)]
mod tests {
    use super::*;
    use rsa::RsaPrivateKey;
    use rand::rngs::OsRng;

    #[test]
    fn test_sign() {
        let mut rng = OsRng;
        let bits = 2048;
        let priv_key = RsaPrivateKey::new(&mut rng, bits).expect("failed to generate a key");
        
        let message = b"Hello, world!";
        let signature = sign(message, &priv_key);
        
        assert!(!signature.is_empty());
        println!("Signature length: {}", signature.len());
    }
}