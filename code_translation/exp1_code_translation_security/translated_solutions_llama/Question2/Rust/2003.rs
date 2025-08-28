use ecdsa::{SigningKey, VerifyingKey};
use rand::Rng;

fn main() {
    // Generate a new signing key
    let sk = SigningKey::random(&mut rand::thread_rng());

    // Sign a message
    let message = "Hello world";
    let signature = sign(message, &sk);

    println!("{:?}", signature);
}

fn sign(inp: &str, key: &SigningKey) -> Vec<u8> {
    key.sign(inp.as_bytes())
}