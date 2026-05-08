use p256::ecdsa::{
    signature::{Signer, Verifier},
    Signature, SigningKey, VerifyingKey,
};

/// Function that signs a given message using a ECDSA signing key.
///
/// # Arguments
/// * `ecdsa` - ECDSA signing key
/// * `message` - message to be signed
///
/// # Returns
/// The signature
pub fn sign(ecdsa: &SigningKey, message: &[u8]) -> Signature {
    ecdsa.sign(message)
}

/// Function that verifies a given signature using a ECDSA verification key.
///
/// # Arguments
/// * `ecdsa` - ECDSA verification key
/// * `message` - message to be verified
/// * `signature` - signature to be verified
///
/// # Returns
/// `true` if signature is valid, `false` otherwise
pub fn verify(ecdsa: &VerifyingKey, message: &[u8], signature: &Signature) -> bool {
    ecdsa.verify(message, signature).is_ok()
}