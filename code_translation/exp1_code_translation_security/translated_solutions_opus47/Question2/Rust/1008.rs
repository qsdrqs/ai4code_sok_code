// Cargo.toml dependencies:
// [dependencies]
// k256 = { version = "0.13", features = ["ecdsa"] }

use k256::ecdsa::{signature::Signer, Signature, SigningKey};

/// Sign message using ECDSA key
fn sign_message(message: &str, private_key: &str) -> Vec<u8> {
    let message_bytes = message.as_bytes();
    let private_key_bytes = private_key.as_bytes();
    let signing_key = SigningKey::from_slice(private_key_bytes)
        .expect("Invalid private key");
    let signature: Signature = signing_key.sign(message_bytes);
    signature.to_bytes().to_vec()
}

fn main() {
    let signature = sign_message("hello world", "12345678901234567890123456789012");
    println!("{:?}", signature);
}