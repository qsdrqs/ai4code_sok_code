use rsa::{RsaPrivateKey, PKCS1v15Sign};

/// Signs a message using RSA with PKCS#1 v1.5 padding and SHA-256.
///
/// # Arguments
///
/// * `msg` - A byte slice of the message to be signed.
/// * `priv_key` - A reference to the RSA private key.
///
/// # Returns
///
/// A `Vec<u8>` containing the signature.
pub fn sign(msg: &[u8], priv_key: &RsaPrivateKey) -> Vec<u8> {
    priv_key
        .sign_pkcs1v15_with_sha256(msg)
        .expect("Failed to sign message")
}