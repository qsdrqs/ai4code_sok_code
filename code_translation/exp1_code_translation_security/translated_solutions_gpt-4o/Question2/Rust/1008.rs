use k256::ecdsa::{SigningKey, Signature};
use k256::elliptic_curve::sec1::ToEncodedPoint;
use k256::elliptic_curve::rand_core::OsRng;
use k256::elliptic_curve::FieldBytes;
use std::str::FromStr;

/// Sign a message using an ECDSA key
fn sign_message(message: &str, private_key: &str) -> Result<Signature, Box<dyn std::error::Error>> {
    // Convert the message to bytes
    let message_bytes = message.as_bytes();

    // Convert the private key from a string to bytes
    let private_key_bytes = hex::decode(private_key)?;

    // Create a signing key from the private key bytes
    let signing_key = SigningKey::from_bytes(&private_key_bytes)?;

    // Sign the message
    let signature = signing_key.sign(message_bytes);

    Ok(signature)
}

fn main() {
    match sign_message("hello world", "3132333435363738393031323334353637383930313233343536373839303132") {
        Ok(signature) => println!("Signature: {:?}", signature),
        Err(e) => eprintln!("Error signing message: {}", e),
    }
}