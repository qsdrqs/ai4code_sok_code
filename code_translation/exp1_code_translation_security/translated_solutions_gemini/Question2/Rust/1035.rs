use ecdsa::{Signature, SigningKey, VerifyingKey};
use p192::NistP192; // The NIST P-192 curve, equivalent to python-ecdsa's default
use signature::{Signer, Verifier}; // Imports the .sign() and .verify() methods
use rand::rngs::OsRng; // A cryptographically secure random number generator

/// Signs a message using the provided signing key.
///
/// # Arguments
/// * `msg` - The message to sign as a byte slice.
/// * `sk` - A reference to the `SigningKey`.
///
/// # Returns
/// The resulting `Signature`.
fn sign(msg: &[u8], sk: &SigningKey<NistP192>) -> Signature<NistP192> {
    // The `sign` method comes from the `Signer` trait.
    sk.sign(msg)
}

fn main() {
    // In Rust, we must be explicit about the random number generator.
    // OsRng is a good choice for cryptographic key generation.
    let mut rng = OsRng;

    // 1. Generate a signing key for the NIST P-192 curve.
    // The type SigningKey<NistP192> explicitly states which curve we are using.
    let sk: SigningKey<NistP192> = SigningKey::random(&mut rng);

    // 2. Derive the verifying (public) key from the signing key.
    let vk: VerifyingKey<NistP192> = sk.verifying_key();

    // 3. Sign and verify a message.
    let message = b"message";
    let signature = sign(message, &sk);

    // 4. Assert that the signature is valid for the message.
    // The `verify` method returns a `Result`.
    // `is_ok()` returns true on success, and `unwrap()` or `expect()` will panic on failure,
    // which is equivalent to Python's `assert`.
    assert!(vk.verify(message, &signature).is_ok());

    // You could also use .expect() for a more descriptive error message on failure.
    vk.verify(message, &signature)
        .expect("Verification failed!");

    println!("Successfully generated a key, signed a message, and verified the signature!");
}