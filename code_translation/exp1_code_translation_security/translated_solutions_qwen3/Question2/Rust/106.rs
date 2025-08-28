use k256::ecdsa::{SigningKey, VerifyingKey, Signature};
use rand_core::OsRng;

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
    let message = b"helloworld";
    let signed_message = sign(message, &sk);
    println!("{}", vk.verify(message, &signed_message).is_ok());
}