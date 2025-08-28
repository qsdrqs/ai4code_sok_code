use ecdsa::{SigningKey, VerifyingKey};
use ecdsa::signature::Signer;
use secp256k1::Secp256k1;
use sha2::Sha256;
use base64;
use hex;

/// Signs a message using ECDSA with the SECP256k1 curve.
///
/// # Arguments
///
/// * `message`: The message to be signed.
/// * `private_key`: The private key for signing in hexadecimal format.
///
/// # Returns
///
/// The signature of the message in hexadecimal format.
fn sign_message(message: &[u8], private_key: &str) -> String {
    // Parse the private key from hexadecimal
    let private_key_bytes = hex::decode(private_key).expect("Invalid private key");
    
    // Create a new SECP256k1 context
    let s = Secp256k1::new();
    
    // Create a signing key from the private key bytes
    let sk = SigningKey::from_bytes(&s, &private_key_bytes).expect("Invalid private key");
    
    // Sign the message
    let signature = sk.sign(&s, message);
    
    // Convert the signature to hexadecimal
    hex::encode(signature.as_ref())
}

fn main() {
    let message = b"Hello, World!";
    let private_key = "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef"; // Example private key
    
    let signature = sign_message(message, private_key);
    println!("Signature: {}", signature);
}