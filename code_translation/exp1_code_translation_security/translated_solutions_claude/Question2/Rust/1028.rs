use ecdsa::{SigningKey, VerifyingKey, Signature};
use ecdsa::signature::{Signer, Verifier};
use k256::Secp256k1;

/// Function that signs a given message using an ECDSA signing key.
/// 
/// # Arguments
/// * `signing_key` - ECDSA signing key
/// * `message` - message to be signed
/// 
/// # Returns
/// * `Signature` - the signature
pub fn sign(signing_key: &SigningKey<Secp256k1>, message: &[u8]) -> Signature<Secp256k1> {
    signing_key.sign(message)
}

/// Function that verifies a given signature using an ECDSA verification key.
/// 
/// # Arguments
/// * `verifying_key` - ECDSA verification key
/// * `message` - message to be verified
/// * `signature` - signature to be verified
/// 
/// # Returns
/// * `bool` - true if signature is valid, false otherwise
pub fn verify(
    verifying_key: &VerifyingKey<Secp256k1>, 
    message: &[u8], 
    signature: &Signature<Secp256k1>
) -> bool {
    verifying_key.verify(message, signature).is_ok()
}