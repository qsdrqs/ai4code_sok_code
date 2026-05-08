use rand::rngs::OsRng;
use secp256k1::ecdsa::Signature;
use secp256k1::{Message, PublicKey, Secp256k1, SecretKey};
use sha3::{Digest, Sha3_256};

fn sha3_256_hash(msg: &str) -> [u8; 32] {
    let mut hasher = Sha3_256::new();
    hasher.update(msg.as_bytes());
    hasher.finalize().into()
}

fn sign_ecdsa_secp256k1(msg: &str, priv_key: &SecretKey) -> Signature {
    let secp = Secp256k1::new();
    let hash = sha3_256_hash(msg);
    let message = Message::from_digest(hash);
    secp.sign_ecdsa(&message, priv_key)
}

#[allow(dead_code)]
fn verify_ecdsa_secp256k1(msg: &str, signature: &Signature, pub_key: &PublicKey) -> bool {
    let secp = Secp256k1::new();
    let hash = sha3_256_hash(msg);
    let message = Message::from_digest(hash);
    secp.verify_ecdsa(&message, signature, pub_key).is_ok()
}

fn main() {
    // ECDSA sign message (using the curve secp256k1 + SHA3-256)
    let msg = "Message for ECDSA signing";

    // signing key, generated randomly (equivalent to secrets.randbelow(order))
    let secp = Secp256k1::new();
    let (priv_key, _pub_key) = secp.generate_keypair(&mut OsRng);

    let signature = sign_ecdsa_secp256k1(msg, &priv_key);

    println!("Message: {}", msg);
    println!("Private key: 0x{}", hex::encode(priv_key.secret_bytes()));

    // signature.serialize_compact() returns [u8; 64]: first 32 bytes = r, last 32 = s
    let compact = signature.serialize_compact();
    let r_hex = hex::encode(&compact[0..32]);
    let s_hex = hex::encode(&compact[32..64]);
    println!("Signature: r=0x{}, s=0x{}", r_hex, s_hex);
}