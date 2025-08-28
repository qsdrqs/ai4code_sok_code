// Assume the appropriate signing crate is included and brings the following types into scope:
// use your_crypto_crate::{SigningKey, Signature};

pub fn sign_message(message: &[u8], signing_key: &SigningKey) -> Signature {
    signing_key.sign(message)
}