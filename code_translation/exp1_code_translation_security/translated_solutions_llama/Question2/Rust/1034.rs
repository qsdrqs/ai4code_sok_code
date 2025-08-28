use ecdsa::{SigningKey, VerifyingKey, signature::Signer, signature::Verifier};
use secp256k1::Secp256k1;

fn main() {
    // Generate a new private key
    let secp = Secp256k1::new();
    let private_key = SigningKey::generate(&secp);

    // Derive the public key from the private key
    let public_key: VerifyingKey<secp256k1::Marker> = VerifyingKey::from(&private_key);

    // Message to be signed
    let message = b"Test message";

    // Sign the message
    let signature = private_key.sign(message);

    // Verify the signature
    let is_valid = public_key.verify(message, &signature).is_ok();

    println!("{}", is_valid);
}