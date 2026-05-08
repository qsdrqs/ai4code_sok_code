//! Functions that signs a given message using a given ECDSA signing key

use k256::ecdsa::{signature::hazmat::PrehashSigner, Error, Signature, SigningKey};
use sha2::{Digest, Sha256};

/// Signs a given message using a given ECDSA signing key
pub fn sign_message(message: &[u8], signing_key: &SigningKey) -> Result<Vec<u8>, Error> {
    // Hash the message
    let message_hash = Sha256::digest(message);

    // Sign the message hash
    let signature: Signature = signing_key.sign_prehash(&message_hash)?;

    // Return the DER-encoded signature
    Ok(signature.to_der().as_bytes().to_vec())
}