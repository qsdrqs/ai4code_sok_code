use k256::ecdsa::{SigningKey, Signature};
use k256::elliptic_curve::rand_core::OsRng; // For generating random keys

fn sign(message: &str, key: &SigningKey) -> Signature {
    // Sign the message using the provided key
    let signature = key.sign(message.as_bytes());
    signature
}

fn main() {
    // Generate a new signing key
    let private_key = SigningKey::random(&mut OsRng);

    // Sign the message
    let message = "something";
    let signature = sign(message, &private_key);

    // Print the signature
    println!("Signature: {:?}", signature);
}