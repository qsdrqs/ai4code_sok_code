extern crate ring;

use ring::rand::SystemRandom;
use ring::signature::{EcdsaKeyPair, ECDSA_P384_SHA256_ASN1_SIGNING, Signature, KeyPair};

fn sign_string(private_key: &EcdsaKeyPair, data: &[u8]) -> Signature {
    let rng = SystemRandom::new();
    private_key.sign(&rng, data).unwrap()
}

fn main() {
    // Generate a private key for ECDSA using P-384 and SHA-256
    let rng = SystemRandom::new();
    let private_key_bytes = EcdsaKeyPair::generate_pkcs8(&ECDSA_P384_SHA256_ASN1_SIGNING, &rng).unwrap();
    let private_key = EcdsaKeyPair::from_pkcs8(&ECDSA_P384_SHA256_ASN1_SIGNING, private_key_bytes.as_ref()).unwrap();

    // Data to be signed
    let data = b"hello world";

    // Sign the data
    let signature = sign_string(&private_key, data);

    // Print the signature
    println!("Signature: {:?}", signature.as_ref());
}