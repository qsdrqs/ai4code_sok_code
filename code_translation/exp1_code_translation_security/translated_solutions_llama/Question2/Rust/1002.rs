use ring::digest;
use ring::signature::{self, ECDSA_P384_SHA384};
use rand::thread_rng;

// Define a function to generate a private key
fn generate_private_key() -> signature::EcdsaKeyPair {
    let rng = thread_rng();
    signature::EcdsaKeyPair::generate(&ECDSA_P384_SHA384, &rng).unwrap()
}

// Define a function to sign data with a private key
fn sign(key: &signature::EcdsaKeyPair, data: &[u8]) -> signature::Signature {
    key.sign(&ECDSA_P384_SHA384, data).unwrap()
}

// Define a test function
fn test() {
    let private_key = generate_private_key();
    let data = b"this is some data I'd like to sign";
    let signature = sign(&private_key, data);
    println!("{:?}", signature.as_ref());
}

fn main() {
    test();
}