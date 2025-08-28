// Import necessary crates and traits
use ecdsa::{
    signature::Signer, // Trait for signing functionality
    SigningKey,        // Represents a signing (private) key
    VerifyingKey,      // Represents a verifying (public) key
    Signature,         // Represents an ECDSA signature
};
use p256::NistP256;   // NIST P-256 elliptic curve
use rand_core::OsRng; // Cryptographically secure random number generator

/// Signs a message using the provided private key and verifies the signature.
///
/// # Arguments
/// - `msg`: A byte slice containing the message to be signed.
/// - `key`: A reference to the signing key.
///
/// # Returns
/// - A valid ECDSA signature.
fn sign(msg: &[u8], key: &SigningKey<NistP256>) -> Signature<NistP256> {
    // Get the corresponding verifying key
    let vk = key.verifying_key();

    // Sign the message
    let signature = key.sign(msg);

    // Verify the signature to ensure correctness
    assert!(vk.verify(msg, &signature).is_ok());

    // Return the signature
    signature
}

/// Test function to generate a signing key and sign a message.
fn test() {
    // Generate a new signing key using a secure random number generator
    let sk = SigningKey::<NistP256>::random(&mut OsRng);

    // Sign the message "message"
    let sig = sign(b"message", &sk);

    // Print the signature in debug format (as a byte slice)
    println!("Signature: {:?}", sig.as_ref());
}

/// Entry point of the program
fn main() {
    test();
}