use p256::ecdsa::{Signature, SigningKey, VerifyingKey};

/// Function that signs a given message using an ECDSA signing key.
pub fn sign(signing_key: &SigningKey, message: &[u8]) -> Vec<u8> {
    signing_key.sign(message).as_bytes().to_vec()
}

/// Function that verifies a given signature using an ECDSA verification key.
pub fn verify(verifying_key: &VerifyingKey, message: &[u8], signature_bytes: &[u8]) -> bool {
    match Signature::try_from(signature_bytes) {
        Ok(signature) => verifying_key.verify(message, &signature).is_ok(),
        Err(_) => false,
    }
}