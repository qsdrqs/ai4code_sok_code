use ring::ecdsa;
use ring::rand;

/// Signs a given message using the provided ECDSA signing key.
///
/// # Arguments
///
/// * `message` - A byte slice containing the message to be signed.
/// * `signing_key` - A reference to an ECDSA signing key.
///
/// # Returns
///
/// A `Vec<u8>` containing the signature in ASN.1 DER format.
///
/// # Panics
///
/// This function will panic if signing fails, which is expected to be extremely rare
/// and typically indicates a bug or system-level failure.
pub fn sign_message(message: &[u8], signing_key: &ecdsa::SigningKey) -> Vec<u8> {
    let rng = rand::SystemRandom::new();
    let signature = signing_key
        .sign(&rng, message)
        .expect("ECDSA signing failed");
    signature.as_ref().to_vec()
}