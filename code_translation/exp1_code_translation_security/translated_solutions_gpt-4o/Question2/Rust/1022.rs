use k256::ecdsa::{SigningKey, VerifyingKey, signature::{Signer, Verifier}, Signature};
use rand_core::OsRng; // For generating secure random numbers

fn main() {
    // Generate a private key
    let signing_key = SigningKey::random(&mut OsRng);
    let verifying_key = VerifyingKey::from(&signing_key); // Derive the public key

    let message = b"My test message";

    // Sign the message
    let signature: Signature = signing_key.sign(message);

    // Verify the signature
    let is_valid = verifying_key.verify(message, &signature).is_ok();

    println!("{}", is_valid); // Prints true if the signature is valid
}