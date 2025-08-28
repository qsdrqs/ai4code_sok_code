// ------ Crate imports ------
// k256 gives us a pure-Rust implementation of the secp256k1 (a.k.a. “K-256”) curve
// together with an ECDSA implementation that is compatible with the Python ecdsa-library.
use k256::{
    ecdsa::{signature::Signer, Signature, SigningKey},
    elliptic_curve::SecretKey,
};

/// Signs a message with an ECDSA/secp256k1 private key
///
/// * `message`      – raw message bytes to be signed
/// * `private_key`  – 32-byte secp256k1 secret key
///
/// Returns the DER-encoded signature (same format that `ecdsa.SigningKey.sign`
/// produces in Python).
pub fn sign_message(
    message: &[u8],
    private_key: &[u8],
) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    // 1. Turn the raw key bytes into a `SigningKey`
    let secret = SecretKey::from_be_bytes(private_key)?;
    let signing_key: SigningKey = secret.into();

    // 2. Sign the message
    let signature: Signature = signing_key.sign(message);

    // 3. Return the DER-encoded signature so that it is byte-for-byte
    //    compatible with the Python implementation
    Ok(signature.to_der().as_bytes().to_vec())
}