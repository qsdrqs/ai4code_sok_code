use ecdsa::{SigningKey, VerifyingKey, signature::Signer};
use rand::thread_rng;

fn main() {
    // Generate a new signing key (uses NIST P-256 by default)
    let sk = SigningKey::random(&mut thread_rng());
    let vk: VerifyingKey = sk.verifying_key();

    // Define a function to sign a message
    fn sign(msg: &[u8], sk: &SigningKey) -> Vec<u8> {
        let signature = sk.sign(msg);
        signature.to_bytes().to_vec()
    }

    // Test the signature verification
    let msg = b"message";
    let signature = sign(msg, &sk);
    assert!(vk.verify(msg, &signature).is_ok());
}