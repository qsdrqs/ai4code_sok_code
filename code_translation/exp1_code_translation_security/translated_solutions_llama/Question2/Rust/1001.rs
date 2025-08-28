use ecdsa::{SigningKey, VerifyingKey};
use ecdsa::signature::Signer;
use sha2::Sha256;
use secp256k1::Secp256k1;

/// Signs a message using ECDSA with SHA256 hash function.
///
/// # Arguments
///
/// * `key` - The private key used for signing.
/// * `message` - The message to be signed.
///
/// # Returns
///
/// The ECDSA signature of the message.
pub fn sign_using_ecdsa(key: &secp256k1::SecretKey, message: &[u8]) -> ecdsa::Signature {
    let signing_key = SigningKey::from_secret_key(Secp256k1::new(), key);
    let mut signer = signing_key.signer();
    signer.update(message);
    signer.sign(&Sha256::new().update(message).finalize())
}

fn main() {
    // Example usage:
    let s = Secp256k1::new();
    let key = secp256k1::SecretKey::new(&s);
    let message = b"Hello, World!";
    let signature = sign_using_ecdsa(&key, message);
    println!("{:?}", signature);
}