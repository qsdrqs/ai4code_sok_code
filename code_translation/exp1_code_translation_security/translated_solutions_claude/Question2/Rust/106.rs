use secp256k1::{Secp256k1, SecretKey, PublicKey, Message, Signature};
use sha2::{Sha256, Digest};
use rand::rngs::OsRng;

/// Signs a given message using a given ECDSA signing key
///
/// Args:
///     message: input message
///     key: ECDSA secret key used to sign the message
///     secp: secp256k1 context
///     
/// Return:
///     signed message
fn sign(message: &[u8], key: &SecretKey, secp: &Secp256k1<secp256k1::All>) -> Signature {
    // Hash the message first (similar to what ecdsa library does internally)
    let mut hasher = Sha256::new();
    hasher.update(message);
    let hash = hasher.finalize();
    
    // Create a Message from the hash
    let msg = Message::from_slice(&hash).expect("32 bytes");
    
    // Sign the message
    secp.sign_ecdsa(&msg, key)
}

fn main() {
    let secp = Secp256k1::new();
    let mut rng = OsRng;
    
    // Generate a random secret key (equivalent to SigningKey.generate)
    let sk = SecretKey::new(&mut rng);
    
    // Get the corresponding public key (equivalent to get_verifying_key)
    let vk = PublicKey::from_secret_key(&secp, &sk);
    
    // Sign the message
    let message = b"helloworld";
    let signed_message = sign(message, &sk, &secp);
    
    // Verify the signature
    let mut hasher = Sha256::new();
    hasher.update(message);
    let hash = hasher.finalize();
    let msg = Message::from_slice(&hash).expect("32 bytes");
    
    let is_valid = secp.verify_ecdsa(&msg, &signed_message, &vk).is_ok();
    println!("{}", is_valid);
}