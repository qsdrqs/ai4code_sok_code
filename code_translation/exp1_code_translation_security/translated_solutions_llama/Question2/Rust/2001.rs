use ring::digest;
use secp256k1::{Context, Error, Message, SecretKey, Signing};

/// Function that signs input with the given ECDSA private key
fn sign(message: &[u8], private_key: &SecretKey) -> Result<Vec<u8>, Error> {
    let context = Context::new();
    let message = Message::from_slice(message)?;
    let signature = context.sign(&private_key, &message);
    Ok(signature.to_vec())
}

fn main() {
    // Example usage:
    let secret_key = SecretKey::new(&secp256k1::rand::thread_rng());
    let message = b"Hello, World!";
    match sign(message, &secret_key) {
        Ok(signature) => println!("Signature: {:?}", signature),
        Err(err) => println!("Error: {}", err),
    }
}