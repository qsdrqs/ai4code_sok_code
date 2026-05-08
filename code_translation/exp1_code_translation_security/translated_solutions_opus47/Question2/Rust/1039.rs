use k256::ecdsa::{SigningKey, Signature, signature::Signer};

fn sign_message(message: &[u8], key: &SigningKey) -> Signature {
    key.sign(b"message")
}