// Equivalent of the given Python example.
//
// The necessary crates (sha2, secp256k1 and hex) are assumed to be
// available in Cargo.toml – no need to add them here.

use sha2::{Digest, Sha256};
use secp256k1::{ecdsa::Signature, Message, Secp256k1, SecretKey};

/// Sign `message` with the provided raw 32-byte ECDSA / secp256k1
/// private key.
///
/// On success the DER-encoded signature is returned.
fn sign_message(message: &str, private_key_bytes: &[u8]) -> Result<Signature, secp256k1::Error> {
    // ── 1. SHA-256 hash of the UTF-8 encoded message ────────────────────────────
    let message_hash = Sha256::digest(message.as_bytes());

    // secp256k1 expects the hash as a `Message` object (exactly 32 bytes)
    let msg = Message::from_slice(&message_hash)?;

    // ── 2. Build a secret key object from the raw bytes ─────────────────────────
    let secret_key = SecretKey::from_slice(private_key_bytes)?;

    // ── 3. Sign the hash ────────────────────────────────────────────────────────
    let secp = Secp256k1::new();
    Ok(secp.sign_ecdsa(&msg, &secret_key))
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    let message = "Hello, world!";

    // Same private key as in the Python example (hex -> bytes)
    let private_key_hex = "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725";
    let private_key_bytes = hex::decode(private_key_hex)?;

    let signature = sign_message(message, &private_key_bytes)?;

    // Print the DER-encoded signature as a hex string
    println!("Signature (DER, hex): {}", hex::encode(signature.serialize_der()));

    Ok(())
}