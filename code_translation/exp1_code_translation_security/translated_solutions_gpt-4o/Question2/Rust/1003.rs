use k256::ecdsa::{signature::Signer, signature::Verifier, SigningKey, VerifyingKey, Signature};
use sha2::{Sha256, Digest};

/// Sign a message using the NIST P-256 curve
fn sign_nist256(message: &[u8], signing_key_raw: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    // Create a signing key from the raw bytes
    let signing_key = SigningKey::from_bytes(signing_key_raw)?;
    
    // Sign the message
    let signature: Signature = signing_key.sign(message);
    
    // Return the signature as bytes
    Ok(signature.as_bytes().to_vec())
}

/// Verify a signature using the NIST P-256 curve
fn verify_nist256(message: &[u8], signature: &[u8], verifying_key_raw: &[u8]) -> bool {
    // Create a verifying key from the raw bytes
    let verifying_key = match VerifyingKey::from_sec1_bytes(verifying_key_raw) {
        Ok(key) => key,
        Err(_) => return false, // Invalid verifying key
    };

    // Parse the signature
    let signature = match Signature::from_bytes(signature) {
        Ok(sig) => sig,
        Err(_) => return false, // Invalid signature
    };

    // Verify the signature
    verifying_key.verify(message, &signature).is_ok()
}

fn main() {
    // Example usage
    let message = b"Hello, world!";
    let signing_key_raw = [/* 32 bytes of private key data */];
    let verifying_key_raw = [/* 33 or 65 bytes of public key data */];

    // Sign the message
    match sign_nist256(message, &signing_key_raw) {
        Ok(signature) => {
            println!("Signature: {:?}", signature);

            // Verify the signature
            let is_valid = verify_nist256(message, &signature, &verifying_key_raw);
            println!("Is the signature valid? {}", is_valid);
        }
        Err(e) => {
            eprintln!("Failed to sign the message: {}", e);
        }
    }
}