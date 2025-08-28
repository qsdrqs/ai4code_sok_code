use p256::ecdsa::{SigningKey, VerifyingKey, signature::{Signer, Verifier}};
use rand_core::OsRng;

fn ecdsa_key(message: &[u8]) -> (Vec<u8>, VerifyingKey) {
    // Generate a signing key
    let signing_key = SigningKey::random(&mut OsRng);
    let verifying_key = signing_key.verifying_key();

    // Sign the message
    let signature = signing_key.sign(message);

    // Verify the signature
    assert!(verifying_key.verify(message, &signature).is_ok());
    println!("Signature verified successfully!");

    (signature.to_bytes().to_vec(), verifying_key)
}

fn ecdsa_verify(signature: &[u8], message: &[u8], verifying_key: &VerifyingKey) -> bool {
    // Verify the signature
    let signature = p256::ecdsa::Signature::from_bytes(signature).expect("Invalid signature format");
    verifying_key.verify(message, &signature).is_ok()
}

fn ecdsa_genkey() -> (SigningKey, VerifyingKey) {
    // Generate a signing key
    let signing_key = SigningKey::random(&mut OsRng);
    let verifying_key = signing_key.verifying_key();
    (signing_key, verifying_key)
}

fn ecdsa_sign(signing_key: &SigningKey, message: &[u8]) -> Vec<u8> {
    // Sign the message
    let signature = signing_key.sign(message);
    signature.to_bytes().to_vec()
}

fn ecdsa_verify_key(verifying_key: &VerifyingKey, signature: &[u8], message: &[u8]) -> bool {
    // Verify the signature
    let signature = p256::ecdsa::Signature::from_bytes(signature).expect("Invalid signature format");
    verifying_key.verify(message, &signature).is_ok()
}

fn main() {
    let message = b"This is a test message";

    // Generate key pair
    let (signing_key, verifying_key) = ecdsa_genkey();

    // Print the signing key and verifying key
    println!("Signing Key: {:?}", signing_key.to_bytes());
    println!("Verifying Key: {:?}", verifying_key.to_bytes());

    // Sign the message
    let signature = ecdsa_sign(&signing_key, message);
    println!("Signature: {:?}", signature);

    // Verify the signature
    let is_valid = ecdsa_verify_key(&verifying_key, &signature, message);
    println!("Signature valid: {}", is_valid);
}