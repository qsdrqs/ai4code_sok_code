use rand::rngs::OsRng;
use p192::ecdsa::{SigningKey, VerifyingKey};
use sha1::Digest;

fn main() {
    // Generate a random signing key using the P-192 curve
    let mut os_rng = OsRng;
    let sk = SigningKey::random(&mut os_rng);

    // Derive the corresponding verifying key
    let vk = sk.verifying_key();

    // Message to be signed
    let msg = b"message";

    // Hash the message using SHA-1
    let hash = sha1::Sha1::digest(msg);

    // Sign the hash
    let signature = sk.sign(&hash);

    // Verify the signature
    assert!(vk.verify(&hash, &signature).is_ok());
}