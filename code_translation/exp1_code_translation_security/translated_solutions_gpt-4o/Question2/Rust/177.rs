use ed25519_dalek::{Keypair, Signature, Signer};

fn sign(message: &[u8], secret_key: &Keypair) -> Signature {
    secret_key.sign(message)
}