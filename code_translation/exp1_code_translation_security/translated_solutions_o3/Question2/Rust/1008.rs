// main.rs
use k256::ecdsa::{signature::Signer, Signature, SigningKey};

/// Sign `message` with the given 32-byte SECP-256k1 private key.
///
/// Both parameters are supplied as UTF-8 strings, exactly like the
/// original Python version.
fn sign_message(message: &str, private_key: &str) -> Signature {
    // Turn inputs into raw bytes (Python did the same with .encode()).
    let msg_bytes = message.as_bytes();

    // The Python code expected *exactly* 32 bytes of key material.
    let key_bytes = private_key.as_bytes();
    assert!(
        key_bytes.len() == 32,
        "private key must be exactly 32 UTF-8 bytes"
    );

    // Build the signing key and produce a signature.
    // (k256's `SigningKey::from_bytes` will fail if the key is out of range.)
    let signing_key = SigningKey::from_bytes(key_bytes)
        .expect("invalid SECP256k1 private key");

    // Sign and return the DER-encoded ECDSA signature object.
    signing_key.sign(msg_bytes)
}

fn main() {
    let sig = sign_message("hello world",
                           "12345678901234567890123456789012");

    // Print the raw DER bytes as hex so it looks similar to Python's output.
    // (You can also use `sig.to_der()` or `sig.as_bytes()` directly.)
    println!("signature: {:02x?}", sig.to_der());
}