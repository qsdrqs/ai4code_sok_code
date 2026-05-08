// Cargo.toml dependencies:
// [dependencies]
// k256 = { version = "0.13", features = ["ecdsa"] }

use k256::ecdsa::{signature::Signer, Signature, SigningKey};

/// Function in Rust that signs a given message using a given ECDSA signing key
fn sign_message(message: &[u8], signing_key: &SigningKey) -> Signature {
    // Sign the message using the signing key
    let signature: Signature = signing_key.sign(message);
    // Return the signature
    signature
}