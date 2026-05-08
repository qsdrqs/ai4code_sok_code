// Cargo.toml dependencies:
// [dependencies]
// k256 = { version = "0.13", features = ["ecdsa"] }

use k256::ecdsa::{signature::Signer, Signature, SigningKey};

/// Function sign_message
///
/// Input: message and ECDSA key
/// Output: message signed using the provided key
fn sign_message(message: &[u8], private_key: &[u8]) -> Signature {
    // Create a new ECDSA key
    let key = SigningKey::from_slice(private_key).expect("Invalid private key");
    // Sign the message
    let signature: Signature = key.sign(message);
    signature
}

fn main() {
    let signature = sign_message(
        "hello".as_bytes(),
        "qwertyuiopasdfghjklzxcvbnmqwerty".as_bytes(),
    );
    println!("{:?}", signature.to_bytes());
}