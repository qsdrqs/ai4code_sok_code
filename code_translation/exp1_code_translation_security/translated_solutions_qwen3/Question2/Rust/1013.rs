use ecdsa::{SigningKey, signature::Signer};
use p192::NistP192;
use rand::prelude::*;
use sha1::Digest;

fn sign(message: &str, key: &SigningKey<NistP192>) -> Vec<u8> {
    // Hash the message using SHA-1
    let digest = Sha1::digest(message.as_bytes());
    // Sign the precomputed digest
    let signature = key.sign_digest(&digest);
    // Return the DER-encoded signature as a Vec<u8>
    signature.as_ref().to_vec()
}

fn main() {
    // Initialize a random number generator
    let mut rng = rand::thread_rng();

    // Generate a new private key using NIST P-192
    let private_key = SigningKey::<NistP192>::random(&mut rng);

    // Sign the message "something"
    let sig = sign("something", &private_key);

    // Print the signature bytes
    println!("Signature: {:?}", sig);
}