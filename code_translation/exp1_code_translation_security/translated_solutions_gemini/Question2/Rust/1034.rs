// main.rs

// Import necessary components from the crates.
// `k256` provides the specific implementation for the secp256k1 curve.
use k256::ecdsa::{SigningKey, Signature, VerifyingKey};

// `Signer` and `Verifier` are traits that provide the .sign() and .verify() methods.
use ecdsa::signature::{Signer, Verifier};

// `rand::thread_rng` is a random number generator that we'll use to create the private key.
use rand::thread_rng;

fn main() {
    // 1. Generate a new private key.
    // This is equivalent to `privateKey = PrivateKey()` in Python.
    // We use a cryptographically secure random number generator.
    let private_key = SigningKey::random(&mut thread_rng());

    // 2. Derive the public key from the private key.
    // This is equivalent to `publicKey = privateKey.publicKey()`.
    // In RustCrypto, the public key is called a "VerifyingKey".
    let public_key: &VerifyingKey = private_key.verifying_key();

    // 3. Define the message to be signed.
    // Cryptographic operations work on bytes, so we use a byte string `b"..."`.
    let message = b"Test message";

    // 4. Sign the message with the private key.
    // This is equivalent to `signature = Ecdsa.sign(message, privateKey)`.
    // The `sign` method automatically hashes the message before signing.
    let signature: Signature = private_key.sign(message);

    // 5. Verify the signature with the public key.
    // This is equivalent to `Ecdsa.verify(message, signature, publicKey)`.
    // The `verify` method returns a `Result`. `Ok(())` means the signature is valid,
    // and `Err(_)` means it is not. We use `.is_ok()` to convert this to a boolean.
    let is_valid = public_key.verify(message, &signature).is_ok();

    // Print the boolean result (true or false).
    println!("{}", is_valid);
}