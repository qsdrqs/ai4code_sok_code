use ecdsa::{SigningKey, VerifyingKey, Signature, signature::{Signer, Verifier}};
use p256::NistP256;
use rand::rngs::OsRng;

fn main() {
    // Generate Keys
    let private_key = SigningKey::<NistP256>::random(&mut OsRng);
    let public_key = VerifyingKey::from(&private_key);

    let message = "My test message";

    // Generate Signature
    let signature: Signature<NistP256> = private_key.sign(message.as_bytes());

    // Verify if signature is valid
    let is_valid = public_key.verify(message.as_bytes(), &signature).is_ok();
    println!("{}", is_valid);
}