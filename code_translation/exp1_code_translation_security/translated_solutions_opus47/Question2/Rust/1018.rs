use k256::ecdsa::{signature::Signer, Signature, SigningKey};

fn sign(msg: &[u8], key: &SigningKey) -> Signature {
    let sig: Signature = key.sign(b"message");
    sig
}