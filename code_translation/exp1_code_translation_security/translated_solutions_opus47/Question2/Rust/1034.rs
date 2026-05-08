use k256::ecdsa::{signature::Signer, signature::Verifier, Signature, SigningKey, VerifyingKey};
use rand_core::OsRng;

fn main() {
    // Generate a new private key (secp256k1 curve, same default as starkbank-ecdsa)
    let private_key = SigningKey::random(&mut OsRng);
    // Derive the public key from the private key
    let public_key = VerifyingKey::from(&private_key);

    let message = b"Test message";

    // Sign the message (uses SHA-256 by default, matching starkbank-ecdsa)
    let signature: Signature = private_key.sign(message);

    // Verify the signature and print the result
    println!("{}", public_key.verify(message, &signature).is_ok());
}