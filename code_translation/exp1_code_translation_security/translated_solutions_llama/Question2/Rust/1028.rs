use ecdsa::{SigningKey, VerifyingKey, signature::Signer, signature::Verifier};

/// Signs a given message using an ECDSA signing key.
///
/// # Arguments
///
/// * `signing_key`: ECDSA signing key
/// * `message`: Message to be signed
///
/// # Returns
///
/// The signature of the message.
pub fn sign(signing_key: &SigningKey, message: &[u8]) -> Vec<u8> {
    let signature = signing_key.sign(message);
    signature.to_bytes().to_vec()
}

/// Verifies a given signature using an ECDSA verification key.
///
/// # Arguments
///
/// * `verifying_key`: ECDSA verification key
/// * `message`: Message to be verified
/// * `signature`: Signature to be verified
///
/// # Returns
///
/// `true` if the signature is valid, `false` otherwise.
pub fn verify(verifying_key: &VerifyingKey, message: &[u8], signature: &[u8]) -> bool {
    let signature = ecdsa::Signature::from_bytes(signature).unwrap();
    verifying_key.verify(message, &signature).is_ok()
}

fn main() {
    // Example usage:
    let signing_key = SigningKey::random(&mut rand::thread_rng());
    let verifying_key = VerifyingKey::from(&signing_key);

    let message = b"Hello, World!";
    let signature = sign(&signing_key, message);

    println!("Signature: {:?}", signature);
    println!("Is signature valid? {}", verify(&verifying_key, message, &signature));
}