// main.rs
use rand_core::OsRng;                          // Random‐number generator
use k256::ecdsa::{
    signature::{Signer, Verifier},             // Traits for signing / verifying
    Signature, SigningKey, VerifyingKey,
};

/// Signs a given message with the provided ECDSA signing key.
///
/// Args:
///     message: input message
///     key:     ECDSA key used to sign the message
///
/// Returns:
///     The ECDSA signature for `message`.
fn sign(message: &[u8], key: &SigningKey) -> Signature {
    key.sign(message)
}

fn main() {
    // Generate a fresh SECP256k1 signing key
    let sk = SigningKey::random(&mut OsRng);
    // Derive the corresponding verifying key
    let vk = VerifyingKey::from(&sk);

    let message = b"helloworld";

    // Sign the message
    let signed_message = sign(message, &sk);

    // Verify the signature
    let is_valid = vk.verify(message, &signed_message).is_ok();
    println!("{}", is_valid);       // prints `true`
}