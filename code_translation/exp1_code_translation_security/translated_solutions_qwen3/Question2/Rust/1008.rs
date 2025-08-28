use k256::ecdsa::{SigningKey, signature::Signer};
use std::convert::TryInto;

fn sign_message(message: &str, private_key: &str) -> Result<Vec<u8>, String> {
    // Convert private key string to 32-byte array
    let private_key_bytes = private_key.as_bytes();
    let private_key_array: [u8; 32] = private_key_bytes
        .try_into()
        .map_err(|_| "Private key must be exactly 32 bytes".to_string())?;

    // Create a signing key from the private key bytes
    let signing_key = SigningKey::from_bytes(&private_key_array)
        .map_err(|e| format!("Invalid private key: {}", e))?;

    // Sign the message (UTF-8 encoded)
    let signature = signing_key.sign(message.as_bytes());

    // Return the DER-encoded signature as a Vec<u8>
    Ok(signature.as_ref().to_vec())
}

fn main() {
    match sign_message("hello world", "12345678901234567890123456789012") {
        Ok(sig) => println!("Signature: {:?}", sig),
        Err(e) => println!("Error: {}", e),
    }
}