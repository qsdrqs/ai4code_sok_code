// Import necessary dependencies
use k256::ecdsa::{signature::Signer, Signature, SigningKey};
use sha2::{Digest, Sha256};

/// Signs a given message using a given ECDSA signing key
///
/// # Arguments
/// * `message` - The message to be signed as a byte slice
/// * `signing_key` - The ECDSA signing key
///
/// # Returns
/// * A `Vec<u8>` containing the DER-encoded signature
pub fn sign_message(message: &[u8], signing_key: &SigningKey) -> Vec<u8> {
    // Hash the message using SHA-256
    let mut hasher = Sha256::new();
    hasher.update(message);
    let message_hash = hasher.finalize();

    // Sign the message hash
    let signature: Signature = signing_key.sign(&message_hash);

    // Return the DER-encoded signature as a Vec<u8>
    signature.to_der().as_bytes().to_vec()
}

#[cfg(test)]
mod tests {
    use super::*;
    use k256::ecdsa::SigningKey;
    use rand_core::OsRng;

    #[test]
    fn test_sign_message() {
        // Generate a random signing key
        let signing_key = SigningKey::random(&mut OsRng);

        // Message to be signed
        let message = b"Hello, world!";

        // Sign the message
        let signature = sign_message(message, &signing_key);

        // Ensure the signature is not empty
        assert!(!signature.is_empty());
    }
}