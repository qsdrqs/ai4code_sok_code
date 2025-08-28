use ring::signature::{Signer, KeyPair};

// Assuming you have a function to generate or load a signing key pair
fn sign_message(message: &[u8], signing_key: &KeyPair) -> Vec<u8> {
    let mut signature = vec![0; signing_key.public_key().signature_len()];
    let mut signer = Signer::new(&signing_key.private_key());
    signer.update(message);
    signer.sign(&mut signature).unwrap();
    signature
}

fn main() {
    // Example usage
    let rng = ring::rand::SystemRandom::new();
    let key_pair = KeyPair::generate(&rng, &ring::signature::ED25519).unwrap();
    let message = b"Hello, World!";
    let signature = sign_message(message, &key_pair);
    println!("{:?}", signature);
}