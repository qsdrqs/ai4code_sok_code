// Add the following dependencies to your Cargo.toml
// [dependencies]
// k256 = "0.10"
// base64 = "0.21"
// hex = "0.4"

use k256::ecdsa::{SigningKey, Signature, signature::SignerMut};
use k256::elliptic_curve::sec1::ToEncodedPoint;
use hex::FromHex;
use std::error::Error;

fn sign_message(message: &[u8], private_key_hex: &str) -> Result<Signature, Box<dyn Error>> {
    // Convert the private key from hex string to bytes
    let private_key_bytes = Vec::from_hex(private_key_hex)?;

    // Create a signing key from the private key bytes
    let signing_key = SigningKey::from_bytes(&private_key_bytes)?;

    // Sign the message
    let signature = signing_key.sign(message);

    Ok(signature)
}

fn main() -> Result<(), Box<dyn Error>> {
    // Example usage
    let message = b"Hello, world!";
    let private_key_hex = "your_private_key_in_hex_format";

    match sign_message(message, private_key_hex) {
        Ok(signature) => {
            println!("Signature: {:?}", signature);
        }
        Err(e) => {
            eprintln!("Error signing message: {}", e);
        }
    }

    Ok(())
}