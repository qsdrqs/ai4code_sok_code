use k256::ecdsa::{signature::SignerMut, Signature, SigningKey};
use k256::elliptic_curve::sec1::ToEncodedPoint;
use sha2::{Digest, Sha256};
use hex;

fn sign_message(message: &str, private_key: &[u8]) -> String {
    // Hash the message
    let mut hasher = Sha256::new();
    hasher.update(message.as_bytes());
    let message_hash = hasher.finalize();

    // Create a signing key from the private key
    let signing_key = SigningKey::from_bytes(private_key).expect("Invalid private key");

    // Sign the message hash
    let signature: Signature = signing_key.sign(message_hash.as_slice());

    // Return the signature as a hex string
    hex::encode(signature.to_der())
}

fn main() {
    // Test the function
    let private_key_hex = "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
    let private_key = hex::decode(private_key_hex).expect("Invalid hex string for private key");
    let message = "Hello";
    let signature = sign_message(message, &private_key);
    println!("{}", signature);
}