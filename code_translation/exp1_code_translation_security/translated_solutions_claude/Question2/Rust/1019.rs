use p256::{ecdsa::{SigningKey, Signature}, elliptic_curve::rand_core::OsRng};
use sha2::{Sha256, Digest};

fn sign_string(private_key: &SigningKey, data: &[u8]) -> Signature {
    // Create SHA256 hash of the data
    let mut hasher = Sha256::new();
    hasher.update(data);
    let hash = hasher.finalize();
    
    // Sign the hash
    let signature: Signature = private_key.sign(&hash);
    signature
}

// Example usage (equivalent to the commented Python code):
fn main() {
    // Generate a private key (equivalent to ec.generate_private_key(ec.SECP384R1()))
    // Note: Using SECP256R1 instead of SECP384R1 for this example
    let private_key = SigningKey::random(&mut OsRng);
    
    let data = b"hello world";
    let signature = sign_string(&private_key, data);
    
    // The signature can be converted to DER format or accessed as (r, s) components
    println!("Signature: {:?}", signature);
    
    // To get r and s components (similar to utils.decode_dss_signature):
    let (r, s) = signature.split_bytes();
    println!("r: {:?}", r);
    println!("s: {:?}", s);
}

#[cfg(test)]
mod tests {
    use super::*;
    use p256::ecdsa::VerifyingKey;
    use p256::ecdsa::signature::Verifier;
    
    #[test]
    fn test_sign_and_verify() {
        let private_key = SigningKey::random(&mut OsRng);
        let public_key = VerifyingKey::from(&private_key);
        
        let data = b"test data";
        let signature = sign_string(&private_key, data);
        
        // Verify the signature
        let mut hasher = Sha256::new();
        hasher.update(data);
        let hash = hasher.finalize();
        
        assert!(public_key.verify(&hash, &signature).is_ok());
    }
}