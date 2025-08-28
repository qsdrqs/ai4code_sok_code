// Import the necessary components from the k256 crate.
// `SigningKey` is the private key used for signing.
// `Signature` is the result of a signing operation.
use k256::ecdsa::{SigningKey, Signature, VerifyingKey};
use k256::Secp256k1;

// The `Signer` trait provides the `.sign()` method.
// The `Verifier` trait provides the `.verify()` method.
use signature::{Signer, Verifier};

// `OsRng` is a cryptographically secure random number generator from the OS.
use rand::rngs::OsRng;

/// Generates a new SECP256k1 private key and uses it to sign a message.
///
/// # Arguments
///
/// * `message` - A byte slice representing the message to be signed.
///
/// # Returns
///
/// A tuple containing the generated `SigningKey` (private key) and the `Signature`.
fn my_function(message: &[u8]) -> (SigningKey, Signature) {
    // 1. Generate a new private key (SigningKey).
    // This is the equivalent of `ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1)`.
    // The `k256` crate is specific to the SECP256k1 curve, so we don't need to specify it.
    // We use `OsRng` to ensure the key is generated from a secure source of randomness.
    let sk = SigningKey::random(&mut OsRng);

    // 2. Sign the message with the private key.
    // This is the equivalent of `sk.sign(message)`.
    // The `sign` method is available through the `Signer` trait.
    let sig: Signature = sk.sign(message);

    (sk, sig)
}

fn main() {
    // The message to be signed, as a byte array.
    let message = b"This is a message that we will sign.";

    // Call our function to generate a key and a signature.
    let (private_key, signature) = my_function(message);

    println!("Message: \"{}\"", String::from_utf8_lossy(message));
    println!("Private Key: [REDACTED]"); // Private keys should not be printed.
    println!("Signature (hex): {}", signature);

    // --- Verification (Bonus) ---
    // To prove the signature is valid, you need the public key.
    // The public key can be derived from the private key.
    let public_key: VerifyingKey = private_key.verifying_key();

    println!("\n--- Verifying Signature ---");
    println!("Public Key (hex): {}", public_key.to_sec1_bytes().escape_hex());

    // Verify the signature against the original message and the public key.
    // This will panic if verification fails. For production code, use `is_ok()`.
    match public_key.verify(message, &signature) {
        Ok(_) => println!("✅ Signature is valid!"),
        Err(e) => println!("❌ Signature is invalid: {}", e),
    }
}