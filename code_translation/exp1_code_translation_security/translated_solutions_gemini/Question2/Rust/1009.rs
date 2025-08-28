// All dependencies are provided below, as they would be in a Cargo.toml file.
/*
[dependencies]
p384 = { version = "0.13", features = ["ecdsa"] }
signature = "2.2"
rand = "0.8"
*/

use p384::{
    ecdsa::{Signature, VerifyingKey},
    SigningKey,
};
use signature::{Signer, Verifier}; // Import the generic Signer and Verifier traits
use rand::rngs::OsRng; // Import a cryptographically secure random number generator

/// Signs a message with a given P-384 signing key.
///
/// # Arguments
/// * `sk` - A reference to the P-384 signing key.
/// * `message` - The byte slice of the message to sign.
///
/// # Returns
/// The resulting ECDSA signature.
fn sign(sk: &SigningKey, message: &[u8]) -> Signature {
    // The `sign` method comes from the `Signer` trait.
    // It takes the message and returns a `Signature` type.
    sk.sign(message)
}

fn main() {
    // The message to be signed, as a byte slice.
    // This is the equivalent of Python's b"hehehe".
    let message = b"hehehe";

    // Generate a new private/signing key for the NIST P-384 curve.
    // In Rust, we must explicitly provide a source of randomness.
    // `OsRng` is a secure choice that uses the operating system's entropy source.
    let sk = SigningKey::generate(&mut OsRng);

    // Call our sign function to create the signature.
    // We pass the signing key by reference.
    let sig = sign(&sk, message);

    // In Rust, we first get the verifying key from the signing key.
    let vk: VerifyingKey = sk.verifying_key();

    // The `verify` method is defined in the `Verifier` trait.
    // It returns a `Result`: `Ok(())` on successful verification,
    // and `Err` on failure.
    // We can use `.is_ok()` to get a boolean `true`/`false` result,
    // mirroring the Python example's output.
    let is_signature_valid = vk.verify(message, &sig).is_ok();

    // Print the boolean result. This will print "true".
    println!("{}", is_signature_valid);
}