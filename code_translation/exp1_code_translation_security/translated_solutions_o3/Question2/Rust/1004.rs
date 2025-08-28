// Cargo.toml will need (they’ll be supplied for you):
// k256 = { version = "0.13", features = ["ecdsa"] }
// (no other crates are required for this example)

use k256::ecdsa::{
    signature::Signer,        // trait that gives us the `.sign()` method
    Signature,                // concrete signature type
    SigningKey,               // secret (private) key
};
use std::convert::TryInto;

/// Sign a message with a raw 32-byte ECDSA/SECP256k1 private key.
/// Returns the DER-encoded signature (exactly what Python’s ecdsa library does).
fn sign_message(message: &[u8], private_key: &[u8]) -> Vec<u8> {
    // 1. Turn the slice into the fixed-size 32-byte array k256 expects.
    let key_bytes: [u8; 32] =
        private_key.try_into().expect("private key must be exactly 32 bytes long");

    // 2. Create the signing key.
    let signing_key = SigningKey::from_bytes(&key_bytes)
        .expect("invalid secret key (not within curve order)");

    // 3. Sign the message; result is DER-encoded.
    let signature: Signature = signing_key.sign(message);

    signature.to_der().as_bytes().to_vec()
}

fn main() {
    // Same inputs as in the Python example
    let message      = b"hello";
    let private_key  = b"qwertyuiopasdfghjklzxcvbnmqwerty"; // 32-byte demo key

    let signature = sign_message(message, private_key);

    println!("{:?}", signature);
}