use k256::ecdsa::{SigningKey, VerifyingKey, signature::{Signer, Verifier}};
use k256::elliptic_curve::rand_core::OsRng;

fn sign_message(sk: &SigningKey, vk: &VerifyingKey, message: &[u8]) -> bool {
    // Sign the message
    let signature = sk.sign(message);

    // Verify the signature
    vk.verify(message, &signature).is_ok()
}

fn main() {
    // Generate a signing key (private key) using SECP256k1 curve
    let signing_key = SigningKey::random(&mut OsRng);
    let verifying_key = VerifyingKey::from(&signing_key);

    // Message to be signed
    let message = b"..";

    // Sign and verify the message
    let is_valid = sign_message(&signing_key, &verifying_key, message);

    println!("{}", is_valid);
}