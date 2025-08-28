use k256::ecdsa::{SigningKey, Signature};
use k256::elliptic_curve::rand_core::OsRng;

fn sign_message(message: &[u8]) -> Signature {
    // Generate a random private key
    let signing_key = SigningKey::random(&mut OsRng);

    // Sign the message
    let signature = signing_key.sign(message);

    signature
}

fn main() {
    let message = b"Hello, world!";
    let signature = sign_message(message);

    println!("Signature: {:?}", signature);
}