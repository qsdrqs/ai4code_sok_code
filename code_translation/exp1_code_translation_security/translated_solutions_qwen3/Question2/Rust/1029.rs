use p384::{SecretKey, VerifyingKey, ecdsa::Signature};
use rand::rngs::OsRng;
use hex::encode;
use signature::Signer;

/// Generates a key pair, signs a message, verifies the signature, and returns the signature.
fn ecdsa_key(message: &[u8], _key: &SecretKey) -> Signature {
    let sk = SecretKey::random(&mut OsRng);
    let vk = VerifyingKey::from(&sk);
    let signature = sk.sign(message);
    let valid = vk.verify(message, &signature).is_ok();
    println!("{}", valid);
    signature
}

/// Verifies a signature using the given public key.
fn ecdsa_verify(signature: &Signature, message: &[u8], vk: &VerifyingKey) -> bool {
    vk.verify(message, signature).is_ok()
}

/// Generates and returns a new ECDSA key pair.
fn ecdsa_genkey() -> (SecretKey, VerifyingKey) {
    let sk = SecretKey::random(&mut OsRng);
    let vk = VerifyingKey::from(&sk);
    (sk, vk)
}

/// Signs a message using the given private key.
fn ecdsa_sign(sk: &SecretKey, message: &[u8]) -> Signature {
    sk.sign(message)
}

/// Verifies a signature using the given public key.
fn ecdsa_verify_key(vk: &VerifyingKey, signature: &Signature, message: &[u8]) -> bool {
    vk.verify(message, signature).is_ok()
}

fn main() {
    let message = b"This is a test message";
    let (sk, vk) = ecdsa_genkey();

    // Print private and public keys as hex strings
    println!("{}", encode(sk.to_bytes().as_slice()));
    println!("{}", encode(vk.as_ref()));

    // Sign the message
    let signature = ecdsa_sign(&sk, message);
    println!("{}", encode(signature.as_ref()));

    // Verify the signature
    println!("{}", ecdsa_verify_key(&vk, &signature, message));
}