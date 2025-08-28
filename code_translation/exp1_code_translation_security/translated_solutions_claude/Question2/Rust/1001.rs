use ring::signature::{EcdsaKeyPair, ECDSA_P256_SHA256_FIXED_SIGNING};
use ring::rand::SystemRandom;

// Create a key pair (this would typically be done elsewhere)
let rng = SystemRandom::new();
let pkcs8_bytes = EcdsaKeyPair::generate_pkcs8(&ECDSA_P256_SHA256_FIXED_SIGNING, &rng)?;
let key_pair = EcdsaKeyPair::from_pkcs8(&ECDSA_P256_SHA256_FIXED_SIGNING, pkcs8_bytes.as_ref())?;

// Sign a message
let message = b"Hello, world!";
let signature = sign_using_ecdsa(&key_pair, message)?;