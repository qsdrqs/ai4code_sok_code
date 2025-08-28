// Rust translation of the original Python snippet.
//
// Cargo.toml will have (and is assumed to already contain) something like:
//
// [dependencies]
// k256        = { version = "0.13", features = ["ecdsa"] }   # secp256k1 curve
// rand_core   = "0.6"                                        # for OsRng
// hex         = "0.4"                                        # only used in the demo `main`
//
// ────────────────────────────────────────────────────────────────
// The code below mirrors, as closely as possible, what the Python
// version does: it generates a fresh (random) ECDSA signing key
// each time the function is called, derives the associated
// verifying key (public key), signs the supplied message and
// returns the resulting signature bytes.
// ────────────────────────────────────────────────────────────────

use k256::ecdsa::{signature::Signer, Signature, SigningKey};
use rand_core::OsRng;

/// Sign `message` with a freshly–generated secp256k1 ECDSA key.
///
/// Returns the DER-encoded signature bytes (which is what the
/// `ecdsa` Python package returns by default).
pub fn sign_message(message: &[u8]) -> Vec<u8> {
    // 1. Generate a random private key (signing key).
    let signing_key = SigningKey::random(&mut OsRng);

    // 2. Obtain the corresponding public (verifying) key.
    //    We compute it only to mirror the Python example.
    let _verifying_key = signing_key.verifying_key();

    // 3. Sign the message.
    let signature: Signature = signing_key.sign(message);

    // 4. Return the raw bytes of the signature.
    signature.as_ref().to_vec()
}

// -----------------------------------------------------------------------------
// Small demo to illustrate usage (can be removed in production).
// -----------------------------------------------------------------------------
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_signs() {
        let msg = b"Hello, Rust!";
        let sig = sign_message(msg);

        // The signature is variable-length DER, but it must not be empty.
        assert!(!sig.is_empty());
    }
}

// Optional `main` for a quick manual run.
fn main() {
    let msg = b"Hello from Rust!";
    let sig = sign_message(msg);

    println!("Signature (hex): {}", hex::encode(sig));
}