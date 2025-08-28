// -----------------------------------------------------------------------------
// ECDSA helper functions
// -----------------------------------------------------------------------------

// All necessary dependencies will be injected by the build system, but these
// are the typical `use` lines you will need.  They rely on the `k256` crate,
// which provides an implementation of the secp256k1 (a.k.a. K-256) curve.
//
// k256 = { version = "0.13", features = ["ecdsa"] }

use k256::ecdsa::{
    signature::{Signer, Verifier},
    Signature, SigningKey, VerifyingKey,
};

/// Signs the supplied message with the given ECDSA signing key.
///
/// # Arguments
/// * `signing_key` – The ECDSA private (signing) key.
/// * `message`     – The message bytes to be signed.
///
/// # Returns
/// A `k256::ecdsa::Signature` representing the ECDSA signature.
pub fn sign(signing_key: &SigningKey, message: &[u8]) -> Signature {
    signing_key.sign(message)
}

/// Verifies the supplied ECDSA signature against the message and verification key.
///
/// # Arguments
/// * `verifying_key` – The ECDSA public (verification) key.
/// * `message`       – The original message bytes.
/// * `signature`     – The signature to be verified.
///
/// # Returns
/// `true` if the signature is valid, `false` otherwise.
pub fn verify(verifying_key: &VerifyingKey, message: &[u8], signature: &Signature) -> bool {
    verifying_key.verify(message, signature).is_ok()
}