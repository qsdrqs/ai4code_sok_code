use p384::ecdsa::{SigningKey, Signature};
use sha2::{Digest, Sha256};

/// Signs the given data using ECDSA with the SECP384R1 curve and SHA-256.
///
/// # Arguments
/// - `private_key`: A reference to the ECDSA signing key.
/// - `data`: The data to be signed.
///
/// # Returns
/// A `Vec<u8>` containing the DER-encoded ECDSA signature.
pub fn sign_string(private_key: &SigningKey, data: &[u8]) -> Vec<u8> {
    // Step 1: Hash the input data using SHA-256
    let digest = Sha256::digest(data);

    // Step 2: Pad the 32-byte SHA-256 digest to 48 bytes (384 bits)
    let mut padded_digest = [0u8; 48];
    padded_digest[16..].copy_from_slice(&digest);

    // Step 3: Sign the padded digest
    let signature = private_key.sign(&padded_digest);

    // Step 4: Return the DER-encoded signature
    signature.to_der()
}