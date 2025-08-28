use ring::{signature, digest};

/// Signs a message using an ECDSA private key with SHA-256.
///
/// # Arguments
///
/// * `message` - A byte slice containing the message to be signed.
/// * `private_key` - A reference to an `EcdsaKeyPair` representing the ECDSA private key.
///
/// # Returns
///
/// A `Result` containing:
/// - `Ok(Vec<u8>)` - The DER-encoded ECDSA signature.
/// - `Err(ring::error::Unspecified)` - If the signing operation fails.
pub fn sign(message: &[u8], private_key: &signature::EcdsaKeyPair) -> Result<Vec<u8>, ring::error::Unspecified> {
    // Step 1: Hash the message using SHA-256
    let digest = digest::digest(&digest::SHA256, message);

    // Step 2: Sign the prehashed message
    let signature = private_key.sign(digest.as_ref())?;

    // Step 3: Return the signature as a Vec<u8>
    Ok(signature.as_ref().to_vec())
}