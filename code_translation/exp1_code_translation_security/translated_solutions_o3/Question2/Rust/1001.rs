// std / external crates ------------------------------------------------------
use openssl::{
    error::ErrorStack,
    hash::MessageDigest,
    pkey::{PKey, Private},
    sign::Signer,
};

// ---------------------------------------------------------------------------
// Equivalent to the Python function:
//
//     def signusingECDSA(key, message):
//         signer = key.signer(ec.ECDSA(hashes.SHA256()))
//         signer.update(message)
//         return signer.finalize()
// ---------------------------------------------------------------------------

/// Sign `message` with the given ECDSA private `key` (SHA-256 digest).
///
/// Returns the raw DER-encoded ECDSA signature on success.
pub fn sign_using_ecdsa(
    key: &PKey<Private>,
    message: &[u8],
) -> Result<Vec<u8>, ErrorStack> {
    // Create a SHA-256 / ECDSA signer.
    let mut signer = Signer::new(MessageDigest::sha256(), key)?;
    // Feed the data to be signed.
    signer.update(message)?;
    // Produce and return the signature.
    signer.sign_to_vec()
}