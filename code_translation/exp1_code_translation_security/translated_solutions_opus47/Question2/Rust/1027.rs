use ed25519_dalek::{Signature, Signer, SigningKey};

fn sign_message(message: &[u8], signing_key: &SigningKey) -> Signature {
    signing_key.sign(message)
}