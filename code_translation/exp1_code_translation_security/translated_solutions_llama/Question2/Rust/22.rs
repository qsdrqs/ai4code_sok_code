use ecdsa::{SigningKey, VerifyingKey};
use rand::Rng;

fn my_function(message: &[u8]) -> Vec<u8> {
    // Generate a new signing key
    let mut rng = rand::thread_rng();
    let sk = SigningKey::random(&ecdsa::SECP256k1, &mut rng);

    // Sign the message
    let sig = sk.sign(message);

    // Return the signature as a vector of bytes
    sig.to_bytes().to_vec()
}

fn main() {
    let message = b"Hello, World!";
    let signature = my_function(message);
    println!("{:?}", signature);
}