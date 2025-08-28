use ed25519_dalek::{Signature, Signer, SigningKey};
use rand::rngs::OsRng; // A cryptographically secure random number generator

/// Signs a message using a provided Ed25519 signing key.
///
/// # Arguments
/// * `message` - A byte slice representing the message to be signed.
/// * `signing_key` - A reference to the `SigningKey` that will perform the signing.
///
/// # Returns
/// An Ed25519 `Signature`.
fn sign_message(message: &[u8], signing_key: &SigningKey) -> Signature {
    // The `sign` method is provided by the `Signer` trait, which `SigningKey` implements.
    // This is a direct equivalent to `signing_key.sign(message)` in Python.
    signing_key.sign(message)
}

fn main() {
    // 1. Generate a new signing key.
    // In a real application, you would load a secret key from a secure location.
    let mut csprng = OsRng;
    let signing_key = SigningKey::generate(&mut csprng);

    // 2. Define the message to be signed.
    let message: &[u8] = b"This is a message that we want to sign.";

    // 3. Call the function to sign the message.
    let signature = sign_message(message, &signing_key);

    println!("Message: \"{}\"", String::from_utf8_lossy(message));
    println!("Signature (hex): {}", signature);

    // (Optional) For demonstration, let's verify the signature.
    // The public key is derived from the signing key.
    let verifying_key = signing_key.verifying_key();
    match verifying_key.verify(message, &signature) {
        Ok(_) => println!("Signature is valid!"),
        Err(_) => println!("Signature is invalid!"),
    }
}