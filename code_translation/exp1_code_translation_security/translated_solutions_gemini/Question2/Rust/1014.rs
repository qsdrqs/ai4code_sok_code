//! Functions that sign a given message using a given ECDSA signing key.

use k256::ecdsa::{
    signature::{PrehashSigner, SignatureEncoding},
    Signature, SigningKey,
};
use sha2::{Digest, Sha256};

/// Signs a given message using a given ECDSA (secp256k1) signing key.
///
/// This function replicates the logic of first hashing the message with SHA-256
/// and then signing the resulting digest, which is common in many ECDSA implementations.
///
/// # Arguments
///
/// * `message` - A byte slice representing the message to be signed.
/// * `signing_key` - A reference to a `k256::ecdsa::SigningKey`.
///
/// # Returns
///
/// A `Vec<u8>` containing the signature encoded in ASN.1 DER format.
fn sign_message(message: &[u8], signing_key: &SigningKey) -> Vec<u8> {
    // 1. Hash the message using SHA-256.
    // The `digest` method returns a 32-byte array representing the hash.
    let message_hash = Sha256::digest(message);

    // 2. Sign the message hash.
    // We use `sign_prehash` to sign a pre-computed digest. This is the equivalent
    // of Python's `signing_key.sign_digest()`.
    // The `unwrap()` here assumes the signing process will not fail, which is
    // generally a safe assumption with a valid key.
    let signature: Signature = signing_key.sign_prehash(&message_hash).unwrap();

    // 3. Encode the signature into DER format.
    // The `to_der()` method is provided by the `SignatureEncoding` trait.
    signature.to_der().to_vec()
}

/// Main function to demonstrate the signing process.
fn main() {
    // --- DEMONSTRATION ---

    // 1. Create a signing key.
    // WARNING: This is a fixed, insecure key for demonstration purposes only.
    // In a real application, generate a key randomly and store it securely.
    let secret_bytes = [0x42; 32];
    let signing_key = SigningKey::from_bytes(&secret_bytes).expect("32 bytes must be a valid key");

    // 2. Define the message to be signed.
    let message = b"This is a test message";

    // 3. Sign the message using our function.
    let der_signature = sign_message(message, &signing_key);

    println!("Original Message: \"{}\"", String::from_utf8_lossy(message));
    println!("Signature (DER, hex): {}", hex::encode(&der_signature));

    // 4. (Optional) Verify the signature to confirm it's valid.
    use k256::ecdsa::{signature::Verifier, VerifyingKey};

    let verifying_key = VerifyingKey::from(&signing_key);
    let signature_from_der =
        Signature::from_der(&der_signature).expect("Failed to parse DER signature");

    // The `verify` method hashes the message internally before checking the signature.
    assert!(
        verifying_key.verify(message, &signature_from_der).is_ok(),
        "Signature verification failed!"
    );

    println!("\nSignature verified successfully!");
}