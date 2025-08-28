extern crate ring;

use ring::rand::SystemRandom;
use ring::signature::{EcdsaKeyPair, ECDSA_P384_SHA256_ASN1_SIGNING, Signature, KeyPair};

fn sign(key_pair: &EcdsaKeyPair, data: &[u8]) -> Signature {
    let rng = SystemRandom::new();
    key_pair.sign(&rng, data).unwrap()
}

fn test() {
    let rng = SystemRandom::new();
    let private_key = EcdsaKeyPair::generate_pkcs8(&ECDSA_P384_SHA256_ASN1_SIGNING, &rng).unwrap();
    let key_pair = EcdsaKeyPair::from_pkcs8(&ECDSA_P384_SHA256_ASN1_SIGNING, private_key.as_ref()).unwrap();
    
    let data = b"this is some data I'd like to sign";
    let signature = sign(&key_pair, data);
    println!("{:?}", signature.as_ref());
}

fn main() {
    test();
}