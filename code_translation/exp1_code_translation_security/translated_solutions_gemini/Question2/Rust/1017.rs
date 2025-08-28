// All necessary dependencies are brought into scope with `use`.
// `ecdsa` provides the generic signing traits.
// `k256` is a specific implementation of the secp256k1 elliptic curve.
// `sha2` is used for hashing the message before signing, which is standard practice.
// `rand_core` is used for generating a secure random signing key.
use ecdsa::{signature::Signer, SigningKey};
use k256::ecdsa::{Signature, VerifyingKey};
use k256::Secp256k1;
use sha2::{Digest, Sha256};
use rand::rngs::OsRng; // OsRng is a secure random number generator

/// Function in Rust that signs a given message using a given ECDSA signing key.
///
/// # Arguments
/// * `message` - A byte slice representing the message to be signed.
/// * `signing_key` - A reference to the ECDSA signing key.
///
/// # Returns
/// * `Signature` - The resulting ECDSA signature.
fn sign_message(message: &[u8], signing_key: &SigningKey<Secp256k1>) -> Signature {
    // In standard cryptographic practice, we sign the *hash* of the message,
    // not the raw message itself. Here we use SHA-256.
    let mut hasher = Sha256::new();
    hasher.update(message);
    let digest = hasher.finalize();

    // Sign the hashed message using the signing key.
    // The `sign` method comes from the `Signer` trait.
    let signature: Signature = signing_key.sign(&digest);

    // Return the signature
    signature
}

fn main() {
    // --- Setup ---
    // 1. Generate a new random private signing key.
    //    In a real application, you would load this from a secure storage.
    let signing_key = SigningKey::random(&mut OsRng);

    // 2. Define the message to be signed.
    let message = b"This is a test message.";

    // --- Signing ---
    println!("Original Message: {}", std::str::from_utf8(message).unwrap());
    
    // Call our function to sign the message
    let signature = sign_message(message, &signing_key);

    // Signatures are binary data, so we often represent them in hexadecimal for printing.
    println!("Signature (hex): {}", hex::encode(signature.to_bytes()));

    // --- Verification (to prove it worked) ---
    // 1. Get the public verifying key from the private signing key.
    let verifying_key: VerifyingKey = signing_key.verifying_key();

    // 2. Hash the message again for verification. The verifier must use the same hash function.
    let mut hasher = Sha256::new();
    hasher.update(message);
    let digest = hasher.finalize();

    // 3. Verify the signature against the message digest.
    use ecdsa::signature::Verifier;
    match verifying_key.verify(&digest, &signature) {
        Ok(_) => println!("\n✅ Signature is valid!"),
        Err(_) => println!("\n❌ Signature is invalid!"),
    }
}