use k256::ecdsa::{SigningKey, Signature, signature::Signer};
use rand_core::OsRng;

fn sign(message: &[u8], key: &SigningKey) -> Signature {
    key.sign(message)
}

fn main() {
    // Generate a new signing key
    let signing_key = SigningKey::random(&mut OsRng);

    // Sign the message
    let signature = sign(b"i am a message", &signing_key);

    // Print the signature
    println!("{:?}", signature);
}