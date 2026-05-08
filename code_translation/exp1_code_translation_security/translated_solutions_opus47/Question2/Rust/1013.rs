// Cargo.toml dependencies:
// [dependencies]
// p256 = "0.13"
// rand_core = { version = "0.6", features = ["getrandom"] }

use p256::ecdsa::{signature::Signer, Signature, SigningKey};
use rand_core::OsRng;

fn sign(message: &str, key: &SigningKey) -> Signature {
    // let signing_key = SigningKey::random(&mut OsRng); // uses NIST P-256
    key.sign(message.as_bytes())
}

fn main() {
    println!("{:?}", sign("something", &SigningKey::random(&mut OsRng)));
}