// Necessary dependencies in Cargo.toml:
// secp256k1 = "0.25"
// sha3 = "0.10"
// rand = "0.8"
// num-bigint = "0.4"
// hex = "0.4"

extern crate secp256k1;
extern crate sha3;
extern crate rand;
extern crate num_bigint;
extern crate hex;

use secp256k1::{Secp256k1, SecretKey, PublicKey, Message};
use secp256k1::ecdsa::Signature;
use sha3::{Digest, Sha3_256};
use rand::RngCore;
use num_bigint::BigUint;

fn sha3_256_hash(msg: &str) -> [u8; 32] {
    let mut hasher = Sha3_256::new();
    hasher.update(msg.as_bytes());
    let output = hasher.finalize();
    let mut hash = [0u8; 32];
    hash.copy_from_slice(&output);
    hash
}

fn sign_ecdsa_secp256k1(msg: &str, sk: &SecretKey) -> Signature {
    let secp = Secp256k1::new();
    let hash = sha3_256_hash(msg);
    let msg = Message::from_slice(&hash).expect("Hash is 32 bytes");
    secp.sign_ecdsa(&msg, sk)
}

fn verify_ecdsa_secp256k1(msg: &str, sig: &Signature, pk: &PublicKey) -> bool {
    let secp = Secp256k1::new();
    let hash = sha3_256_hash(msg);
    let msg = Message::from_slice(&hash).expect("Hash is 32 bytes");
    secp.verify_ecdsa(&msg, sig, pk).is_ok()
}

fn main() {
    let msg = "Message for ECDSA signing";
    
    // Generate private key
    let secp = Secp256k1::new();
    let mut rng = rand::thread_rng();
    let sk = SecretKey::new(&mut rng);
    
    // Derive public key
    let pk = PublicKey::from_secret_key(&secp, &sk);
    
    // Sign message
    let sig = sign_ecdsa_secp256k1(msg, &sk);
    
    // Convert signature to r and s hex strings (like Python)
    let r_bytes = sig.r().as_ref();
    let s_bytes = sig.s().as_ref();
    let r_big = BigUint::from_bytes_be(r_bytes);
    let s_big = BigUint::from_bytes_be(s_bytes);
    let r_hex = format!("0x{}", r_big.to_str_radix(16));
    let s_hex = format!("0x{}", s_big.to_str_radix(16));
    
    // Convert private key scalar to hex string
    let sk_bytes = sk.as_ref();
    let scalar_big = BigUint::from_bytes_be(sk_bytes);
    let priv_key_hex = format!("0x{}", scalar_big.to_str_radix(16));
    
    // Print results
    println!("Message: {}", msg);
    println!("Private key: {}", priv_key_hex);
    println!("Signature: r={}, s={}", r_hex, s_hex);
    
    // Optional: verify
    let valid = verify_ecdsa_secp256k1(msg, &sig, &pk);
    println!("Signature valid: {}", valid);
}