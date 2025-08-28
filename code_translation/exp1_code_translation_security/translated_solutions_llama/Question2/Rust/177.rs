use ring::signature::{Signer, KeyPair};

// Assuming the secret key is a KeyPair
fn sign(m: &[u8], sk: &KeyPair) -> Vec<u8> {
    let mut signature = vec![0; sk.public_key().as_ref().len()];
    let signature_len = sk.sign(m, &mut signature).unwrap();
    signature.truncate(signature_len);
    signature
}

// Alternatively, if you're using the `ed25519` crate
use ed25519::Keypair;

fn sign_ed25519(m: &[u8], sk: &Keypair) -> Vec<u8> {
    sk.sign(m).to_vec()
}

fn main() {
    // Example usage
    let rng = ring::rand::SystemRandom::new();
    let key_pair = ring::signature::KeyPair::generate(&ring::signature::ED25519, &rng).unwrap();
    let message = b"Hello, World!";
    let signature = sign(message, &key_pair);
    println!("{:?}", signature);

    // Example usage with ed25519 crate
    let keypair = ed25519::Keypair::generate();
    let message = b"Hello, World!";
    let signature_ed25519 = sign_ed25519(message, &keypair);
    println!("{:?}", signature_ed25519);
}