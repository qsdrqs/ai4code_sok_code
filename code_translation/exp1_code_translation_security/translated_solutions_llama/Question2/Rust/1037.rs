use ecdsa::{SigningKey, VerifyingKey, signature::Signer};
use p384;

fn main() {
    // Generate a new signing key
    let sk = SigningKey::random(&p384::NIST_P384);

    // Get the corresponding verifying key
    let vk = VerifyingKey::from(&sk);

    // Sign a message
    let signature = sign_message(&sk, b"testing");

    // Verify the signature
    println!("{}", vk.verify(b"testing", &signature).is_ok());
}

fn sign_message(key: &SigningKey<p384::NistP384>, message: &[u8]) -> Vec<u8> {
    key.sign(message).to_bytes().to_vec()
}