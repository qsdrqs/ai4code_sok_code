use secp256k1::{Secp256k1, SecretKey, PublicKey, Message};
use secp256k1::hashes::{sha256, Hash};
use rand::rngs::OsRng;

fn main() {
    // Create secp256k1 context
    let secp = Secp256k1::new();
    
    // Generate a random secret key
    let sk = SecretKey::new(&mut OsRng);
    
    // Get the corresponding public key (verifying key)
    let vk = PublicKey::from_secret_key(&secp, &sk);
    
    fn sign_message(secp: &Secp256k1<secp256k1::All>, sk: &SecretKey, vk: &PublicKey, message: &str) -> bool {
        // Hash the message (ECDSA requires a hash, not raw message)
        let message_hash = sha256::Hash::hash(message.as_bytes());
        let message = Message::from_slice(&message_hash[..]).expect("32 bytes");
        
        // Sign the message
        let sig = secp.sign_ecdsa(&message, sk);
        
        // Verify the signature and return the result
        secp.verify_ecdsa(&message, &sig, vk).is_ok()
    }
    
    println!("{}", sign_message(&secp, &sk, &vk, ".."));
}