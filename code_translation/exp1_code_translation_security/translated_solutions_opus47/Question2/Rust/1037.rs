use p384::ecdsa::{
    signature::{Signer, Verifier},
    Signature, SigningKey, VerifyingKey,
};
use rand_core::OsRng;

fn main() {
    let sk = SigningKey::random(&mut OsRng);
    let vk = VerifyingKey::from(&sk);
    let signature = sign_message(&sk, b"testing");
    println!("{}", vk.verify(b"testing", &signature).is_ok());
}

fn sign_message(key: &SigningKey, message: &[u8]) -> Signature {
    key.sign(message)
}