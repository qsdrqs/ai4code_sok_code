use ecdsa::{SigningKey, VerifyingKey};
use secp256k1::Secp256k1;
use std::str;

fn sign_message(message: &[u8], private_key: &[u8]) -> Vec<u8> {
    // Create a new Secp256k1 context
    let s = Secp256k1::new();

    // Parse the private key
    let private_key = match SigningKey::from_slice(&s, private_key) {
        Ok(key) => key,
        Err(_) => panic!("Invalid private key"),
    };

    // Sign the message
    let signature = match private_key.sign(&s, message) {
        Ok(sig) => sig.to_der(),
        Err(_) => panic!("Failed to sign message"),
    };

    signature.to_vec()
}

fn main() {
    let message = "hello".as_bytes();
    let private_key = "qwertyuiopasdfghjklzxcvbnmqwerty".as_bytes();

    let signature = sign_message(message, private_key);
    println!("{:?}", signature);
}