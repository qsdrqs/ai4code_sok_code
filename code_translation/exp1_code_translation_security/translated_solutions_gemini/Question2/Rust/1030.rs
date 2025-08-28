use secp256k1::{Secp256k1, Message, SecretKey, PublicKey, Signature};
use sha3::{Digest, Sha3_256};
use rand::rngs::OsRng; // A cryptographically secure random number generator

/// Hashes a message using SHA3-256 and returns the 32-byte digest.
/// This is equivalent to Python's `hashlib.sha3_256(msg.encode("utf8")).digest()`.
/// The secp256k1 library expects the hash as a 32-byte array, not a large integer.
fn sha3_256_hash(msg: &str) -> [u8; 32] {
    let mut hasher = Sha3_256::new();
    hasher.update(msg.as_bytes());
    hasher.finalize().into()
}

/// Signs a message using a private key on the secp256k1 curve.
/// The message is first hashed with SHA3-256.
fn sign_ecdsa_secp256k1(
    secp: &Secp256k1<secp256k1::All>,
    msg: &str,
    priv_key: &SecretKey,
) -> Signature {
    let msg_hash = sha3_256_hash(msg);
    // The Message struct is a wrapper around the 32-byte hash.
    let message = Message::from_slice(&msg_hash).expect("32 bytes, so this can't fail");
    secp.sign_ecdsa(&message, priv_key)
}

/// Verifies an ECDSA signature.
/// This function is included for completeness, mirroring the Python version.
#[allow(dead_code)] // We don't call this in main, so suppress the warning.
fn verify_ecdsa_secp256k1(
    secp: &Secp256k1<secp256k1::All>,
    msg: &str,
    signature: &Signature,
    pub_key: &PublicKey,
) -> bool {
    let msg_hash = sha3_256_hash(msg);
    let message = Message::from_slice(&msg_hash).expect("32 bytes, so this can't fail");
    secp.verify_ecdsa(&message, signature, pub_key).is_ok()
}

fn main() {
    // The secp256k1 library requires a context object to be created.
    // The `All` generic parameter enables all functionality (signing and verification).
    let secp = Secp256k1::new();

    // ECDSA sign message (using the curve secp256k1 + SHA3-256)
    let msg = "Message for ECDSA signing";

    // Generate a new random private key.
    // This is the equivalent of `secrets.randbelow(secp256k1_generator.order())`.
    // The `generate_keypair` function ensures the key is valid for the curve.
    let (priv_key, pub_key) = secp.generate_keypair(&mut OsRng);

    // Sign the message with the private key.
    let signature = sign_ecdsa_secp256k1(&secp, msg, &priv_key);

    // Print the results, formatted to look like the Python output.
    println!("Message: {}", msg);
    // The `SecretKey` struct has a secure `display_secret` method.
    println!("Private key: {}", priv_key.display_secret());

    // The signature object contains r and s values. We can get them by serializing.
    let serialized_sig = signature.serialize_compact();
    let r = &serialized_sig[0..32];
    let s = &serialized_sig[32..64];
    println!("Signature: r=0x{}, s=0x{}", hex::encode(r), hex::encode(s));

    // --- Verification (Bonus) ---
    // The original Python script didn't verify, but it's good practice to show it works.
    let is_valid = verify_ecdsa_secp256k1(&secp, msg, &signature, &pub_key);
    println!("\n--- Verification ---");
    println!("Public key: 0x{}", hex::encode(pub_key.serialize()));
    println!("Is signature valid? {}", is_valid);
    assert!(is_valid, "Signature verification failed!");
    println!("Signature verified successfully!");
}