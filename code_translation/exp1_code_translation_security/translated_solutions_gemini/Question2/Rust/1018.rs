//
// --- Dependencies ---
//
// Add the following to your Cargo.toml file:
//
// [dependencies]
// k256 = { version = "0.13", features = ["ecdsa"] }
// rand_core = { version = "0.6", features = ["getrandom"] }
//

// Import necessary items from the crates.
// The `Signer` trait provides the .sign() method.
use k256::ecdsa::{signature::Signer, Signature, SigningKey};
// OsRng is used to generate a cryptographically secure random key for the example.
use rand_core::OsRng;

/// Signs a fixed message using a provided ECDSA (secp256k1) private key.
///
/// Note: This function mirrors the original Python code by signing the
/// hardcoded byte string b"message" and ignoring the `msg` parameter.
///
/// # Arguments
/// * `_msg`: An unused message byte slice. The underscore prefix `_` tells the
///           Rust compiler that this parameter is intentionally unused.
/// * `key`: A reference to the `SigningKey` to use for signing.
///
/// # Returns
/// The resulting `Signature`. This is a type-safe struct that can be easily
/// verified or converted to bytes.
fn sign(_msg: &[u8], key: &SigningKey) -> Signature {
    // The original Python code signs the literal b"message", not the msg parameter.
    // We do the same here. The `sign` method is part of the `Signer` trait.
    let signature: Signature = key.sign(b"message");
    signature
}

/// --- Example Usage ---
fn main() {
    // 1. Generate a new private key for signing.
    // In a real application, you would load this from a secure location
    // (e.g., a file, environment variable, or hardware security module).
    let private_key = SigningKey::random(&mut OsRng);
    println!("Generated a new random private key for the example.");

    // 2. Define a message.
    // This will be passed to our function but ignored, as in the original Python code.
    let some_message = b"this data will be ignored";

    // 3. Call the sign function to get the signature.
    let signature = sign(some_message, &private_key);

    println!("\nSuccessfully signed the fixed message: b\"message\"");
    // The Signature type has a standard Display implementation (ASN.1 DER format).
    println!("Signature: {}", signature);

    // To get the raw signature bytes (r || s), you can use .to_bytes()
    let signature_bytes = signature.to_bytes();
    println!("Signature as raw bytes (64): {:?}", signature_bytes);

    // 4. (Bonus) Verify the signature to confirm it's valid.
    use k256::ecdsa::{signature::Verifier, VerifyingKey};
    let verifying_key: &VerifyingKey = private_key.verifying_key();

    // The verify method returns Ok(()) if the signature is valid for the message.
    assert!(verifying_key.verify(b"message", &signature).is_ok());
    println!("\nVerification successful!");
}