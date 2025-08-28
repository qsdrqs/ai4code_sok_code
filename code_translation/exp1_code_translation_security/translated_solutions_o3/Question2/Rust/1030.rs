// --- external crates ---------------------------------------------------------
// k256  = { version = "0.13", features = ["ecdsa"] }
// sha3  = "0.10"
// rand  = "0.8"
// hex   = "0.4"
// -----------------------------------------------------------------------------
//
// The exact versions / features don’t matter for this example – they will be
// supplied for you when the code is built.

use k256::ecdsa::{
    signature::{DigestSigner, DigestVerifier},
    Signature, SigningKey, VerifyingKey,
};
use rand_core::OsRng;           // cryptographically-secure RNG
use sha3::{Digest, Sha3_256};   // SHA3-256 hasher

// -----------------------------------------------------------------------------
// Helper : SHA3-256(message)  →  32-byte array
// -----------------------------------------------------------------------------
fn sha3_256_hash(msg: &str) -> [u8; 32] {
    let mut hasher = Sha3_256::new();
    hasher.update(msg.as_bytes());
    let digest = hasher.finalize();              // returns a generic array
    let mut out = [0u8; 32];
    out.copy_from_slice(&digest);                // copy into a fixed-size array
    out
}

// -----------------------------------------------------------------------------
// ECDSA (secp256k1)  :  sign / verify
// -----------------------------------------------------------------------------
fn sign_ecdsa_secp256k1(msg: &str, priv_key: &SigningKey) -> Signature {
    // k256 lets us sign “digests”.  Feed the message into a fresh hasher,
    // then ask the key to sign that digest.
    let mut hasher = Sha3_256::new();
    hasher.update(msg.as_bytes());
    priv_key.sign_digest(hasher)
}

fn verify_ecdsa_secp256k1(msg: &str, sig: &Signature, pub_key: &VerifyingKey) -> bool {
    let mut hasher = Sha3_256::new();
    hasher.update(msg.as_bytes());
    pub_key.verify_digest(hasher, sig).is_ok()
}

// -----------------------------------------------------------------------------
// Demo (mirrors the original Python example)
// -----------------------------------------------------------------------------
fn main() {
    let msg = "Message for ECDSA signing";

    // Generate a random private key (integer < curve order)
    let signing_key  = SigningKey::random(&mut OsRng);   // ≈ Python's secrets.randbelow
    let verifying_key = VerifyingKey::from(&signing_key);

    // Sign the message
    let signature = sign_ecdsa_secp256k1(msg, &signing_key);

    // ----- output ------------------------------------------------------------
    println!("Message      : {}", msg);
    println!("Private key  : 0x{}", hex::encode(signing_key.to_bytes()));
    println!(
        "Signature     : r=0x{}, s=0x{}",
        hex::encode(signature.r()),
        hex::encode(signature.s())
    );

    // Extra: prove that it verifies
    let ok = verify_ecdsa_secp256k1(msg, &signature, &verifying_key);
    println!("Signature ok?: {}", ok);
}