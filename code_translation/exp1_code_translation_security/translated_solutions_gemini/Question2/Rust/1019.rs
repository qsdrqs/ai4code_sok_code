use p384::NistP384; // The NIST P-384 curve, equivalent to SECP384R1
use sha2::{Sha256, Digest}; // The SHA-256 hashing algorithm
use ecdsa::{
    SigningKey, 
    signature::{Signer, hazmat::PrehashSigner}
};
use rand_core::OsRng; // A cryptographically secure random number generator

/// Signs a byte slice using a P-384 private key and returns the
/// signature in ASN.1 DER format.
///
/// # Arguments
///
/// * `private_key`: The ECDSA private key to sign with.
/// * `data`: The data to be signed.
///
/// # Returns
///
/// A `Vec<u8>` containing the DER-encoded signature.
fn sign_string(private_key: &SigningKey<NistP384>, data: &[u8]) -> Vec<u8> {
    // 1. Hash the data with SHA-256.
    // The `signature` crate's Signer trait expects a pre-computed digest.
    let digest = Sha256::digest(data);

    // 2. Sign the digest.
    // The `sign` method is from the `PrehashSigner` trait.
    let signature: ecdsa::Signature<NistP384> = private_key.sign(&digest);

    // 3. Return the signature encoded as ASN.1 DER bytes.
    // This is the standard format and matches the output of the Python cryptography library.
    signature.to_der().to_vec()
}

fn main() {
    // Generate a new private key for the P-384 curve.
    // This is equivalent to `ec.generate_private_key(ec.SECP384R1())`.
    let private_key = SigningKey::random(&mut OsRng);

    // The data to be signed.
    // Equivalent to `b"this is some data I'd like to sign"`.
    let data = b"this is some data I'd like to sign";

    // Sign the data using our function.
    let signature_bytes = sign_string(&private_key, data);

    println!("Data to sign: \"{}\"", String::from_utf8_lossy(data));
    println!("Signature (hex): {}", hex::encode(&signature_bytes));
    println!("Signature length: {} bytes", signature_bytes.len());

    // You can also verify the signature to confirm it works.
    use ecdsa::VerifyingKey;
    use ecdsa::signature::Verifier;

    let verifying_key = VerifyingKey::from(&private_key); // Get the public key
    let digest_to_verify = Sha256::digest(data);
    let signature_to_verify = ecdsa::Signature::from_der(&signature_bytes).expect("Failed to parse DER signature");

    assert!(verifying_key.verify(&digest_to_verify, &signature_to_verify).is_ok());
    println!("\nVerification successful!");
}