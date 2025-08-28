use ecdsa::{SigningKey, signature::Signer};
use sha2::{Digest, Sha256};

/// Signs a given message using a given ECDSA signing key.
///
/// # Arguments
///
/// * `message` - A byte slice containing the message to be signed.
/// * `signing_key` - A reference to an ECDSA signing key.
///
/// # Returns
///
/// A `Vec<u8>` containing the DER-encoded ECDSA signature.
pub fn sign_message(message: &[u8], signing_key: &SigningKey) -> Vec<u8> {
    // Step 1: Hash the message using SHA-256
    let hash = Sha256::digest(message);

    // Step 2: Sign the hash using the provided ECDSA signing key
    let signature = signing_key.sign(&hash);

    // Step 3: Encode the signature in DER format
    signature.to_der()
}