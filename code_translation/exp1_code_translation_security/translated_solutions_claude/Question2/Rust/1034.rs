use secp256k1::{Secp256k1, Message, SecretKey, PublicKey};
use secp256k1::hashes::{sha256, Hash};
use rand::rngs::OsRng;

fn main() {
    // Create secp256k1 context
    let secp = Secp256k1::new();
    
    // Generate private key
    let (private_key, public_key) = secp.generate_keypair(&mut OsRng);
    
    let message = "Test message";
    
    // Hash the message using SHA-256
    let message_hash = sha256::Hash::hash(message.as_bytes());
    let message_obj = Message::from_slice(&message_hash[..]).expect("32 bytes");
    
    // Sign the message
    let signature = secp.sign_ecdsa(&message_obj, &private_key);
    
    // Verify the signature
    let is_valid = secp.verify_ecdsa(&message_obj, &signature, &public_key).is_ok();
    
    println!("{}", is_valid);
}