// Cargo.toml dependencies:
// [dependencies]
// k256 = { version = "0.13", features = ["ecdsa"] }

//! A function in Rust that signs a given message using a given ECDSA signing key.

use k256::ecdsa::{signature::Signer, Signature, SigningKey};

/// Sign a message using a given ECDSA signing key.
fn sign(message: &[u8], private_key: &[u8]) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    // Create a new ECDSA signing key from the private key bytes (SECP256k1 curve)
    let signing_key = SigningKey::from_slice(private_key)?;
    // Sign the message using the ECDSA signing key
    let signed_message: Signature = signing_key.sign(message);
    // Return the signed message as bytes
    Ok(signed_message.to_bytes().to_vec())
}

fn main() {
    // Example usage
    let private_key = [0x01u8; 32]; // Example 32-byte private key
    let message = b"Hello, world!";

    match sign(message, &private_key) {
        Ok(signature) => println!("Signature ({} bytes): {:x?}", signature.len(), signature),
        Err(e) => eprintln!("Error: {}", e),
    }
}