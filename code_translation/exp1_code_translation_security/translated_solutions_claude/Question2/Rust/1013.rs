use secp256k1::{Secp256k1, SecretKey, Message};
use sha2::{Sha256, Digest};
use rand::rngs::OsRng;

fn sign(message: &str, key: &SecretKey) -> secp256k1::ecdsa::Signature {
    let secp = Secp256k1::new();
    
    // Hash the message using SHA-256
    let mut hasher = Sha256::new();
    hasher.update(message.as_bytes());
    let hash = hasher.finalize();
    
    // Create a Message from the hash
    let msg = Message::from_slice(&hash).expect("32 bytes");
    
    // Sign the message
    secp.sign_ecdsa(&msg, key)
}

fn main() {
    let secp = Secp256k1::new();
    let mut rng = OsRng;
    let key = SecretKey::new(&mut rng);
    
    let signature = sign("something", &key);
    println!("{:?}", signature);
}