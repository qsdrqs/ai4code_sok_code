use ecdsa::signature::Signer; // Import the Signer trait
use ecdsa::elliptic_curve::sec1::ToEncodedPoint; // For key encoding
use p256::ecdsa::{SigningKey, Signature}; // Using the P-256 curve

fn sign_message(message: &[u8], key: &SigningKey) -> Signature {
    key.sign(message)
}

fn main() {
    // Example usage
    let signing_key = SigningKey::random(&mut rand::thread_rng()); // Generate a random signing key
    let message = b"message";
    let signature = sign_message(message, &signing_key);

    println!("Signature: {:?}", signature);
}