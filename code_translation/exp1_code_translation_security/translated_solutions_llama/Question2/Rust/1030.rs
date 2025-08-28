use secp256k1::{Context, PublicKey, SecretKey, Message};
use sha3::{Digest, Sha3_256};
use rand::Rng;

fn sha3_256_hash(msg: &str) -> u256 {
    let mut hasher = Sha3_256::new();
    hasher.update(msg.as_bytes());
    let hash_bytes = hasher.finalize();
    u256::from_big_endian(&hash_bytes)
}

fn sign_ecdsa_secp256k1(msg: &str, priv_key: &SecretKey, context: &Context) -> (u256, u256) {
    let msg_hash = sha3_256_hash(msg);
    let (signature, recovery_id) = context.sign(&msg_hash, priv_key);
    let r = u256::from_big_endian(&signature[0..32]);
    let s = u256::from_big_endian(&signature[32..64]);
    (r, s)
}

fn verify_ecdsa_secp256k1(msg: &str, signature: (u256, u256), pub_key: &PublicKey, context: &Context) -> bool {
    let msg_hash = sha3_256_hash(msg);
    let signature_bytes = [
        signature.0.to_big_endian().as_slice(),
        signature.1.to_big_endian().as_slice(),
    ].concat();
    context.verify(&msg_hash, &signature_bytes, pub_key).is_ok()
}

fn main() {
    let context = Context::new();
    let msg = "Message for ECDSA signing";
    let mut rng = rand::thread_rng();
    let priv_key = SecretKey::random(&mut rng, &context);
    let pub_key = PublicKey::from_secret_key(&context, &priv_key);
    let signature = sign_ecdsa_secp256k1(msg, &priv_key, &context);
    println!("Message: {}", msg);
    println!("Private key: 0x{:x}", priv_key);
    println!("Public key: 0x{:x}", pub_key);
    println!("Signature: r=0x{:x}, s=0x{:x}", signature.0, signature.1);
    let valid = verify_ecdsa_secp256k1(msg, signature, &pub_key, &context);
    println!("Verification: {}", valid);
}