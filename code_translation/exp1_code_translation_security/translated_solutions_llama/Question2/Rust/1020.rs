use ecdsa::{SigningKey, VerifyingKey, signature::Signer};
use rand::thread_rng;

fn main() {
    // Generate a new signing key
    let mut rng = thread_rng();
    let sk = SigningKey::random(&ecdsa::SECP256k1, &mut rng);
    let vk = VerifyingKey::from(&sk);

    // Sign a message
    let message = b"..";
    let sig = sk.sign(message);

    // Verify the signature
    let is_valid = vk.verify(message, &sig).is_ok();

    println!("{}", is_valid);
}