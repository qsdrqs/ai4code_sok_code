use ecdsa::{SigningKey, VerifyingKey};
use ecdsa::signature::{Signer, SignerMut, Verifier};
use secp256k1::Secp256k1;

fn main() {
    // Generate Keys
    let secp = Secp256k1::new();
    let private_key = SigningKey::random(&secp);
    let public_key = VerifyingKey::from(&private_key);

    let message = "My test message";

    // Generate Signature
    let signature = private_key.sign(message.as_bytes());

    // Verify if signature is valid
    let is_valid = public_key.verify(message.as_bytes(), &signature).is_ok();

    println!("{}", is_valid);
}