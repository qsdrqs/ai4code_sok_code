// Cargo.toml dependencies:
// [dependencies]
// p384 = { version = "0.13", features = ["ecdsa"] }
// rand_core = { version = "0.6", features = ["getrandom"] }

use p384::ecdsa::signature::{Signer, Verifier};
use p384::ecdsa::{Signature, SigningKey};
use rand_core::OsRng;

fn sign(sk: &SigningKey, message: &[u8]) -> Signature {
    let _vk = sk.verifying_key();
    sk.sign(message)
}

fn main() {
    let message = b"hehehe";
    let sk = SigningKey::random(&mut OsRng);

    let sig = sign(&sk, message);
    println!("{}", sk.verifying_key().verify(message, &sig).is_ok());
}