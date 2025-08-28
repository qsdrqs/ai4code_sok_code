// Cargo.toml dependencies:
// [dependencies]
// p256 = { version = "0.9", features = ["ecdsa"] }
// sha1 = "0.10"

use p256::ecdsa::{SigningKey, Signature};
use sha1::Digest;

/// Signs the literal string "message" using the provided ECDSA private key.
/// 
/// # Arguments
/// 
/// * `message` - Ignored in this implementation.
/// * `key` - A reference to an ECDSA private key.
/// 
/// # Returns
/// 
/// A `Vec<u8>` containing the DER-encoded ECDSA signature of the SHA-1 hash of "message".
pub fn sign_message(message: &[u8], key: &SigningKey) -> Vec<u8> {
    // Compute SHA-1 hash of the literal "message"
    let hash = Sha1::digest(b"message");

    // Sign the prehashed value
    let signature = key
        .sign_prehash(&hash)
        .expect("ECDSA signing failed");

    // Return the DER-encoded signature as a byte vector
    signature.to_der().to_vec()
}