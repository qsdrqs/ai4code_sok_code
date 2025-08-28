// All necessary imports are listed here.
// `k256` is the crate for the secp256k1 curve.
// `ecdsa` types like `SigningKey` and `Signature` are re-exported by `k256`.
use k256::ecdsa::{signature::Signer, Signature, SigningKey};

// The `ToDer` trait is needed to convert the signature into the standard DER format,
// which is what the Python library does before base64 encoding.
use k256::ecdsa::signature::der::ToDer;

// `rand` is used for cryptographically secure random number generation for the private key.
use rand::rngs::OsRng;

// `base64` is used to encode the DER-formatted signature into a Base64 string.
use base64::{engine::general_purpose, Engine as _};

/// Signs a message using ECDSA with a given private key.
///
/// The process mirrors the Python library:
/// 1. The message is implicitly hashed using SHA-256 (this is the default for `k256`).
/// 2. The hash is signed using the private key.
/// 3. The resulting signature is encoded in DER format.
/// 4. The DER-encoded signature is then Base64-encoded to produce the final string.
///
/// # Arguments
/// * `message` - The message to be signed as a byte slice.
/// * `private_key` - A reference to the `SigningKey` (private key).
///
/// # Returns
/// A Base64-encoded string of the DER-formatted signature.
fn generate_ecdsa_signed_msg(message: &[u8], private_key: &SigningKey) -> String {
    // The `sign` method handles hashing the message and creating the signature.
    let signature: Signature = private_key.sign(message);

    // The Python library returns a Base64 encoded string of the signature in DER format.
    // We first convert the signature to a DER byte array.
    let der_signature = signature.to_der().unwrap();

    // Then, we encode the DER bytes into a Base64 string.
    general_purpose::STANDARD.encode(der_signature)
}

fn main() {
    // === Generate Keys ===
    // In Rust, we explicitly create a cryptographically secure random number generator.
    // `OsRng` is recommended as it gets randomness from the operating system.
    let mut rng = OsRng;

    // Generate a new private key (SigningKey).
    let private_key = SigningKey::random(&mut rng);

    // Derive the public key from the private key.
    // This is not used for signing but is included to match the original script's structure.
    let _public_key = private_key.verifying_key();

    // The message to be signed. In Rust, we work with byte slices (`&[u8]`) for crypto.
    let message = b"My test message";

    // Generate the signature and print it to the console.
    let signature_base64 = generate_ecdsa_signed_msg(message, &private_key);
    println!("{}", signature_base64);
}