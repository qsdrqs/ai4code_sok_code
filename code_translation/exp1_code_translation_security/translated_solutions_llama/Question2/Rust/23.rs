use ecdsa::{SigningKey, VerifyingKey};
use rand::thread_rng;

fn sign(message: &[u8], key: &SigningKey) -> Vec<u8> {
    let vk = VerifyingKey::from(key);
    let signature = key.sign(message);
    signature.to_bytes().to_vec()
}

fn main() {
    let mut rng = thread_rng();
    let key = SigningKey::random(&mut rng);
    let message = b"i am a message";
    let signature = sign(message, &key);
    let vk = VerifyingKey::from(&key);
    assert!(vk.verify(message, &signature).is_ok());
    println!("{:?}", signature);
}