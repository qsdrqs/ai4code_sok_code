use ed25519_dalek::{SigningKey, Signature, Signer};
use rand::rngs::OsRng;

fn main() {
    let mut csprng = OsRng;
    let signing_key = SigningKey::generate(&mut csprng);
    let message = b"Hello, world!";
    let signature = sign_message(message, &signing_key);
    println!("Signature: {:?}", signature);
}