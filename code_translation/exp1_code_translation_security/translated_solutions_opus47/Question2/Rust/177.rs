use ed25519_dalek::{Signature, Signer, SigningKey};

fn sign(m: &[u8], sk: &SigningKey) -> Signature {
    sk.sign(m)
}