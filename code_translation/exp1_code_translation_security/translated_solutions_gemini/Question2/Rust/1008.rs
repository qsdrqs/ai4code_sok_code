/*
## Dependencies ##
Add the following to your Cargo.toml file:

[dependencies]
k256 = { version = "0.13", features = ["ecdsa"] }
hex = "0.4" # Used for printing the signature in a readable hex format
*/

use k256::ecdsa::{signature::Signer, Signature, SigningKey};

/// Signs a message using a SECP256k1 private key.
///
/// # Arguments
/// * `message` - The string message to sign.
/// * `private_key_str` - A 32-byte string representing the private key.
///
/// # Returns
/// A 64-byte ECDSA signature as a `Vec<u8>`.
///
/// # Panics
/// This function will panic if the provided `private_key_str` is not 32 bytes long,
/// mirroring the crashing behavior of the original Python script with an invalid key.
fn sign_message(message: &str, private_key_str: &str) -> Vec<u8> {
    // Python: message = message.encode('utf-8')
    // Rust: In Rust, we can get a UTF-8 byte slice directly from a string.
    let message_bytes = message.as_bytes();

    // Python: private_key = private_key.encode('utf-8')
    let private_key_bytes = private_key_str.as_bytes();

    // Python: private_key = ecdsa.SigningKey.from_string(private_key, curve=ecdsa.SECP256k1)
    // Rust: We create a signing key from the raw private key bytes.
    // The `from_slice` method returns a `Result`, so we use `expect` to handle
    // potential errors, such as a key of invalid length. This mimics the
    // original script's behavior of crashing on bad input.
    let signing_key = SigningKey::from_slice(private_key_bytes)
        .expect("Failed to create signing key: private key must be 32 bytes");

    // Python: signature = private_key.sign(message)
    // Rust: The `sign` method from the `Signer` trait produces a deterministic
    // signature using RFC6979, just like the Python library.
    let signature: Signature = signing_key.sign(message_bytes);

    // Python: return signature
    // Rust: The Python function returns a `bytes` object. We return a `Vec<u8>`
    // which is a heap-allocated byte vector, the idiomatic equivalent.
    signature.to_vec()
}

fn main() {
    let message = "hello world";
    let private_key = "12345678901234567890123456789012";

    // Call the function to sign the message
    let signature_bytes = sign_message(message, private_key);

    // The Python `print` on a bytes object shows a representation like `b'...'`.
    // The Rust `Debug` formatter (`:?`) for a byte slice is similar.
    // However, printing the hex representation is more common and readable for signatures.
    // The output will be identical to the Python version due to deterministic signing (RFC6979).
    println!("Signature (raw bytes): {:?}", signature_bytes);
    println!("Signature (hex): {}", hex::encode(&signature_bytes));
}