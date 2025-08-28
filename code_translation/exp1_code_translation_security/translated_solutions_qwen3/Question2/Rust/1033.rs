#[macro_use]
extern crate signature;
extern crate p256;
extern crate k256;
extern crate rand_core;

use rand_core::OsRng;
use signature::Signer;

/// Signs a message using a randomly generated ECDSA key on the specified curve.
///
/// # Arguments
///
/// * `message` - A byte slice containing the message to be signed.
/// * `curve` - A string slice indicating the curve to use. Supported values:
///             - `"p256"` for NIST P-256
///             - `"k256"` for secp256k1
///
/// # Returns
///
/// A `Vec<u8>` containing the DER-encoded ECDSA signature.
pub fn sign_message(message: &[u8], curve: &str) -> Vec<u8> {
    match curve {
        "p256" => {
            use p256::ecdsa::SigningKey;
            let mut rng = OsRng;
            let signing_key = SigningKey::random(&mut rng);
            signing_key.sign(message).to_vec()
        }
        "k256" => {
            use k256::ecdsa::SigningKey;
            let mut rng = OsRng;
            let signing_key = SigningKey::random(&mut rng);
            signing_key.sign(message).to_vec()
        }
        _ => panic!("Unsupported curve: {}", curve),
    }
}