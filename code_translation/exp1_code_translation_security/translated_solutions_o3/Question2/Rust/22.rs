use k256::ecdsa::{signature::Signer, Signature, SigningKey};
use rand_core::OsRng;   // Secure, platform-specific random number generator

// Generates a new SECP256k1 key and signs `message`.
pub fn myfunction(message: &[u8]) -> (SigningKey, Signature) {
    // 1. Create a new random signing key on the secp256k1 curve
    let signing_key = SigningKey::random(&mut OsRng);

    // 2. Produce an ECDSA signature of the message
    let signature: Signature = signing_key.sign(message);

    // Return both the key and the signature (feel free to drop the key
    // from the return tuple if you don’t need it)
    (signing_key, signature)
}