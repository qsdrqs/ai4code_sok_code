use p256::ecdsa::{
    signature::Signer,
    signature::Verifier,
    Signature, 
    SigningKey, 
    VerifyingKey
};
use sha2::{Sha256, Digest};

/// Signs a message using ECDSA with NIST P-256 and SHA-256.
///
/// # Arguments
/// * `message` - The message to sign.
/// * `signing_key_raw` - A 32-byte slice representing the raw private key.
///
/// # Returns
/// A `Result` containing the signature as a `Vec<u8>` in ASN.1 DER format,
/// or an error if the key is invalid.
pub fn sign_nist256(message: &[u8], signing_key_raw: &[u8]) -> Result<Vec<u8>, &'static str> {
    // 1. Create a signing key from the raw bytes.
    let signing_key = SigningKey::from_bytes(signing_key_raw.into())
        .map_err(|_| "Invalid signing key")?;

    // 2. Manually hash the message with SHA-256.
    // The RustCrypto `sign` method expects a pre-computed digest.
    let mut hasher = Sha256::new();
    hasher.update(message);
    let digest = hasher.finalize();

    // 3. Sign the digest.
    let signature: Signature = signing_key.sign(&digest);

    // 4. Return the signature as raw bytes (ASN.1 DER format).
    Ok(signature.to_vec())
}

/// Verifies an ECDSA signature with NIST P-256 and SHA-256.
///
/// # Arguments
/// * `message` - The message that was signed.
/// * `signature_raw` - The raw signature bytes (in ASN.1 DER format).
/// * `verifying_key_raw` - The public key in SEC1 encoded format (e.g., 65 bytes for uncompressed).
///
/// # Returns
/// `true` if the signature is valid, `false` otherwise.
pub fn verify_nist256(message: &[u8], signature_raw: &[u8], verifying_key_raw: &[u8]) -> bool {
    // The verification process can fail at multiple steps (key parsing, signature parsing,
    // or the final verification logic). We wrap it in a closure that returns a Result
    // and then check if the result is Ok, which gives a clean true/false output.
    let result: Result<(), ()> = (|| {
        // 1. Parse the verifying key from SEC1 encoded bytes.
        let verifying_key = VerifyingKey::from_sec1_bytes(verifying_key_raw)
            .map_err(|_| ())?;

        // 2. Parse the signature from its raw ASN.1 DER format.
        let signature = Signature::from_der(signature_raw)
            .map_err(|_| ())?;

        // 3. Manually hash the message with SHA-256.
        let mut hasher = Sha256::new();
        hasher.update(message);
        let digest = hasher.finalize();

        // 4. Verify the signature against the digest.
        verifying_key.verify(&digest, &signature)
            .map_err(|_| ())
    })();

    result.is_ok()
}

// --- Example Usage ---
fn main() {
    // 1. Generate a new key pair for demonstration.
    // In a real application, you would load a saved private key.
    let signing_key = SigningKey::random(&mut rand_core::OsRng);
    let signing_key_raw = signing_key.to_bytes(); // This is the 32-byte private key

    // Get the corresponding public key.
    let verifying_key = signing_key.verifying_key();
    // Serialize the public key to SEC1 uncompressed format (65 bytes).
    // This is the standard format for transport.
    let verifying_key_raw = verifying_key.to_sec1_bytes();

    // 2. Define a message to sign.
    let message = b"This is a test message for ECDSA signing.";

    println!("Original Message: {:?}", std::str::from_utf8(message).unwrap());
    println!("-------------------------------------------------");

    // 3. Sign the message.
    let signature = sign_nist256(message, &signing_key_raw).expect("Signing failed");
    println!("Generated Signature (DER): {:x?}", signature);
    println!("-------------------------------------------------");

    // 4. Verify the signature with the correct data.
    let is_valid = verify_nist256(message, &signature, &verifying_key_raw);
    println!("Verification with correct message and key: {}", is_valid);
    assert!(is_valid);

    // 5. Attempt to verify with a tampered message (should fail).
    let tampered_message = b"This is a tampered message.";
    let is_tampered_valid = verify_nist256(tampered_message, &signature, &verifying_key_raw);
    println!("Verification with tampered message: {}", is_tampered_valid);
    assert!(!is_tampered_valid);

    // 6. Attempt to verify with a different key (should fail).
    let other_key = SigningKey::random(&mut rand_core::OsRng);
    let other_verifying_key_raw = other_key.verifying_key().to_sec1_bytes();
    let is_wrong_key_valid = verify_nist256(message, &signature, &other_verifying_key_raw);
    println!("Verification with wrong key: {}", is_wrong_key_valid);
    assert!(!is_wrong_key_valid);
}