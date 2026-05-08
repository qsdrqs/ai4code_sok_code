// Cargo.toml:
// [dependencies]
// p384 = { version = "0.13", features = ["ecdsa"] }
// rand_core = { version = "0.6", features = ["getrandom"] }

use p384::ecdsa::{
    signature::{Signer, Verifier},
    Signature, SigningKey, VerifyingKey,
};
use rand_core::OsRng;

#[allow(dead_code)]
fn ecdsa_key(message: &[u8], _key: &[u8]) -> Signature {
    let sk = SigningKey::random(&mut OsRng);
    let vk = VerifyingKey::from(&sk);
    let signature: Signature = sk.sign(message);
    println!("{}", vk.verify(message, &signature).is_ok());
    signature
}

#[allow(dead_code)]
fn ecdsa_verify(signature: &Signature, message: &[u8], vk: &VerifyingKey) -> bool {
    vk.verify(message, signature).is_ok()
}

fn ecdsa_genkey() -> (SigningKey, VerifyingKey) {
    let sk = SigningKey::random(&mut OsRng);
    let vk = VerifyingKey::from(&sk);
    (sk, vk)
}

fn ecdsa_sign(sk: &SigningKey, message: &[u8]) -> Signature {
    sk.sign(message)
}

fn ecdsa_verify_key(vk: &VerifyingKey, signature: &Signature, message: &[u8]) -> bool {
    vk.verify(message, signature).is_ok()
}

fn main() {
    let message = b"This is a test message";
    let (sk, vk) = ecdsa_genkey();
    println!("{:?}", sk.to_bytes());
    println!("{:?}", vk.to_encoded_point(false).as_bytes());
    let signature = ecdsa_sign(&sk, message);
    println!("{:?}", signature.to_bytes());
    println!("{}", ecdsa_verify_key(&vk, &signature, message));
}