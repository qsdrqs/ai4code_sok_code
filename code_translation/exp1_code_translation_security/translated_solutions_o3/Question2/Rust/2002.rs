// src/main.rs
//
// A direct translation of the Python snippet into Rust.
//
// *All the crates used below (`p256`, `ecdsa`, `rand_core`) are expected to be
//  available to the build system; therefore their exact versions / features are
//  not shown here.*

use p256::ecdsa::{SigningKey, VerifyingKey, Signature};
use ecdsa::signature::{Signer, Verifier};
use rand_core::OsRng;

/// Sign the UTF-8 encoded contents of `m` with the given signing key.
fn sign(sk: &SigningKey, m: &str) -> Signature {
    sk.sign(m.as_bytes())
}

fn test() {
    // Generate a fresh signing key (private key).
    let sk = SigningKey::random(&mut OsRng);

    // Derive the corresponding verifying key (public key).
    let vk = VerifyingKey::from(&sk);

    // Sign the message “hello world”.
    let signature = sign(&sk, "hello world");

    // Attempt to verify the signature against a *different* message.
    let verified = vk
        .verify("hello worfld".as_bytes(), &signature)
        .is_ok();        // Convert Result<(), _> to a bool.

    println!("{verified}");
}

fn main() {
    test();
}