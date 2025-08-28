// --- extern crate imports ---------------------------------------------------
// rsa       = "0.9"          (for the PKCS#1 v1.5 signing primitives)
// sha2      = "0.10"         (SHA-256 hash function)
// rand      = "0.8"          (secure RNG required by the RSA crate)
// signature = "2"            (trait that offers the `Signer` behaviour)

use rand::rngs::OsRng;
use rsa::{
    pkcs1v15::SigningKey,   // PKCS#1 v1.5 padding + hashing adaptor
    RsaPrivateKey,
};
use sha2::Sha256;
use signature::Signer;       // brings the `sign*` methods into scope

/// Sign `msg` with an RSA private key using PKCS#1 v1.5 + SHA-256.
///
/// Equivalent to the Python snippet shown in the prompt.
///
/// # Arguments
/// * `msg`      – The message to be signed (raw bytes).
/// * `priv_key` – A reference to an `RsaPrivateKey`.
///
/// # Returns
/// A `Vec<u8>` containing the DER-encoded signature bytes.
pub fn sign(msg: &[u8], priv_key: &RsaPrivateKey) -> Vec<u8> {
    // Create a PKCS#1 v1.5 signer that internally hashes with SHA-256.
    let signing_key = SigningKey::<Sha256>::new(priv_key.clone());

    // A CSPRNG is required by the RSA implementation for blinding.
    let mut rng = OsRng;

    // Sign the message and return the raw signature bytes.
    let sig = signing_key.sign_with_rng(&mut rng, msg);
    sig.as_ref().to_vec()
}

/* --------------------------------------------------------------------------
   Example usage
   -------------------------------------------------------------------------- */
#[cfg(test)]
mod tests {
    use super::*;
    use rsa::pkcs1v15::VerifyingKey;
    use signature::Verifier;

    #[test]
    fn round_trip() {
        // Generate a 2048-bit test key.
        let mut rng = OsRng;
        let priv_key = RsaPrivateKey::new(&mut rng, 2048).expect("key gen failed");

        // The message we want to sign.
        let message = b"hello-rust";

        // Sign it.
        let signature = sign(message, &priv_key);

        // Verify it (just to prove it works).
        let verifying_key = VerifyingKey::<Sha256>::new(priv_key.to_public_key());
        assert!(verifying_key.verify(message, &signature).is_ok());
    }
}