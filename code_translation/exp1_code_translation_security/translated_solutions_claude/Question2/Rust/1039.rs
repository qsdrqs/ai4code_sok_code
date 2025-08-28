use ecdsa::{SigningKey, Signature, signature::Signer};
use k256::Secp256k1; // Using secp256k1 curve as an example

fn sign_message(message: &str, key: &SigningKey<Secp256k1>) -> Signature<Secp256k1> {
    key.sign(b"message")
}