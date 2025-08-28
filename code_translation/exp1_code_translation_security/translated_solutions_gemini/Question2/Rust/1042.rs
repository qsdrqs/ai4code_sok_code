use k256::ecdsa::{signature::Signer, Signature, SigningKey};

/// Signs a given message using an ECDSA private key on the SECP256k1 curve.
///
/// # Arguments
///
/// * `message` - A byte slice representing the message to be signed.
/// * `private_key_bytes` - A 32-byte slice representing the private key.
///
/// # Returns
///
/// A `Result` containing the ECDSA `Signature` on success, or an `ecdsa::Error`
/// if the private key is invalid or signing fails.
pub fn sign_message(
    message: &[u8],
    private_key_bytes: &[u8],
) -> Result<Signature, k256::ecdsa::Error> {
    // In Rust, we first create a SigningKey object from the raw private key bytes.
    // The `from_slice` method will return an error if the key is not valid (e.g., wrong length),
    // which we propagate with the `?` operator.
    let signer = SigningKey::from_slice(private_key_bytes)?;

    // The `try_sign` method performs the signing. It also returns a Result.
    // This is equivalent to `signer.sign(message)` in the Python code.
    let signature: Signature = signer.try_sign(message)?;

    Ok(signature)
}

// --- Example Usage ---
// The following code demonstrates how to use the function.
// It is not part of the direct translation but shows a complete, runnable example.
fn main() {
    // 1. Generate a new random private key for demonstration purposes.
    // In a real application, you would load your private key from a secure source.
    let private_key = SigningKey::random(&mut rand_core::OsRng);
    let private_key_bytes = private_key.to_bytes(); // Get the raw bytes

    // 2. Define a message to sign.
    let message = b"This is a test message for ECDSA signing.";

    println!("Original Message: {}", String::from_utf8_lossy(message));
    println!("Private Key (hex): {}", hex::encode(private_key_bytes));

    // 3. Call the function to sign the message.
    match sign_message(message, &private_key_bytes) {
        Ok(signature) => {
            // The signature is a struct, not just raw bytes.
            // We can convert it to bytes for storage or transmission.
            println!("Signature (DER-encoded hex): {}", hex::encode(signature.to_der()));
            println!("Signature is valid!");

            // Optional: Verify the signature to confirm it works
            use k256::ecdsa::signature::Verifier;
            let verifying_key = private_key.verifying_key();
            assert!(verifying_key.verify(message, &signature).is_ok());
        }
        Err(e) => {
            eprintln!("Error signing message: {}", e);
        }
    }
}

// To run the example, you'll also need these dev-dependencies in Cargo.toml:
// [dev-dependencies]
// rand_core = "0.6"
// hex = "0.4"