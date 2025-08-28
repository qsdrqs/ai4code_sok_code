// To run this code, add the following dependencies to your Cargo.toml:
// p192 = "0.13"
// ecdsa = { version = "0.16", features = ["signing"] }
// rand = "0.8"
// hex = "0.4"

// well there's an ecdsa python library but that would be cheating
// In Rust, using a well-vetted crypto crate is the standard and recommended practice.
use p192::{NistP192, SecretKey};
use ecdsa::{signature::Signer, Signature, SigningKey};
use rand::rngs::OsRng;

// what
// The RustCrypto crates provide a generic `ecdsa` crate and curve-specific
// crates like `p192`. This makes the code modular and secure.

// The following is a translation of the sample code from the Python library's guide:
/*
    // let sk = SigningKey::random(&mut OsRng); // uses NIST192p
    // let vk = sk.verifying_key();
    // let message = b"message";
    // let signature: Signature<NistP192> = sk.sign(message);
    // assert!(vk.verify(message, &signature).is_ok());
*/

/// Signs a message with the given signing key.
///
/// # Arguments
/// * `message` - The byte slice to sign.
/// * `key` - A reference to the `SigningKey` for the NistP192 curve.
///
/// # Returns
/// The ECDSA signature.
fn sign(message: &[u8], key: &SigningKey<NistP192>) -> Signature<NistP192> {
    // In the Python code, `vk` is created but not used in the function.
    // We can skip that here and directly sign the message.
    let signature = key.sign(message);
    signature
}

fn main() {
    // Generate a new random private key.
    // `OsRng` is a cryptographically secure random number generator from the OS.
    let signing_key = SigningKey::random(&mut OsRng);

    let message = b"i am a message";
    let signature = sign(message, &signing_key);

    // The signature is a struct containing the raw signature bytes.
    // We print it as a hex string for readability, which is a common practice.
    println!("{}", hex::encode(signature.to_bytes()));
}