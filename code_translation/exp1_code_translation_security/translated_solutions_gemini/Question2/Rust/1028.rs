use p256::ecdsa::{SigningKey, Signature, VerifyingKey};
use ecdsa::{signature::Signer, signature::Verifier};

/// Signs a given message using an ECDSA signing key.
///
/// # Arguments
///
/// * `signing_key` - A reference to the ECDSA signing key (`p256::ecdsa::SigningKey`).
/// * `message` - The message to be signed as a byte slice.
///
/// # Returns
///
/// * An ECDSA signature (`p256::ecdsa::Signature`).
pub fn sign(signing_key: &SigningKey, message: &[u8]) -> Signature {
    // The `sign` method is provided by the `ecdsa::signature::Signer` trait.
    signing_key.sign(message)
}

/// Verifies a given signature using an ECDSA verification key.
///
/// # Arguments
///
/// * `verifying_key` - A reference to the ECDSA verification key (`p256::ecdsa::VerifyingKey`).
/// * `message` - The message to be verified as a byte slice.
/// * `signature` - A reference to the signature to be verified.
///
/// # Returns
///
/// * `true` if the signature is valid, `false` otherwise.
pub fn verify(verifying_key: &VerifyingKey, message: &[u8], signature: &Signature) -> bool {
    // The `verify` method is provided by the `ecdsa::signature::Verifier` trait.
    // It returns a `Result<(), Error>`. `is_ok()` conveniently converts this
    // to `true` on success (valid signature) and `false` on failure (invalid signature).
    verifying_key.verify(message, signature).is_ok()
}

// The test module demonstrates the usage of the functions and ensures they work correctly.
// You can run this with `cargo test`.
#[cfg(test)]
mod tests {
    use super::*;
    use rand::rngs::OsRng; // For generating a random signing key

    #[test]
    fn test_sign_and_verify_flow() {
        // 1. Generate a new random signing key.
        let signing_key = SigningKey::random(&mut OsRng);

        // 2. Derive the corresponding verification key.
        let verifying_key = signing_key.verifying_key();

        // 3. Define the message to be signed.
        let message = b"This is a test message for ECDSA.";

        // 4. Sign the message using our `sign` function.
        println!("Signing message: {:?}", String::from_utf8_lossy(message));
        let signature = sign(&signing_key, message);
        println!("Generated Signature: {:?}", signature);

        // 5. Verify the signature with the correct key and message.
        // This should return `true`.
        let is_valid = verify(&verifying_key, message, &signature);
        println!("Verification with correct message: {}", is_valid);
        assert!(is_valid);

        // 6. Attempt to verify the signature with a different message.
        // This should return `false`.
        let wrong_message = b"This is NOT the correct message.";
        let is_invalid = verify(&verifying_key, wrong_message, &signature);
        println!("Verification with wrong message: {}", is_invalid);
        assert!(!is_invalid);
        
        // 7. Attempt to verify with a different key.
        // This should also return `false`.
        let other_signing_key = SigningKey::random(&mut OsRng);
        let other_verifying_key = other_signing_key.verifying_key();
        let is_invalid_key = verify(&other_verifying_key, message, &signature);
        println!("Verification with wrong key: {}", is_invalid_key);
        assert!(!is_invalid_key);
    }
}