use ecdsa::{SigningKey, VerifyingKey, signature::Signer, signature::Verifier};
use sha2::Sha256;
use ecdsa::elliptic_curve::sec1::FromEncodedPoint;

// Define functions for signing and verifying messages using NIST256p curve
pub fn sign_nist256(message: &[u8], signing_key_raw: &[u8]) -> Vec<u8> {
    // Load signing key from raw bytes
    let signing_key = SigningKey::from_bytes(signing_key_raw, &k256::SECP256R1);

    // Sign the message
    let signature = signing_key.sign(message, &mut Sha256::new());

    // Return the signature as a vector of bytes
    signature.to_bytes().to_vec()
}

pub fn verify_nist256(message: &[u8], signature: &[u8], verifying_key_raw: &[u8]) -> bool {
    // Load verifying key from raw bytes
    let verifying_key = VerifyingKey::from_bytes(verifying_key_raw, &k256::SECP256R1).unwrap();

    // Verify the signature
    let signature = ecdsa::Signature::from_bytes(signature).unwrap();
    verifying_key.verify(message, &signature, &mut Sha256::new()).is_ok()
}

fn main() {
    // Example usage
    let message = b"Hello, World!";
    let signing_key_raw = include_bytes!("path_to_your_signing_key.pem"); // Load your signing key
    let verifying_key_raw = include_bytes!("path_to_your_verifying_key.pem"); // Load your verifying key

    let signature = sign_nist256(message, signing_key_raw);
    let is_valid = verify_nist256(message, &signature, verifying_key_raw);

    println!("Is signature valid? {}", is_valid);
}