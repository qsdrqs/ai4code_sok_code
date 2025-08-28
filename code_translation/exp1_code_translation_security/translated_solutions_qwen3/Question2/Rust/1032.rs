use k256::ecdsa::{SigningKey};
use sha2::{Digest, Sha256};
use hex;

fn sign_message(message: &str, private_key: [u8; 32]) -> Vec<u8> {
    // Hash the message using SHA-256
    let message_hash = Sha256::digest(message.as_bytes());
    
    // Create a new ECDSA private key object
    let signing_key = SigningKey::from_bytes(&private_key).expect("Invalid private key");
    
    // Sign the message hash
    let signature = signing_key.sign(&message_hash);
    
    // Convert the signature to DER format and return as Vec<u8>
    signature.to_der().to_vec()
}

fn main() {
    // Test function
    let message = "Hello, world!";
    let private_key_hex = "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725";
    
    // Decode the private key from hex to bytes
    let private_key_bytes = hex::decode(private_key_hex).expect("Failed to decode private key");
    
    // Convert the Vec<u8> to a 32-byte array
    let mut private_key = [0u8; 32];
    private_key.copy_from_slice(&private_key_bytes[..32]);
    
    // Sign the message
    let signature = sign_message(message, private_key);
    
    // Print the signature
    println!("Signature: {:?}", signature);
}