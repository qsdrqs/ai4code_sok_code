use ring::signature::{EcdsaKeyPair, KeyPair, ECDSA_P256_SHA256_ASN1_SIGNING};
use ring::rand::SystemRandom;

// Function that signs input with the given ECDSA private key
fn sign(message: &[u8], private_key: &EcdsaKeyPair) -> Vec<u8> {
    let rng = SystemRandom::new();
    private_key.sign(&rng, message).unwrap().as_ref().to_vec()
}

fn main() {
    // Example usage
    let rng = SystemRandom::new();

    // Generate a private key for ECDSA P-256
    let private_key_bytes = ring::signature::EcdsaKeyPair::generate_pkcs8(&ECDSA_P256_SHA256_ASN1_SIGNING, &rng).unwrap();
    let private_key = EcdsaKeyPair::from_pkcs8(&ECDSA_P256_SHA256_ASN1_SIGNING, private_key_bytes.as_ref()).unwrap();

    let message = b"Hello, world!";
    let signature = sign(message, &private_key);

    println!("Signature: {:?}", signature);
}