use k256::ecdsa::{SigningKey, Signature, VerifyingKey};
use signature::{Signer, Verifier}; // Import the generic Signer and Verifier traits
use rand::rngs::OsRng; // OsRng is a cryptographically secure random number generator

/// Signs a message with the given signing key.
///
/// # Arguments
/// * `sk` - A reference to the `SigningKey` (private key).
/// * `m` - The string message to sign.
///
/// # Returns
/// The resulting `Signature`.
fn sign(sk: &SigningKey, m: &str) -> Signature {
    // The `sign` method from the `Signer` trait takes a byte slice.
    // In Rust, a string slice `&str` can be easily converted to bytes with `.as_bytes()`.
    // This is equivalent to Python's `m.encode('utf-8')`.
    sk.sign(m.as_bytes())
}

/// Demonstrates key generation, signing, and verification.
/// This function is equivalent to the Python `test()` function.
fn main() {
    // 1. Generate a new private key (SigningKey).
    // Rust's API requires an explicit random number generator.
    // `OsRng` is the recommended choice for secure key generation.
    let sk = SigningKey::random(&mut OsRng);

    // 2. Derive the public key (VerifyingKey) from the private key.
    let vk: VerifyingKey = sk.verifying_key();

    // 3. Sign the message "hello world".
    let message = "hello world";
    let signature = sign(&sk, message);

    println!("Original Message: \"{}\"", message);
    println!("Signature: {}", signature);

    // 4. Verify the signature against a tampered message.
    // This is the equivalent of the final line in the Python `test` function.
    let tampered_message = "hello worfld";
    println!("Attempting to verify with tampered message: \"{}\"", tampered_message);

    // In Rust, verification doesn't raise an exception on failure like the Python library.
    // Instead, it returns a `Result` enum.
    // - `Ok(())` indicates a successful verification.
    // - `Err(e)` indicates a failure.
    // We can check this result to see if the signature is valid.
    let verification_result = vk.verify(tampered_message.as_bytes(), &signature);

    match verification_result {
        Ok(_) => println!("Verification FAILED to detect tampering! (This should not happen)"),
        Err(_) => println!("Verification correctly FAILED for the tampered message. (This is the expected outcome)"),
    }

    // For completeness, let's also verify with the correct message.
    println!("\nAttempting to verify with original message: \"{}\"", message);
    match vk.verify(message.as_bytes(), &signature) {
        Ok(_) => println!("Verification with original message: SUCCESS"),
        Err(_) => println!("Verification FAILED with original message! (Error in logic)"),
    }
}