use ring::signature::{EcdsaKeyPair, Signature};

/// Signs a message using ECDSA with SHA-256.
///
/// # Arguments
///
/// * `key` - A reference to an ECDSA key pair.
/// * `message` - The message to be signed.
///
/// # Returns
///
/// A `Vec<u8>` containing the ECDSA signature.
pub fn sign_using_ecdsa(key: &EcdsaKeyPair, message: &[u8]) -> Vec<u8> {
    key.sign(message)
        .expect("ECDSA signing failed")
        .as_ref()
        .to_vec()
}