use ecdsa::{signature::Signer, signature::Verifier, Signature, SigningKey};
use k256::Secp256k1; // The specific curve we'll be using (same as python-ecdsa default)
use rand::rngs::OsRng; // A cryptographically secure random number generator

/// Signs a message with a given private key and verifies the signature.
///
/// # Arguments
///
/// * `msg` - The message to sign as a byte slice.
/// * `key` - A reference to the `SigningKey` for the Secp256k1 curve.
///
/// # Returns
///
/// The generated `Signature`.
///
/// # Panics
///
/// This function will panic if the verification step fails, which indicates a
/// critical error in the underlying cryptographic library.
fn sign(msg: &[u8], key: &SigningKey<Secp256k1>) -> Signature {
    // Get the verifying key (public key) from the signing key.
    // In Rust, this is a method call, not a property access.
    let vk = key.verifying_key();

    // Sign the message. The `sign` method is provided by the `Signer` trait.
    let signature: Signature = key.sign(msg);

    // Verify the signature to ensure correctness.
    // The `verify` method is provided by the `Verifier` trait and returns a `Result`.
    // We use `is_ok()` and `assert!` to replicate the Python `assert`.
    assert!(
        vk.verify(msg, &signature).is_ok(),
        "Signature verification failed!"
    );

    signature
}

/// The main function, equivalent to the Python `test` function.
fn main() {
    // Generate a new random signing key (private key).
    // We need a mutable reference to a cryptographically secure random number generator.
    let sk = SigningKey::random(&mut OsRng);

    // Define the message to be signed.
    let msg = b"message";

    // Call the sign function.
    let signature = sign(msg, &sk);

    // Print the signature. Signatures are binary data, so we print them as a
    // hexadecimal string for readability.
    println!("Message: {}", String::from_utf8_lossy(msg));
    println!("Signature (hex): {}", hex::encode(signature.to_bytes()));
}