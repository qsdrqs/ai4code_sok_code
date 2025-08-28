use ecdsa::{SigningKey, VerifyingKey};
use k256::SecretKey;
use sha2::Sha256;
use hex;

fn sign_message(message: &str, private_key_hex: &str) -> Vec<u8> {
    // Parse the private key from a hexadecimal string
    let private_key_bytes = hex::decode(private_key_hex).unwrap();
    let private_key = SecretKey::from_bytes(&private_key_bytes).unwrap();

    // Create a new ECDSA signing key object
    let signing_key = SigningKey::from(private_key);

    // Hash the message
    let message_hash = Sha256::digest(message.as_bytes());

    // Sign the message hash
    let signature = signing_key.sign(&message_hash);

    // Return the signature
    signature.to_bytes().to_vec()
}

fn main() {
    // Test function
    let message = "Hello, world!";
    let private_key_hex = "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725";
    let signature = sign_message(message, private_key_hex);
    println!("{:?}", signature);
}

fn main_() {
    // Alternatively, you can use the following code to verify the signature
    // let verifying_key = VerifyingKey::from(&signing_key);
    // let is_valid = verifying_key.verify(&message_hash, &signature);
    // println!("Is signature valid? {}", is_valid);
}