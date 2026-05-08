// Add the following dependencies to your Cargo.toml:
// [dependencies]
// k256 = { version = "0.13", features = ["ecdsa"] }
// sha2 = "0.10"
// hex = "0.4"

//! Sign a given message using a given ECDSA signing key

use k256::ecdsa::{signature::hazmat::PrehashSigner, Signature, SigningKey};
use sha2::{Digest, Sha256};

/// Sign a message using a given ECDSA private key
fn sign_message(message: &str, private_key: &[u8]) -> Signature {
    // Hash the message
    let message_hash = Sha256::digest(message.as_bytes());

    // Create a new ECDSA private key object
    let signing_key = SigningKey::from_slice(private_key)
        .expect("Invalid private key");

    // Sign the message hash
    let signature: Signature = signing_key
        .sign_prehash(&message_hash)
        .expect("Failed to sign message");

    signature
}

/// Test function
fn main() {
    let message = "Hello, world!";
    let private_key = hex::decode("18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725")
        .expect("Invalid hex string");
    let signature = sign_message(message, &private_key);
    println!("{}", hex::encode(signature.to_bytes()));
}