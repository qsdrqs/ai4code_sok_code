//! sign message ecdsa

// In Rust, dependencies are managed by Cargo and imported with `use`.
// `k256` is the Rust equivalent of Python's `ecdsa` library for the SECP256k1 curve.
use k256::ecdsa::{
    signature::Signer, // The trait that provides the `.sign()` method
    Signature,         // The ECDSA signature type
    SigningKey,        // The ECDSA signing key type (private key)
};

// The `k256::ecdsa::Error` type is used for error handling, similar to exceptions in Python.
use k256::ecdsa::Error as EcdsaError;

/// Signs a message using a private key with the SECP256k1 curve.
///
/// This function is a direct translation of the Python `sign_message` function.
///
/// # Arguments
///
/// * `message`: A byte slice representing the message to be signed.
/// * `private_key`: A 32-byte slice representing the private key.
///
/// # Returns
///
/// A `Result` containing the raw 64-byte signature as a `Vec<u8>` on success,
/// or an `EcdsaError` on failure.
pub fn sign_message(message: &[u8], private_key: &[u8]) -> Result<Vec<u8>, EcdsaError> {
    // Python: sk = SigningKey.from_string(private_key, curve=SECP256k1)
    //
    // In Rust, we create a `SigningKey` from a byte slice.
    // This will fail if the key is not a valid 32-byte private key,
    // so we use `?` to propagate the error.
    let signing_key = SigningKey::from_slice(private_key)?;

    // Python: signature = sk.sign(message)
    //
    // The `.sign()` method from the `Signer` trait signs the raw message bytes.
    // It does NOT hash the message first, which matches the behavior of the
    // Python `ecdsa` library's `sign` method.
    let signature: Signature = signing_key.sign(message);

    // Python: return signature
    //
    // The Python function returns the raw signature as `bytes`.
    // In Rust, we convert the `Signature` struct to a byte vector (`Vec<u8>`).
    // The standard format is a 64-byte array (r || s).
    Ok(signature.to_bytes().to_vec())
}

/// Main function to demonstrate and test the `sign_message` function.
fn main() {
    // 1. For demonstration, we generate a new random private key.
    // In a real application, you would load or receive an existing key.
    let private_key = SigningKey::random(&mut rand::thread_rng());
    let private_key_bytes = private_key.to_bytes(); // This is a GenericArray<u8, 32>

    // 2. Define the message to be signed.
    let message = b"This is a test message.";

    println!("Private Key (hex): {}", hex::encode(private_key_bytes));
    println!("Message: \"{}\"", String::from_utf8_lossy(message));
    println!("---");

    // 3. Call the sign_message function and handle the result.
    match sign_message(message, &private_key_bytes) {
        Ok(signature_bytes) => {
            println!("Successfully signed message!");
            println!("Signature (64 bytes, hex): {}", hex::encode(&signature_bytes));
            println!("Signature length: {} bytes", signature_bytes.len());

            // 4. (Optional but recommended) Verify the signature to confirm correctness.
            // We need the public key, which can be derived from the private key.
            use k256::ecdsa::{signature::Verifier, VerifyingKey};

            let signature = Signature::from_slice(&signature_bytes)
                .expect("Failed to deserialize signature");
            let verifying_key = VerifyingKey::from(&private_key); // Get public key

            assert!(verifying_key.verify(message, &signature).is_ok());

            println!("---");
            println!("Verification successful!");
        }
        Err(e) => {
            eprintln!("Error signing message: {}", e);
        }
    }
}