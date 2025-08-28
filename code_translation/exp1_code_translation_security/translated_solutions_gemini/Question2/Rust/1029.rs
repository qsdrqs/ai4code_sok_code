use p384::ecdsa::{SigningKey, Signature, VerifyingKey};
use signature::{Signer, Verifier};

// Type aliases for clarity, matching the NIST P-384 curve.
type EcdsaSigningKey = SigningKey;
type EcdsaVerifyingKey = VerifyingKey;
type EcdsaSignature = Signature;

/// Generates a key, signs a message, verifies it, prints the result,
/// and returns the signature.
/// The `key` parameter from the Python version was unused, so it's omitted here.
fn ecdsa_key(message: &[u8]) -> EcdsaSignature {
    // Generate a new random signing key.
    let sk = EcdsaSigningKey::random(&mut rand::thread_rng());
    let vk = sk.verifying_key();

    // Sign the message
    let signature: EcdsaSignature = sk.sign(message);

    // Verify the signature and print the result (true/false)
    let is_valid = vk.verify(message, &signature).is_ok();
    println!("Verification inside ecdsa_key: {}", is_valid);

    signature
}

/// Verifies a signature against a message and verifying key.
/// Note: The argument order is different from the Python version for consistency.
fn ecdsa_verify(signature: &EcdsaSignature, message: &[u8], vk: &EcdsaVerifyingKey) -> bool {
    vk.verify(message, signature).is_ok()
}

/// Generates a new P-384 key pair.
fn ecdsa_genkey() -> (EcdsaSigningKey, EcdsaVerifyingKey) {
    let sk = EcdsaSigningKey::random(&mut rand::thread_rng());
    let vk = *sk.verifying_key(); // Dereference to get an owned key
    (sk, vk)
}

/// Signs a message with the given signing key.
fn ecdsa_sign(sk: &EcdsaSigningKey, message: &[u8]) -> EcdsaSignature {
    sk.sign(message)
}

/// Verifies a signature using the given verifying key.
fn ecdsa_verify_key(vk: &EcdsaVerifyingKey, signature: &EcdsaSignature, message: &[u8]) -> bool {
    vk.verify(message, signature).is_ok()
}

fn main() {
    let message = b"This is a test message";

    // Generate keys
    let (sk, vk) = ecdsa_genkey();

    // Print keys as hex strings
    // sk.to_bytes() gives the raw private scalar.
    println!("Signing Key (Private): {}", hex::encode(sk.to_bytes()));
    // vk.to_encoded_point(false) gives the uncompressed public key.
    println!("Verifying Key (Public): {}", hex::encode(vk.to_encoded_point(false)));

    // Sign the message
    let signature = ecdsa_sign(&sk, message);

    // Print the signature as a hex string
    // The signature is composed of two scalars (r, s). to_bytes() serializes them.
    println!("Signature: {}", hex::encode(signature.to_bytes()));

    // Verify the signature
    let is_valid = ecdsa_verify_key(&vk, &signature, message);
    println!("Verification in main: {}", is_valid);

    // Demonstrate the other functions
    println!("\n--- Demonstrating other functions ---");
    let _ = ecdsa_key(b"another message");
    let verification_result = ecdsa_verify(&signature, message, &vk);
    println!("Result from ecdsa_verify: {}", verification_result);
}