use ecdsa::{SigningKey, VerifyingKey};
use ecdsa::curve::secp256k1;
use hex;

fn sign_message(message: &str, private_key: &str) -> Vec<u8> {
    // Convert private key from hex string to bytes
    let private_key_bytes = hex::decode(private_key).unwrap();

    // Create a new signing key from the private key bytes
    let signing_key = SigningKey::from_bytes(&private_key_bytes, &secp256k1::params());

    // Convert message to bytes
    let message_bytes = message.as_bytes();

    // Sign the message
    let signature = signing_key.sign(message_bytes);

    // Return the signature as a vector of bytes
    signature.to_bytes().to_vec()
}

fn main() {
    let message = "hello world";
    let private_key = "12345678901234567890123456789012";

    let signature = sign_message(message, private_key);
    println!("{:?}", signature);
}