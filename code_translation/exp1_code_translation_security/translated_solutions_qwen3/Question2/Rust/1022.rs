use p256::ecdsa::{SigningKey, VerifyingKey};
use signature::{Signer, Verifier};
use rand::rngs::ThreadRng;
use rand::RngCore;

fn main() {
    // Step 1: Generate a random private key
    let mut rng = rand::thread_rng();
    let signing_key = SigningKey::random(&mut rng);

    // Step 2: Derive the public key
    let verifying_key = signing_key.verifying_key();

    // Step 3: Define the message to sign
    let message = b"My test message";

    // Step 4: Sign the message
    let signature = signing_key.sign(message);

    // Step 5: Verify the signature
    let is_valid = verifying_key.verify(message, &signature).is_ok();

    // Output the result
    println!("{}", is_valid);
}