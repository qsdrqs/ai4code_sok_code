// Cargo.toml:
// [dependencies]
// p192 = { version = "0.13", features = ["ecdsa"] }
// rand_core = { version = "0.6", features = ["std"] }

use p192::ecdsa::{
    signature::{Signer, Verifier},
    Signature, SigningKey,
};
use rand_core::OsRng;

fn sign(msg: &[u8], sk: &SigningKey) -> Signature {
    sk.sign(msg)
}

fn main() {
    let sk = SigningKey::random(&mut OsRng); // uses NIST P-192
    let vk = sk.verifying_key();

    assert!(vk.verify(b"message", &sign(b"message", &sk)).is_ok());
}