use ed25519_dalek::{Signature, Signer};

fn sign<S: Signer<Signature>>(m: &[u8], sk: &S) -> Signature {
    sk.sign(m)
}