/// Function that signs a given message using an ECDSA signing key.
fn sign(ecdsa_signing_key: &ecdsa::SigningKey, message: &[u8]) -> Result<ecdsa::Signature, ecdsa::Error> {
    // Sign the message using the ECDSA signing key
    ecdsa_signing_key.sign(message)
}

/// Function that verifies a given signature using an ECDSA verification key.
fn verify(ecdsa_verification_key: &ecdsa::VerificationKey, message: &[u8], signature: &ecdsa::Signature) -> bool {
    // Verify the signature using the ECDSA verification key
    ecdsa_verification_key.verify(message, signature).is_ok()
}