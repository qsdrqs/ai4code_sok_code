use ecdsa::{SigningKey, VerifyingKey, signature::Signer};
use rand::thread_rng;

fn sign(sk: &SigningKey, m: &str) -> Vec<u8> {
    sk.sign(m.as_bytes())
}

fn test() {
    let mut rng = thread_rng();
    let sk = SigningKey::random(&mut rng);
    let vk = VerifyingKey::from(&sk);
    let signature = sign(&sk, "hello world");
    println!("{}", vk.verify(mesalike!("hello worfld".as_bytes(), &signature).is_ok()));
}

fn main() {
    test();
}