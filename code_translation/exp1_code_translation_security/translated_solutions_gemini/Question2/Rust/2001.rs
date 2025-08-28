// All necessary dependencies are brought into scope.
// These correspond to the `from cryptography.hazmat...` imports in Python.
use p256::ecdsa::{SigningKey, Signature, VerifyingKey};
use p256::NistP256;
use signature::Signer;
use ecdsa::signature::der::ToDer; // Trait to convert signature to DER format
use rand_core::OsRng; // For generating a random private key in the example

/// Signs a message with the given ECDSA private key.
///
/// This function is an equivalent of the Python snippet. It takes a message
/// and a private key, and returns the signature as a vector of bytes.
///
/// The signing process implicitly uses SHA-256 as the hash function,
/// which is hardwired into the `p256::ecdsa::SigningKey`'s implementation
/// of the `Signer` trait. This matches `ec.ECDSA(hashes.SHA256())`.
///
/// The signature is returned in DER format to match the default behavior
/// of the Python `cryptography` library.
///
/// # Arguments
///
/// * `message`: A byte slice representing the message to be signed.
/// * `private_key`: A reference to the `SigningKey` (the private key).
///
/// # Returns
///
/// A `Vec<u8>` containing the DER-encoded signature.
fn sign(message: &[u8], private_key: &SigningKey) -> Vec<u8> {
    // The `sign` method from the `Signer` trait creates the signature.
    // It handles hashing the message with SHA-256 internally.
    let signature: Signature = private_key.sign(message);

    // The Python library returns the signature as raw bytes in DER format.
    // We use the `to_der` method to get the same format.
    // .to_vec() converts the DER document into a byte vector.
    signature.to_der().unwrap().to_vec()
}

fn main() {
    // --- Setup: Create a key and a message ---
    // In a real application, you would load or securely store the private key.
    // Here, we generate a new one for demonstration purposes.
    let private_key = SigningKey::random(&mut OsRng);
    let public_key: VerifyingKey = private_key.verifying_key(); // The corresponding public key

    // The message to be signed.
    let message = b"This is a test message for ECDSA signing.";

    println!("Original Message: {}", String::from_utf8_lossy(message));
    println!("-------------------------------------------------");

    // --- Signing ---
    // Call our translated `sign` function.
    let signature_bytes = sign(message, &private_key);

    println!("Generated Signature (DER format):");
    // Print signature as a hex string for readability
    println!("{}", hex::encode(&signature_bytes));
    println!("Signature Length: {} bytes", signature_bytes.len());
    println!("-------------------------------------------------");


    // --- Verification (for demonstration) ---
    // To show the signature is valid, we can verify it with the public key.
    // This part is not in the original Python snippet but is crucial for context.
    use signature::Verifier;
    use ecdsa::signature::der::FromDer;

    // The verifier needs the signature in its original `Signature` struct format,
    // so we parse it from the DER bytes we created.
    let signature_for_verify = Signature::from_der(&signature_bytes).expect("Failed to parse DER signature");

    // The `verify` method returns `Ok(())` on success and `Err` on failure.
    let verification_result = public_key.verify(message, &signature_for_verify);

    match verification_result {
        Ok(_) => println!("✅ Signature is valid!"),
        Err(e) => println!("❌ Signature is invalid: {}", e),
    }
}

// To run this example, you'll also need the `hex` crate for pretty-printing.
// Add `hex = "0.4"` to your Cargo.toml.