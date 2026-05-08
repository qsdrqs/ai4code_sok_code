use p256::ecdsa::{Signature, SigningKey, VerifyingKey};
use p256::ecdsa::signature::{Signer, Verifier};
use rand_core::OsRng;

fn sign(sk: &SigningKey, m: &str) -> Signature {
    sk.sign(m.as_bytes())
}

fn test() {
    let sk = SigningKey::random(&mut OsRng);
    let vk = VerifyingKey::from(&sk);
    let signature = sign(&sk, "hello world");
    println!("{}", vk.verify("hello worfld".as_bytes(), &signature).is_ok());
}

fn main() {
    test();
}