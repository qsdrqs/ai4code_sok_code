use k256::ecdsa::{signature::Signer, Signature, SigningKey};

/// Signs a given message using ECDSA signing key
///
/// # Arguments
/// * `message` - message to be signed
/// * `private_key` - private key to sign the message (32 bytes for SECP256k1)
///
/// # Returns
/// signature of the message as a `Vec<u8>`
pub fn sign_message(message: &[u8], private_key: &[u8]) -> Result<Vec<u8>, k256::ecdsa::Error> {
    let signing_key = SigningKey::from_slice(private_key)?;
    let signature: Signature = signing_key.sign(message);
    Ok(signature.to_bytes().to_vec())
}