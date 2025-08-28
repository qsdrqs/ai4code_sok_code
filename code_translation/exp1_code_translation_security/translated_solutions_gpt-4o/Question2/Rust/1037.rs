use k256::ecdsa::{SigningKey, VerifyingKey, signature::{Signer, Verifier}};

fn main() {
    // Generate a signing key using the NIST P-384 curve
    let signing_key = SigningKey::random(&mut rand::thread_rng());
    let verifying_key = VerifyingKey::from(&signing_key);

    // Sign the message
    let message = b"testing";
    let signature = sign_message(&signing_key, message);

    // Verify the signature
    let is_valid = verifying_key.verify(message, &signature).is_ok();
    println!("{}", is_valid);
}

fn sign_message(key: &SigningKey, message: &[u8]) -> k256::ecdsa::Signature {
    key.sign(message)
}