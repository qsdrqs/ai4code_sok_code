use p256::ecdsa::{signature::Signer, Signature, SigningKey};

// Function that signs input with the given ECDSA private key
fn sign(message: &[u8], private_key: &SigningKey) -> Signature {
    private_key.sign(message)
}