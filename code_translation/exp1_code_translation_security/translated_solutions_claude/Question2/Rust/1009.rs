use p384::ecdsa::{SigningKey, VerifyingKey, Signature, signature::Signer, signature::Verifier};
use rand::rngs::OsRng;

fn sign(sk: &SigningKey, message: &[u8]) -> Signature {
    let _vk: VerifyingKey = sk.verifying_key();
    sk.sign(message)
}

fn main() {
    let message = b"hehehe";
    let sk = SigningKey::random(&mut OsRng);
    
    let sig = sign(&sk, message);
    let vk = sk.verifying_key();
    let result = vk.verify(message, &sig).is_ok();
    println!("{}", result);
}