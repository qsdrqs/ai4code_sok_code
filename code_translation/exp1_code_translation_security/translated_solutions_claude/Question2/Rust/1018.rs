use ecdsa::{SigningKey, Signature};
use ecdsa::signature::Signer;
use k256::Secp256k1;

fn sign(msg: &[u8], key: &SigningKey<Secp256k1>) -> Signature<Secp256k1> {
    let sig: Signature<Secp256k1> = key.sign(b"message");
    sig
}