use ecdsa::{SigningKey, VerifyingKey};
use rand::thread_rng;

fn sign(message: &str, key: &SigningKey) -> Vec<u8> {
    let signature = key.sign(message.as_bytes());
    signature.to_bytes().to_vec()
}

fn main() {
    let mut rng = thread_rng();
    let key = SigningKey::random(&mut rng);
    let message = "something";
    let signature = sign(message, &key);
    println!("{:?}", signature);
}