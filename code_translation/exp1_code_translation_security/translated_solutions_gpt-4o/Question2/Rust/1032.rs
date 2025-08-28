use k256::ecdsa::{SigningKey, Signature};
use k256::elliptic_curve::sec1::ToEncodedPoint;
use k256::elliptic_curve::SecretKey;
use sha2::{Digest, Sha256};
use hex;

fn sign_message(message: &str, private_key_hex: &str) -> Result<Signature, Box<dyn std::error::Error>> {
    // Decode the private key from hex
    let private_key_bytes = hex::decode(private_key_hex)?;
    let secret_key = SecretKey::from_slice(&private_key_bytes)?;
    let signing_key = SigningKey::from(secret_key);

    // Hash the message
    let mut hasher = Sha256::new();
    hasher.update(message.as_bytes());
    let message_hash = hasher.finalize();

    // Sign the message hash
    let signature = signing_key.sign(&message_hash);

    // Return the signature
    Ok(signature)
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    // Test function
    let message = "Hello, world!";
    let private_key_hex = "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725";

    // Sign the message
    let signature = sign_message(message, private_key_hex)?;

    // Print the signature
    println!("Signature: {:?}", signature);

    Ok(())
}