use serde_json;
use secp256k1::{Secp256k1, Message, SecretKey, PublicKey};
use secp256k1::hashing::sha256;
use rand::rngs::OsRng;

fn generate_ecdsa_signed_msg(message: &str, private_key: &SecretKey) -> secp256k1::ecdsa::Signature {
    let secp = Secp256k1::new();
    
    // Hash the message using SHA-256
    let message_hash = sha256::Hash::hash(message.as_bytes());
    let message = Message::from_slice(&message_hash[..]).expect("32 bytes");
    
    // Sign the message
    secp.sign_ecdsa(&message, private_key)
}

fn main() {
    // Generate Keys
    let secp = Secp256k1::new();
    let mut rng = OsRng;
    let (private_key, public_key) = secp.generate_keypair(&mut rng);
    
    let message = "My test message";
    let signature = generate_ecdsa_signed_msg(message, &private_key);
    
    println!("{:?}", signature);
}