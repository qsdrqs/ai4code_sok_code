// sign_message.rs
//
// Equivalent of the Python example shown below:
//
//     sk = SigningKey.from_string(private_key, curve=SECP256k1)
//     signature = sk.sign(message)
//
// Uses the `k256` crate for “secp256k1” ECDSA operations.
//
// Cargo.toml (dependencies you’ll get):
// k256        = "0.13"     # or whichever version is supplied
// hex-literal = "0.4"      # only used in the test-module below
//
// ────────────────────────────────────────────────────────────────────────────

use k256::ecdsa::{
    signature::{Signer, Signature as _}, // traits
    SigningKey,
};

/// Sign `message` with a 32-byte secp256k1 private key.
///
/// Returns the raw DER-encoded ECDSA signature bytes (same as
/// `SigningKey.sign()` in the Python snippet).
pub fn sign_message(message: &[u8], private_key: &[u8]) -> Vec<u8> {
    // Python’s `SigningKey.from_string()` panics when the key length is wrong;
    // we keep the behaviour here for a like-for-like port.
    let signing_key = SigningKey::from_bytes(private_key)
        .expect("`private_key` must be a 32-byte secp256k1 secret");

    let signature = signing_key.sign(message);
    signature.as_bytes().to_vec()
}

// ────────────────────────────────────────────────────────────────────────────
// Optional quick-check demonstrating usage
// Run with `cargo test`
#[cfg(test)]
mod tests {
    use super::*;
    use hex_literal::hex;

    #[test]
    fn test_sign_message() {
        // Example 32-byte private key
        let priv_key = hex!("1e99423a4ed27608a15a2616f8ec55b42b13d564a029a0910d4fbea2f7b2b698");
        let msg = b"hello world";

        let sig = sign_message(msg, &priv_key);
        assert!(!sig.is_empty());
    }
}