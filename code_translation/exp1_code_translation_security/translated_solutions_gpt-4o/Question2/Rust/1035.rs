use p256::ecdsa::{SigningKey, VerifyingKey, signature::{Signer, Verifier}};
use rand_core::OsRng; // For secure random number generation

fn main() {
    // Generate a signing key (private key) using the NIST P-256 curve
    let signing_key = SigningKey::random(&mut OsRng);
    let verifying_key = VerifyingKey::from(&signing_key);

    // Message to be signed
    let message = b"message";

    // Sign the message
    let signature = signing_key.sign(message);

    // Verify the signature
    assert!(verifying_key.verify(message, &signature).is_ok());
    println!("Signature verified successfully!");
}