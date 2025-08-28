use p256::ecdsa::{SigningKey, signature::Signer};
use sha2::{Digest, Sha256};

fn sign_using_ecdsa(key: &SigningKey, message: &[u8]) -> Vec<u8> {
    // Hash the message using SHA256
    let hashed_message = Sha256::digest(message);

    // Sign the hashed message using the ECDSA signing key
    let signature = key.sign(&hashed_message);

    // Return the signature as a byte vector
    signature.as_ref().to_vec()
}