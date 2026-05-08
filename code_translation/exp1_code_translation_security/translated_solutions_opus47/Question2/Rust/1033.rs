// Cargo.toml dependencies:
// [dependencies]
// p256 = { version = "0.13", features = ["ecdsa"] }
// rand_core = { version = "0.6", features = ["getrandom"] }

use p256::ecdsa::{signature::Signer, Signature, SigningKey};
use rand_core::OsRng;

fn sign_message(message: &[u8]) -> Signature {
    // Generate a random private key
    let signing_key = SigningKey::random(&mut OsRng);
    // Get the public key
    let _verifying_key = signing_key.verifying_key();
    // Sign the message
    let signature: Signature = signing_key.sign(message);
    signature
}

fn main() {
    let message = b"Hello, world!";
    let signature = sign_message(message);
    println!("Signature: {:?}", signature);
}