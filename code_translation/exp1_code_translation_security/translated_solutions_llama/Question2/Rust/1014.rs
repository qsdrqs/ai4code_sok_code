//! Functions that signs a given message using a given ECDSA signing key

use ecdsa::{SigningKey, Signature};
use sha2::{Sha256, Digest};

/// Signs a given message using a given ECDSA signing key
///
/// # Arguments
///
/// * `message`: The message to be signed
/// * `signing_key`: The ECDSA signing key
///
/// # Returns
///
/// The signature of the message
pub fn sign_message(message: &[u8], signing_key: &SigningKey) -> Signature {
    // Hash the message
    let message_hash = Sha256::digest(message);

    // Sign the message hash
    signing_key.sign(&message_hash)
}

fn main() {
    // Example usage
    let signing_key = SigningKey::random(&mut rand::thread_rng());
    let message = b"Hello, World!";
    let signature = sign_message(message, &signing_key);
    println!("{:?}", signature);
}