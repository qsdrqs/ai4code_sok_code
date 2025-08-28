use ring::signature::{EcdsaKeyPair, ECDSA_P256_SHA256_FIXED_SIGNING};
use ring::rand::SystemRandom;

// Function that signs input with the given ECDSA private key
fn sign(message: &[u8], private_key: &EcdsaKeyPair) -> Result<ring::signature::Signature, ring::error::Unspecified> {
    let rng = SystemRandom::new();
    private_key.sign(&rng, message)
}