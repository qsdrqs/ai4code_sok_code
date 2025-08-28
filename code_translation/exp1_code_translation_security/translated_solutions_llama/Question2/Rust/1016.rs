use ecdsa::{SigningKey, VerifyingKey};
use ecdsa::curve::secp256k1;
use sha2::Sha256;

/// Signs a given message using a given ECDSA signing key.
///
/// # Arguments
///
/// * `message`: The message to be signed.
/// * `private_key`: The private key used for signing.
///
/// # Returns
///
/// The signed message.
pub fn sign(message: &[u8], private_key: &[u8]) -> Vec<u8> {
    // Create a new ECDSA signing key from the provided private key
    let signing_key = SigningKey::from_bytes(private_key, &secp256k1::params());
    
    // Sign the message using the ECDSA signing key
    let signed_message = signing_key.sign(message);
    
    // Return the signed message
    signed_message.to_vec()
}

fn main() {
    // Example usage:
    let private_key = vec![0x00; 32]; // Replace with your private key
    let message = b"Hello, World!";
    let signed_message = sign(message, &private_key);
    println!("{:?}", signed_message);
}