use rand::rngs::OsRng;
use p384::ecdsa::{SigningKey, VerifyingKey, Signature};
use ecdsa::signature::{Signer, Verifier};

fn sign_message(key: &SigningKey, message: &[u8]) -> Signature {
    key.sign(message)
}

fn main() {
    let mut os_rng = OsRng;
    let sk = SigningKey::random(&mut os_rng);
    let vk = sk.verifying_key();
    let message = b"testing";
    let signature = sign_message(&sk, message);
    println!("{}", vk.verify(message, &signature).is_ok());
}