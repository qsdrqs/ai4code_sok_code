use ecdsa::{SigningKey, VerifyingKey};
use ecdsa::curve::secp256k1;
use ecdsa::serde::pkcs8::Pkcs8PrivateKey;
use serde::de::DeserializeOwned;
use std::convert::TryFrom;

/// Signs a given message using ECDSA signing key
///
/// # Arguments
///
/// * `message`: The message to be signed.
/// * `private_key`: The private key to sign the message.
///
/// # Returns
///
/// The signature of the message.
pub fn sign_message(message: &[u8], private_key: &[u8]) -> Result<Vec<u8>, ecdsa::Error> {
    // Load the private key from a PKCS#8 encoded byte slice
    let private_key = Pkcs8PrivateKey::try_from(private_key)?;
    let signing_key = SigningKey::from_pkcs8(private_key, &secp256k1::SECP256K1)?;
    
    // Sign the message
    let signature = signing_key.sign(message);
    
    Ok(signature.to_vec())
}

fn main() {
    // Example usage
    let message = b"Hello, World!";
    let private_key = include_bytes!("path_to_your_private_key.pem"); // Replace with your private key file
    match sign_message(message, private_key) {
        Ok(signature) => println!("Signature: {:?}", signature),
        Err(err) => println!("Error: {}", err),
    }
}