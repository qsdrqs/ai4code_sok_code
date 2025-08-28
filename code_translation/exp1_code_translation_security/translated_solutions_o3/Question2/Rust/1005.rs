//! A direct translation of the original Python snippet.
//!
//! Crates assumed to be available in your `Cargo.toml`:
//
//! [dependencies]
//! k256    = { version = "0.13", features = ["ecdsa"] }   // or any other curve crate you prefer
//! ecdsa   = "0.16"                                       // re-exported by `k256`, but kept for trait names
//! rand_core = "0.6"                                      // for `OsRng`
//
//! ────────────────────────────────────────────────────────
//! Equivalent to:
//
//! import ecdsa
//!
//! def sign(msg, key):
//!     vk = key.verifying_key
//!     signature = key.sign(msg)
//!     assert vk.verify(signature, msg)
//!     return signature
//!
//! def test():
//!     sk = ecdsa.SigningKey.generate()
//!     print(sign(b"message", sk))
//! ────────────────────────────────────────────────────────

use ecdsa::signature::{Signer, Verifier};
use k256::ecdsa::{Signature, SigningKey, VerifyingKey};
use rand_core::OsRng;

/// Sign a message and immediately verify the produced signature.
/// Returns the signature if verification succeeds.
fn sign(msg: &[u8], key: &SigningKey) -> Signature {
    // Produce the DER-encoded ECDSA signature
    let signature: Signature = key.sign(msg);

    // Obtain the corresponding public key
    let vk: VerifyingKey = key.verifying_key();

    // Make sure signature verification succeeds
    vk.verify(msg, &signature).expect("signature verification failed");

    signature
}

/// Smoke test that mimics the Python `test()` function.
fn main() {
    // Generate a fresh random signing key
    let sk = SigningKey::random(&mut OsRng);

    // Sign a literal message and print the signature bytes
    let sig = sign(b"message", &sk);
    println!("signature: {:?}", sig);
}