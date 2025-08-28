use p256::ecdsa::{SigningKey, Signature, signature::Signer};
use ecdsa::signature::der::to_vec; // To get the signature as raw bytes (DER format)
use rand::rngs::OsRng; // For generating a new private key in the example

/// Signs a message using a P-256 ECDSA private key.
///
/// The key itself acts as the signer. The `sign` method automatically
/// handles hashing the message with SHA-256 before signing, which is the
/// default for the `p256` crate.
///
/// # Arguments
/// * `key` - A reference to the P-256 ECDSA signing key.
/// * `message` - The message to be signed as a byte slice.
///
/// # Returns
/// A `Vec<u8>` containing the DER-encoded signature, which is the standard
/// format and matches the output of the Python cryptography library.
fn sign_using_ecdsa(key: &SigningKey, message: &[u8]) -> Vec<u8> {
    // The `sign` method takes the message, hashes it with SHA-256,
    // and produces a signature object.
    let signature: Signature = key.sign(message);

    // The Python library returns the signature as raw bytes in DER format.
    // We convert the Rust signature object to the same format for compatibility.
    to_vec(&signature).expect("Failed to DER-encode signature")
}

fn main() {
    // 1. Generate a new random private key for demonstration purposes.
    //    In a real application, you would load this from a secure location.
    let private_key = SigningKey::random(&mut OsRng);
    println!("Generated a new P-256 private key.");

    // 2. Define the message to be signed.
    let message = b"This is a test message for ECDSA signing.";
    println!("Message to sign: \"{}\"", String::from_utf8_lossy(message));

    // 3. Call our function to sign the message.
    let signature_bytes = sign_using_ecdsa(&private_key, message);

    // 4. Print the resulting signature.
    //    We use the `hex` crate to display the binary data in a readable way.
    println!("\nSignature (DER-encoded bytes):");
    println!("{}", hex::encode(&signature_bytes));
    println!("Signature length: {} bytes", signature_bytes.len());

    // (Optional) For verification, you can use the corresponding verifying key.
    use p256::ecdsa::{VerifyingKey, signature::Verifier};

    let verifying_key = VerifyingKey::from(&private_key);
    let signature_to_verify = Signature::from_der(&signature_bytes)
        .expect("Failed to parse DER signature");

    assert!(verifying_key.verify(message, &signature_to_verify).is_ok());
    println!("\nVerification successful!");
}