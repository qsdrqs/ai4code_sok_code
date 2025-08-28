//! Function sign_message
//!
//! Input: message and ECDSA key
//! Output: message signed using the provided key

// Import necessary components from our dependencies.
// `k256` provides the SECP256k1 curve implementation.
// The `ecdsa` feature gives us access to `SigningKey` and `Signature`.
use k256::ecdsa::{signature::Signer, Signature, SigningKey};
// `sha2` is used to hash the message before signing.
use sha2::{Digest, Sha256};

/// Signs a message using a private key with ECDSA (SECP256k1).
///
/// # Arguments
///
/// * `message` - The message to sign as a byte slice.
/// * `private_key_bytes` - The 32-byte private key as a byte slice.
///
/// # Returns
///
/// A `Result` containing the `Signature` on success, or an error string on failure.
fn sign_message(message: &[u8], private_key_bytes: &[u8]) -> Result<Signature, String> {
    // 1. Create a new ECDSA signing key from the raw private key bytes.
    // The `from_bytes` method expects a 32-byte array, so we try to convert the slice.
    // This will fail if the private key is not exactly 32 bytes long.
    let signing_key = SigningKey::from_bytes(private_key_bytes.into())
        .map_err(|e| format!("Failed to create signing key: {}", e))?;

    // 2. Hash the message. ECDSA signs a hash of the message, not the message itself.
    // The Python library does this implicitly; in Rust, we do it explicitly.
    let mut hasher = Sha256::new();
    hasher.update(message);
    let digest = hasher.finalize();

    // 3. Sign the hashed message.
    // The `sign` method from the `Signer` trait takes the digest and returns the signature.
    let signature: Signature = signing_key.sign(&digest);

    Ok(signature)
}

fn main() {
    // The same message and private key from the Python example.
    // In Rust, b"..." creates a byte slice `&[u8]`.
    let message = b"hello";
    let private_key = b"qwertyuiopasdfghjklzxcvbnmqwerty"; // 32 bytes

    // Call the signing function and handle the result.
    match sign_message(message, private_key) {
        Ok(signature) => {
            // The signature is a struct. To view it as bytes, we use `.to_bytes()`.
            // We use the `hex` crate to print it in a readable hexadecimal format.
            println!("Message: {}", String::from_utf8_lossy(message));
            println!("Private Key: {}", String::from_utf8_lossy(private_key));
            println!("Signature (hex): {}", hex::encode(signature.to_bytes()));
        }
        Err(e) => {
            eprintln!("Error signing message: {}", e);
        }
    }
}