// Import necessary traits and types from the crates.
// The `Signer` and `Verifier` traits are the core of the ecdsa crate's API.
use ecdsa::{signature::Signer, signature::Verifier, Signature, SigningKey};
use p384::NistP384; // The specific curve we are using (equivalent to NIST384p)
use rand::thread_rng; // A random number generator

fn main() {
    // 1. Generate a private signing key.
    // We use a cryptographically secure random number generator.
    let mut rng = thread_rng();
    let sk: SigningKey<NistP384> = SigningKey::random(&mut rng);

    // 2. Derive the public verifying key from the private key.
    let vk = sk.verifying_key();

    // Define the message to be signed.
    // In Rust, a byte string literal is written as b"..."
    let message = b"testing";

    // 3. Sign the message with the private key.
    let signature = sign_message(&sk, message);

    // 4. Verify the signature with the public key.
    // The `verify` method returns a `Result`. `Ok(())` on success, `Err` on failure.
    // We use `is_ok()` to convert this result into a boolean (`true` or `false`),
    // matching the behavior of the Python `print(vk.verify(...))`
    let is_valid = vk.verify(message, &signature).is_ok();

    // Print the boolean result.
    println!("{}", is_valid);
}

/// Signs a message with the given private key.
///
/// # Arguments
/// * `key` - A reference to the `SigningKey` (private key).
/// * `message` - The byte slice `&[u8]` to sign.
///
/// # Returns
/// The resulting `Signature`.
fn sign_message(key: &SigningKey<NistP384>, message: &[u8]) -> Signature<NistP384> {
    // The `sign` method comes from the `Signer` trait.
    key.sign(message)
}