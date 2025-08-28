use p256::ecdsa::{SigningKey, VerifyingKey, Signature};
use p256::ecdsa::signature::{Signer, Verifier};

/// Signs a message using the NIST P-256 curve and SHA-256.
///
/// # Arguments
/// - `message`: The message to sign.
/// - `signing_key_raw`: A 32-byte raw private key.
///
/// # Returns
/// A DER-encoded ECDSA signature as a `Vec<u8>`.
pub fn sign_nist256(message: &[u8], signing_key_raw: &[u8]) -> Vec<u8> {
    let key_bytes: [u8; 32] = signing_key_raw.try_into()
        .expect("Signing key must be exactly 32 bytes");

    let signing_key = SigningKey::from_bytes(&key_bytes)
        .expect("Invalid signing key");

    let signature = signing_key.sign(message);
    signature.to_der().as_bytes().to_vec()
}

/// Verifies a message signature using the NIST P-256 curve and SHA-256.
///
/// # Arguments
/// - `message`: The message to verify.
/// - `signature`: A DER-encoded ECDSA signature.
/// - `verifying_key_raw`: A SEC1-encoded public key (65 bytes, starting with 0x04).
///
/// # Returns
/// `true` if the signature is valid, `false` otherwise.
pub fn verify_nist256(message: &[u8], signature: &[u8], verifying_key_raw: &[u8]) -> bool {
    let verifying_key = match VerifyingKey::from_sec1_bytes(verifying_key_raw) {
        Ok(key) => key,
        Err(_) => return false,
    };

    let sig = match Signature::try_from(signature) {
        Ok(s) => s,
        Err(_) => return false,
    };

    verifying_key.verify(message, &sig).is_ok()
}