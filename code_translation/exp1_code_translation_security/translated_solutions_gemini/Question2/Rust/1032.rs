//! A program that signs a given message using a given ECDSA signing key.

// Import necessary items from the crates.
// `k256` provides the SECP256k1 curve implementation.
// `ecdsa` types are re-exported by `k256`.
use k256::ecdsa::{signature::Signer, Signature, SigningKey};
// `sha2` is used for hashing the message.
use sha2::{Digest, Sha256};

/// Signs a message using a given ECDSA (SECP256k1) private key.
///
/// # Arguments
///
/// * `message` - The message string to sign.
/// * `private_key_bytes` - A byte slice representing the 32-byte private key.
///
/// # Returns
///
/// A `Result` containing the ECDSA signature on success, or an error if signing fails.
fn sign_message(message: &str, private_key_bytes: &[u8]) -> Result<Signature, k256::ecdsa::Error> {
    // 1. Hash the message using SHA-256.
    // The Python code encodes the message to UTF-8 first, which `as_bytes()` does in Rust.
    let mut hasher = Sha256::new();
    hasher.update(message.as_bytes());
    let message_hash = hasher.finalize();

    // 2. Create a new ECDSA private key object from the raw bytes.
    // `SigningKey::from_slice` will return an error if the key is invalid (e.g., wrong length).
    let private_key = SigningKey::from_slice(private_key_bytes)?;

    // 3. Sign the message hash.
    // The `sign` method comes from the `signature::Signer` trait, which `SigningKey` implements.
    // It produces a compact 64-byte (r,s) signature.
    let signature: Signature = private_key.sign(&message_hash);

    // 4. Return the signature.
    Ok(signature)
}

/// Test function.
fn main() {
    let message = "Hello, world!";
    // The private key from the Python example, as a hex string.
    let private_key_hex = "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725";

    // In Python, `binascii.unhexlify` is used. In Rust, the `hex` crate provides `decode`.
    // `unwrap()` is used for simplicity in this example; in production code, you should
    // handle the Result properly (e.g., with `match` or `?`).
    let private_key_bytes = hex::decode(private_key_hex).expect("Failed to decode hex private key.");

    // Call the signing function and handle the result.
    match sign_message(message, &private_key_bytes) {
        Ok(signature) => {
            // The Python `print(signature)` shows the raw bytes representation.
            // For better readability, we'll print the signature as a hex string.
            // The `.to_bytes()` method serializes the signature into a byte array.
            println!("Signature (hex): {}", hex::encode(signature.to_bytes()));
        }
        Err(e) => {
            eprintln!("Error signing message: {}", e);
        }
    }
}