use secp256k1::{Secp256k1, SecretKey, PublicKey, Message, ecdsa::Signature};
use sha3::{Sha3_256, Digest};
use rand::Rng;

fn sha3_256_hash(msg: &str) -> [u8; 32] {
    let mut hasher = Sha3_256::new();
    hasher.update(msg.as_bytes());
    hasher.finalize().into()
}

fn sign_ecdsa_secp256k1(msg: &str, priv_key: &SecretKey) -> Signature {
    let secp = Secp256k1::new();
    let msg_hash = sha3_256_hash(msg);
    let message = Message::from_slice(&msg_hash).expect("32 bytes");
    secp.sign_ecdsa(&message, priv_key)
}

fn verify_ecdsa_secp256k1(msg: &str, signature: &Signature, pub_key: &PublicKey) -> bool {
    let secp = Secp256k1::new();
    let msg_hash = sha3_256_hash(msg);
    let message = Message::from_slice(&msg_hash).expect("32 bytes");
    secp.verify_ecdsa(&message, signature, pub_key).is_ok()
}

fn main() {
    let secp = Secp256k1::new();
    let msg = "Message for ECDSA signing";
    
    // Generate a random private key
    let mut rng = rand::thread_rng();
    let priv_key = SecretKey::new(&mut rng);
    
    // Sign the message
    let signature = sign_ecdsa_secp256k1(msg, &priv_key);
    
    // Get the public key for verification
    let pub_key = PublicKey::from_secret_key(&secp, &priv_key);
    
    // Verify the signature
    let is_valid = verify_ecdsa_secp256k1(msg, &signature, &pub_key);
    
    println!("Message: {}", msg);
    println!("Private key: 0x{}", hex::encode(priv_key.secret_bytes()));
    
    // Extract r and s values from signature
    let sig_bytes = signature.serialize_compact();
    let r = &sig_bytes[0..32];
    let s = &sig_bytes[32..64];
    
    println!("Signature: r=0x{}, s=0x{}", hex::encode(r), hex::encode(s));
    println!("Signature valid: {}", is_valid);
}