// Dependencies: p256, rand, hex
// Cargo.toml should have:
// [dependencies]
// p256 = { version = "0.10", features = ["ecdsa", "rand"] }
// rand = "0.8"
// hex = "0.4"

use p256::ecdsa::{SigningKey, VerifyingKey};
use rand::rngs::OsRng;
use hex;

/// Signs a message using the provided ECDSA private key and returns the signature in JSON format.
fn generate_ecdsa_signed_msg(message: &str, private_key: &SigningKey) -> String {
    let signature = private_key.sign(message.as_bytes());
    let sig_bytes = signature.to_bytes();

    let r_hex = hex::encode(&sig_bytes[0..32]);
    let s_hex = hex::encode(&sig_bytes[32..64]);

    format!("{{\"r\": \"{}\", \"s\": \"{}\"}}", r_hex, s_hex)
}

fn main() {
    // Generate ECDSA key pair
    let private_key = SigningKey::random(&mut OsRng);
    let public_key = private_key.verifying_key();

    // Message to sign
    let message = "My test message";

    // Sign and print the result
    let signature = generate_ecdsa_signed_msg(message, &private_key);
    println!("{}", signature);
}