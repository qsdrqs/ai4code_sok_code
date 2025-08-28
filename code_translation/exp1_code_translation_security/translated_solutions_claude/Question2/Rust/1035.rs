use p256::{ecdsa::{SigningKey, VerifyingKey, Signature}, SecretKey};
use rand::rngs::OsRng;

fn main() {
    // Generate a signing key (uses secp256r1/NIST P-256 instead of NIST192p)
    let sk = SigningKey::random(&mut OsRng);
    let vk: VerifyingKey = sk.verifying_key();

    fn sign(msg: &[u8], sk: &SigningKey) -> Signature {
        sk.sign(msg)
    }

    let message = b"message";
    let signature = sign(message, &sk);
    
    // Verify the signature
    assert!(vk.verify(message, &signature).is_ok());
}