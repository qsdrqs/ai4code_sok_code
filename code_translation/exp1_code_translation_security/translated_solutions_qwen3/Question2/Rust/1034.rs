extern crate rand;
extern crate secp256k1;
extern crate sha2;

use rand::RngCore;
use secp256k1::{Message, PublicKey, SecretKey, Secp256k1};
use sha2::{Digest, Sha256};

fn main() {
    let secp = Secp256k1::new();
    let mut rng = rand::thread_rng();
    let private_key = SecretKey::new(&mut rng);
    let public_key = PublicKey::from_secret_key(&secp, &private_key);

    let message = "Test message";

    // Hash the message using SHA-256
    let hash = Sha256::digest(message.as_bytes());
    let msg = Message::from_slice(hash.as_ref()).expect("Message hash must be 32 bytes");

    // Sign the message
    let signature = secp.sign_ecdsa(&msg, &private_key);

    // Verify the signature
    let is_valid = secp.verify_ecdsa(&msg, &signature, &public_key).is_ok();
    println!("{}", is_valid);
}