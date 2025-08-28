use ecdsa::{SigningKey, VerifyingKey, signature::Signer};
use rand::thread_rng;

fn sign(msg: &[u8], key: &SigningKey) -> Vec<u8> {
    let vk = VerifyingKey::from(key);
    let signature = key.sign(msg);
    assert!(vk.verify(msg, &signature).is_ok());
    signature.to_bytes().to_vec()
}

fn main() {
    let mut rng = thread_rng();
    let sk = SigningKey::random(&mut rng);
    println!("{:?}", sign(b"message", &sk));
}

#[dependencies]
fn dependencies() {
    // Add the following dependencies to your Cargo.toml:
    // ecdsa = "0.10.0"
    // rand = "0.8.5"
}