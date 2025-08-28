// All necessary imports are included.
// `k256` is the crate for the secp256k1 curve.
// `ecdsa` provides the generic signing functionality.
// `Signer` is the trait that allows a key to sign a message.
use k256::ecdsa::{signature::Signer, Signature, SigningKey};
use rand::rngs::OsRng; // Used for generating a cryptographically secure random key.

/// Translates the Python `signMessage` function.
///
/// # Parameters
/// - `_message`: This parameter is accepted to match the original Python function's
///   signature, but it is ignored. The `_` prefix tells the Rust compiler
///   that this variable is intentionally unused.
/// - `key`: A reference to the `SigningKey` (private key) used for signing.
///
/// # Returns
/// - An ECDSA `Signature` for the hardcoded byte string `b"message"`.
fn sign_message(_message: &[u8], key: &SigningKey) -> Signature {
    // The original Python code signs the literal b"message", ignoring the input parameter.
    // We replicate that exact behavior here.
    // The `sign` method comes from the `Signer` trait and handles the ECDSA signing process.
    // It panics on the exceedingly rare event of an RNG failure.
    key.sign(b"message")
}

fn main() {
    // 1. Generate a new random private key.
    // In a real application, you would load this from a secure location.
    let private_key = SigningKey::random(&mut OsRng);
    let public_key = private_key.verifying_key(); // The corresponding public key.

    // 2. Define the message that will be passed to the function.
    // Note: This message will be ignored by `sign_message`, as per the original code.
    let actual_message = b"This is the message we intend to sign.";

    println!("--- Key Information ---");
    // Note: Never expose your private key in a real application!
    println!("Private Key (hex): {:x}", private_key.to_bytes());
    println!("Public Key (hex):  {:x}", public_key.to_sec1_bytes());
    println!("\n--- Signing ---");
    println!("Intended message: \"{}\"", String::from_utf8_lossy(actual_message));
    println!("Actual signed data: b\"message\"");


    // 3. Call the translated function to sign the hardcoded data.
    let signature = sign_message(actual_message, &private_key);

    println!("\n--- Result ---");
    println!("Generated Signature: {}", signature);

    // 4. (Optional) Verify the signature to prove it's valid.
    // We must verify against the same data that was signed (b"message").
    use k256::ecdsa::signature::Verifier;
    assert!(public_key.verify(b"message", &signature).is_ok());
    println!("\nVerification successful: The signature is valid for the data b\"message\".");

    // This verification will fail, proving the `actual_message` was ignored.
    assert!(public_key.verify(actual_message, &signature).is_err());
    println!("Verification failed (as expected) for the intended message.");
}