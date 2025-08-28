use p384::ecdsa::{SigningKey, VerifyingKey, Signature};
use p384::ecdsa::signature::{Signer, Verifier};
use rand::rngs::OsRng;

fn main() {
    let sk = SigningKey::random(&mut OsRng);
    let vk: VerifyingKey = sk.verifying_key();
    let signature = sign_message(&sk, b"testing");
    
    match vk.verify(b"testing", &signature) {
        Ok(()) => println!("true"),
        Err(_) => println!("false"),
    }
}

fn sign_message(key: &SigningKey, message: &[u8]) -> Signature {
    key.sign(message)
}