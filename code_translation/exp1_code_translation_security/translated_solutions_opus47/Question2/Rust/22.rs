use k256::ecdsa::{signature::Signer, Signature, SigningKey};
use rand_core::OsRng;

fn myfunction(message: &[u8]) {
    let sk = SigningKey::random(&mut OsRng);
    let sig: Signature = sk.sign(message);
}