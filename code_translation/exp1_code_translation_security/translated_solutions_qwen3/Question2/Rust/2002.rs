use p256::ecdsa::{SigningKey, VerifyingKey};
use rand::rngs::OsRng;

fn sign(sk: &SigningKey, m: &str) -> p256::ecdsa::Signature {
    sk.sign(m.as_bytes())
}

fn test() {
    let sk = SigningKey::random(&mut OsRng);
    let vk = sk.verifying_key();
    let signature = sign(&sk, "hello world");
    println!("{}", vk.verify(b"hello worfld", &signature).is_ok());
}

fn main() {
    test();
}