use p256::ecdsa::{SigningKey, Signature};
use ecdsa::signature::Signer; // Import the Signer trait
use rand::rngs::OsRng; // Cryptographically secure random number generator

/// Signs a message using a private key (SigningKey).
///
/// # Arguments
/// * `message` - A byte slice representing the message to sign.
/// * `key` - A reference to the `SigningKey` (private key).
///
/// # Returns
/// An ECDSA `Signature`.
fn sign(message: &[u8], key: &SigningKey) -> Signature {
    // The `sign` method comes from the `Signer` trait.
    // It takes the message as bytes and returns a digital signature.
    key.sign(message)
}

fn main() {
    // In Python: SigningKey.generate()
    // In Rust, we need to provide a cryptographically secure random number generator.
    // OsRng is the recommended choice for this.
    let private_key = SigningKey::random(&mut OsRng);

    // The message to be signed.
    let message = "something";

    // In Python: key.sign(message.encode())
    // We call our sign function, converting the message string to bytes with `.as_bytes()`.
    let signature = sign(message.as_bytes(), &private_key);

    // In Python: print(signature)
    // The signature object is a set of bytes. For a readable output,
    // we encode it as a hexadecimal string.
    println!("Original Message: {}", message);
    println!("Signature (hex): {}", hex::encode(signature.to_bytes()));

    // --- Optional: Verification (to prove the signature is valid) ---
    // To verify a signature, you need the corresponding public key.
    use p256::ecdsa::VerifyingKey;
    use ecdsa::signature::Verifier;

    let public_key = VerifyingKey::from(&private_key); // Get public key from private key

    // The `verify` method returns `Ok(())` if the signature is valid.
    assert!(public_key.verify(message.as_bytes(), &signature).is_ok());

    println!("\nSignature verified successfully!");
}