// Cargo.toml dependencies:
// p256 = { version = "0.10", features = ["ecdsa"] }
// ecdsa = { version = "0.13", features = ["p256"] }

use ecdsa::{SigningKey, signature::Signer};
use p256::NistP256;

/// Signs the fixed message `b"message"` using the provided ECDSA private key.
/// 
/// # Arguments
/// - `_msg`: Ignored in this implementation, as in the original Python code.
/// - `key`: A reference to the ECDSA signing key (NIST P-256 curve).
/// 
/// # Returns
/// A `Vec<u8>` containing the DER-encoded ECDSA signature.
pub fn sign(_msg: &[u8], key: &SigningKey<NistP256>) -> Vec<u8> {
    key.sign(b"message").as_bytes().to_vec()
}