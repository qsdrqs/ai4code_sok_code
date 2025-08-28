use ecdsa::{SigningKey, VerifyingKey};
use ecdsa::signature::{Signer, Signature};
use sha2::{Sha256, Digest};
use std::str::FromStr;

/// Signs a message with a private key.
///
/// # Arguments
///
/// * `msg`: The message to be signed.
/// * `priv_key`: The private key used for signing.
///
/// # Returns
///
/// The signature of the message.
fn sign(msg: &str, priv_key: &str) -> Result<Vec<u8>, ecdsa::Error> {
    // Parse private key from string
    let private_key = SigningKey::from_str(priv_key)?;

    // Create a new SHA-256 digest
    let mut digest = Sha256::new();
    digest.update(msg.as_bytes());

    // Sign the digest
    let signature = private_key.sign(&digest.finalize());

    // Return the signature as a vector of bytes
    Ok(signature.to_bytes().to_vec())
}

fn main() {
    // Example usage
    let msg = "Hello, World!";
    let priv_key = "your_private_key_here"; // Replace with your private key

    match sign(msg, priv_key) {
        Ok(signature) => println!("Signature: {:?}", signature),
        Err(err) => println!("Error: {}", err),
    }
}