use k256::ecdsa::{
    signature::{Signer, Verifier},
    Signature, SigningKey,
};
use rand::rngs::OsRng;

/// Signs a given message using a given ECDSA signing key
///
/// # Arguments
/// * `message` - input message
/// * `key` - ECDSA key used to sign the message
///
/// # Returns
/// signed message
fn sign(message: &[u8], key: &SigningKey) -> Signature {
    key.sign(message)
}

fn main() {
    let sk = SigningKey::random(&mut OsRng);
    let vk = sk.verifying_key();
    let signed_message = sign(b"helloworld", &sk);
    println!("{}", vk.verify(b"helloworld", &signed_message).is_ok());
}