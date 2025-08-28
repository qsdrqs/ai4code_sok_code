use ecdsa::{SigningKey, VerifyingKey};
use ecdsa::serde::pkcs8;
use serde::{Deserialize, Serialize};
use std::fs::File;
use std::io::Read;
use std::path::Path;

// Define a function to sign a message with a given key
fn sign(msg: &[u8], key: &SigningKey) -> Vec<u8> {
    let sig = key.sign(msg);
    sig.to_bytes().to_vec()
}

fn main() {
    // Load the private key from a file (assuming it's in PKCS#8 format)
    let mut file = File::open("private_key.pem").expect("Failed to open private key file");
    let mut private_key_pem = String::new();
    file.read_to_string(&mut private_key_pem).expect("Failed to read private key file");

    let private_key = pkcs8::from_pem(private_key_pem.as_bytes()).expect("Failed to parse private key");
    let signing_key = SigningKey::from(private_key);

    let msg = b"message";
    let sig = sign(msg, &signing_key);

    println!("Signature: {:?}", sig);
}