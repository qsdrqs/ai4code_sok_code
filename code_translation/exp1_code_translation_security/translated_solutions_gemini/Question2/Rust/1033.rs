// Write a function in Rust that signs a given message using a given ECDSA signing key.
// Note: The original Python function's 'key' parameter specifies the curve, not a pre-existing key.
// This Rust version is hardcoded to use the P-256 curve (equivalent to NIST256p).

use p256::ecdsa::{
    signature::Signer, // The Signer trait to sign messages
    Signature,         // The ECDSA signature type
    SigningKey,        // The ECDSA signing key
};
use rand::rngs::OsRng; // A cryptographically secure random number generator

/// Signs a given message using a newly generated P-256 ECDSA signing key.
///
/// NOTE: This function replicates the Python example's behavior by generating a
/// new, random private key for each call. In a real-world application, you would
/// typically load or persist a single signing key instead of creating an ephemeral one.
///
/// # Arguments
///
/// * `message`: The message to be signed as a byte slice.
///
/// # Returns
///
/// An ECDSA signature for the message. The public key is discarded.
///
fn sign_message(message: &[u8]) -> Signature {
    // Generate a random private key using a cryptographically secure RNG.
    // The curve (P-256) is determined by the type `p256::ecdsa::SigningKey`.
    let signing_key = SigningKey::random(&mut OsRng);

    // The Python code gets the verifying key here, but it's not used for signing.
    // In Rust, we can skip this step as it's unnecessary for the function's output.
    // let verifying_key = signing_key.verifying_key();

    // Sign the message and return the signature.
    // The `sign` method returns a `Signature` type.
    signing_key.sign(message)
}

/// Main function to demonstrate the usage of `sign_message`.
fn main() {
    let message_to_sign = b"This is a test message.";

    // Call the function to get a signature.
    let signature = sign_message(message_to_sign);

    println!("Message: \"{}\"", String::from_utf8_lossy(message_to_sign));
    
    // The `Signature` type has a `Display` implementation that shows its
    // ASN.1 DER encoding in hexadecimal format, which is a standard way to represent it.
    println!("Generated Signature (DER hex): {}", signature);

    // Because the signing key is created inside `sign_message` and then discarded,
    // we cannot get the public key here to verify the signature. A real-world
    // function would typically take a signing key as an argument.
    println!("\nNote: Verification is not possible here because the key is ephemeral.");
}