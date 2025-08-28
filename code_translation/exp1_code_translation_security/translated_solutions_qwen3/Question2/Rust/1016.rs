use k256::ecdsa::{SigningKey, Signature};
use sha1::Digest;
use std::error::Error;

/// Signs a message using ECDSA with the SECP256k1 curve and SHA-1 as the hash function.
///
/// # Arguments
///
/// * `message` - A slice of bytes representing the message to be signed.
/// * `private_key` - A 32-byte array representing the ECDSA private key.
///
/// # Returns
///
/// A `Result` containing:
/// - `Ok(Vec<u8>)` - A 64-byte vector representing the ECDSA signature in big-endian format.
/// - `Err(Box<dyn Error>)` - An error if the private key is invalid or the signing fails.
pub fn sign(message: &[u8], private_key: [u8; 32]) -> Result<Vec<u8>, Box<dyn Error>> {
    // Step 1: Hash the message using SHA-1
    let hash_sha1 = {
        let mut hasher = sha1::Sha1::new();
        hasher.update(message);
        hasher.finalize()
    };

    // Step 2: Pad the 20-byte SHA-1 hash to 32 bytes with leading zeros
    let mut digest = [0u8; 32];
    digest[12..].copy_from_slice(&hash_sha1); // Copy SHA-1 hash into the last 20 bytes

    // Step 3: Create the ECDSA signing key from the private key bytes
    let signing_key = SigningKey::from_bytes(&private_key)?;

    // Step 4: Sign the padded digest using the SECP256k1 curve
    let signature = signing_key.sign_prehash(&digest);

    // Step 5: Return the signature as a 64-byte vector
    Ok(signature.to_bytes().to_vec())
}