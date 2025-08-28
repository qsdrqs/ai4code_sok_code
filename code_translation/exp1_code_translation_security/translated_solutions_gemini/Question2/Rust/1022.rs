// src/main.rs

// Import the necessary components from the crates.
// `k256` is for the secp256k1 curve.
// `ecdsa` types like `SigningKey` (private key), `VerifyingKey` (public key),
// and `Signature` are brought into scope.
use k256::ecdsa::{Signature, SigningKey, VerifyingKey};

// The `Signer` and `Verifier` traits provide the .sign() and .verify() methods.
use signature::{Signer, Verifier};

// `OsRng` is a cryptographically secure random number generator from the OS.
use rand::rngs::OsRng;

fn main() {
    // --- Generate Keys ---
    // In Python:
    // privateKey = PrivateKey()
    // publicKey = privateKey.publicKey()

    // In Rust, we first need a source of randomness.
    let mut rng = OsRng;

    // Generate a new private key (called a `SigningKey` in RustCrypto).
    let private_key: SigningKey = SigningKey::random(&mut rng);

    // Derive the public key (`VerifyingKey`) from the private key.
    let public_key: &VerifyingKey = private_key.verifying_key();

    // --- Message ---
    // In Python:
    // message = "My test message"

    // In Rust, cryptographic operations work on byte slices (`&[u8]`), not strings.
    // The `b""` syntax creates a byte string literal.
    let message: &[u8] = b"My test message";

    // --- Generate Signature ---
    // In Python:
    // signature = Ecdsa.sign(message, privateKey)

    // The `sign` method is provided by the `Signer` trait.
    // It takes the message as a byte slice and returns a `Signature` object.
    let signature: Signature = private_key.sign(message);

    // --- Verify if signature is valid ---
    // In Python:
    // print(Ecdsa.verify(message, signature, publicKey))

    // The `verify` method is provided by the `Verifier` trait.
    // It returns a `Result`: `Ok(())` if the signature is valid, `Err(_)` otherwise.
    // We can use `.is_ok()` to convert this result into a boolean (`true` or `false`).
    let is_valid: bool = public_key.verify(message, &signature).is_ok();

    // Print the boolean result, which will be `true`.
    println!("{}", is_valid);
}