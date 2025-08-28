use k256::ecdsa::{SigningKey, VerifyingKey, signature::{Signer, Verifier}};
use rand_core::OsRng; // For secure random number generation

fn sign(msg: &[u8], key: &SigningKey) -> Vec<u8> {
    // Get the verifying key from the signing key
    let vk = VerifyingKey::from(key);

    // Sign the message
    let signature = key.sign(msg);

    // Verify the signature
    assert!(vk.verify(msg, &signature).is_ok());

    // Return the signature as bytes
    signature.as_ref().to_vec()
}

fn test() {
    // Generate a new signing key
    let sk = SigningKey::random(&mut OsRng);

    // Sign the message and print the signature
    let signature = sign(b"message", &sk);
    println!("{:?}", signature);
}

fn main() {
    test();
}