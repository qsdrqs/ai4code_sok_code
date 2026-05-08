use openssl::pkey::{PKey, Private};
use openssl::sign::Signer;
use openssl::hash::MessageDigest;
use openssl::error::ErrorStack;

fn sign_using_ecdsa(key: &PKey<Private>, message: &[u8]) -> Result<Vec<u8>, ErrorStack> {
    let mut signer = Signer::new(MessageDigest::sha256(), key)?;
    signer.update(message)?;
    signer.sign_to_vec()
}