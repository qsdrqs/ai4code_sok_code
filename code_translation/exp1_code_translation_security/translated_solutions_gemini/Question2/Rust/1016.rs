//! A function in Rust that signs a given message using a given ECDSA signing key.

use k256::ecdsa::{SigningKey, signature::Signer};
use k256::ecdsa::signature::der::ToDer; // For converting the signature to the standard DER format
use rand_core::OsRng; // For generating a random private key in our example

/// Represents potential errors during the signing process.
/// The `k256::ecdsa::Error` type conveniently handles various crypto errors.
type SignError = k256::ecdsa::Error;

/// Signs a message using a given ECDSA (SECP256k1) private key.
///
/// # Arguments
///
/// * `message` - The message to be signed, as a byte slice.
/// * `private_key_bytes` - The 32-byte private key, as a byte slice.
///
/// # Returns
///
/// A `Result` containing the DER-encoded signature as a `Vec<u8>` on success,
/// or a `SignError` on failure (e.g., if the private key is invalid).
fn sign(message: &[u8], private_key_bytes: &[u8]) -> Result<Vec<u8>, SignError> {
    // Create a new ECDSA signing key object from the raw private key bytes.
    // This is equivalent to Python's `ecdsa.SigningKey.from_string(..., curve=ecdsa.SECP256k1)`.
    // The `k256` crate is specifically for the SECP256k1 curve.
    let signing_key = SigningKey::from_slice(private_key_bytes)?;

    // Sign the message using the signing key.
    // The `sign` method automatically handles hashing the message (with SHA-256) before signing.
    // This is equivalent to Python's `signature.sign(message)`.
    let signature = signing_key.sign(message);

    // The Python library returns the signature in DER format by default.
    // We convert our signature object to DER format to match that behavior.
    // The `?` operator will propagate any encoding errors.
    Ok(signature.to_der()?.to_vec())
}

fn main() {
    // --- 1. Setup: Generate a key and a message ---
    
    // Generate a new random private key for demonstration purposes.
    // In a real application, you would load an existing private key.
    let private_key = SigningKey::random(&mut OsRng);
    let private_key_bytes = private_key.to_bytes(); // Get the raw 32 bytes

    // The message we want to sign.
    let message = b"This is a test message for ECDSA signing.";

    println!("--- Input Data ---");
    println!("Private Key (hex): {}", hex::encode(private_key_bytes));
    println!("Message: \"{}\"", String::from_utf8_lossy(message));
    println!("--------------------\n");

    // --- 2. Sign the message ---
    
    match sign(message, &private_key_bytes) {
        Ok(signed_message) => {
            println!("✅ Signing successful!");
            println!("Signature (DER-encoded hex): {}", hex::encode(&signed_message));

            // --- 3. (Bonus) Verify the signature to prove it's valid ---
            use k256::ecdsa::{VerifyingKey, signature::Verifier, Signature};
            
            let verifying_key = VerifyingKey::from(&private_key); // Get the public key
            
            // In a real scenario, you'd receive the signature and parse it.
            let signature = Signature::from_der(&signed_message).expect("Failed to parse DER signature");

            // Verify the signature against the original message.
            match verifying_key.verify(message, &signature) {
                Ok(_) => println!("✅ Verification successful: The signature is valid!"),
                Err(_) => eprintln!("❌ Verification failed: The signature is NOT valid!"),
            }
        }
        Err(e) => {
            eprintln!("❌ Error signing message: {}", e);
        }
    }
}