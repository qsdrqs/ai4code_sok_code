// Import necessary dependencies
use ecdsa::{SigningKey, VerifyingKey};
use ecdsa::serde::pkcs8;
use serde::de::DeserializeOwned;
use std::convert::TryFrom;

// Define a function to sign a message using a given ECDSA signing key
fn sign_message(message: &[u8], signing_key: &SigningKey) -> Vec<u8> {
    // Sign the message using the signing key
    let signature = signing_key.sign(message);
    // Return the signature
    signature.to_bytes().to_vec()
}

// Example usage
fn main() {
    // Generate a new ECDSA signing key (for demonstration purposes only)
    let signing_key = SigningKey::random(&mut rand::thread_rng());

    // Define a message to be signed
    let message = b"Hello, World!";

    // Sign the message
    let signature = sign_message(message, &signing_key);

    // Print the signature
    println!("{:?}", signature);
}