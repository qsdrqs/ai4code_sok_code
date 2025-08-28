use k256::ecdsa::{SigningKey, Signature};
use k256::elliptic_curve::sec1::ToEncodedPoint;
use k256::elliptic_curve::SecretKey;
use k256::ecdsa::signature::Signer;

fn sign_message(message: &[u8], private_key: &[u8]) -> Signature {
    // Create a SecretKey from the private key bytes
    let secret_key = SecretKey::from_bytes(private_key).expect("Invalid private key");

    // Create a SigningKey from the SecretKey
    let signing_key = SigningKey::from(secret_key);

    // Sign the message
    let signature = signing_key.sign(message);

    signature
}

fn main() {
    // Example usage
    let message = b"Hello, world!";
    let private_key = [/* your 32-byte private key here */];

    let signature = sign_message(message, &private_key);
    println!("Signature: {:?}", signature);
}