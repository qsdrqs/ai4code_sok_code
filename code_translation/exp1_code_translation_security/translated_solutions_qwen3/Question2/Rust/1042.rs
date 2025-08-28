use k256::ecdsa::SigningKey;
use signature::Signer;

/// Signs a given message using ECDSA with the SECP256k1 curve.
///
/// # Arguments
///
/// * `message` - A byte slice representing the message to be signed.
/// * `private_key` - A byte slice representing the 32-byte private key.
///
/// # Returns
///
/// A `Vec<u8>` containing the DER-encoded ECDSA signature.
pub fn sign_message(message: &[u8], private_key: &[u8]) -> Vec<u8> {
    let signer = SigningKey::from_bytes(private_key)
        .expect("Invalid private key: must be a 32-byte scalar");
    let signature = signer.sign(message);
    signature.as_ref().to_vec()
}