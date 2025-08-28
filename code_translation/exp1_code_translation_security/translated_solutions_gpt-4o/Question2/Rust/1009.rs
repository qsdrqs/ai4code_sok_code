use p256::ecdsa::{signature::Signer, signature::Verifier, SigningKey, VerifyingKey, Signature};
use rand_core::OsRng;

fn sign(sk: &SigningKey, message: &[u8]) -> Signature {
    sk.sign(message)
}

fn main() {
    let message = b"hehehe";

    // Generate a new signing key using the P-256 curve
    let signing_key = SigningKey::random(&mut OsRng);
    let verifying_key = VerifyingKey::from(&signing_key);

    // Sign the message
    let signature = sign(&signing_key, message);

    // Verify the signature
    let is_valid = verifying_key.verify(message, &signature).is_ok();
    println!("{}", is_valid);
}