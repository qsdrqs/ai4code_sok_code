use ed25519_dalek::{Keypair, Signature, Signer};

fn sign_message(message: &[u8], signing_key: &Keypair) -> Signature {
    signing_key.sign(message)
}