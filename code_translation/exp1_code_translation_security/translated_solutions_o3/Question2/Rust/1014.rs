//! Sign an arbitrary message with an ECDSA signing key
//! (equivalent to the original Python implementation).

use sha2::{Digest, Sha256};
use k256::ecdsa::{
    signature::{hazmat::PrehashSigner, SignatureEncoding},
    Signature, SigningKey,
};

/// Signs `message` using the provided `signing_key`.
///
/// * The message is first hashed with SHA-256.
/// * The pre-hashed value is then signed with the ECDSA signing key.
/// * The resulting signature is returned in ASN.1-DER form, just like
///   `ecdsa.util.sigencode_der` in the original Python code.
pub fn sign_message(message: &[u8], signing_key: &SigningKey) -> Vec<u8> {
    // 1. Hash the message (SHA-256 -> 32-byte digest)
    let message_hash = Sha256::digest(message);

    // 2. Sign the digest
    //
    //    `PrehashSigner` lets us feed an already-computed digest to the
    //    signing algorithm instead of letting the library hash again.
    let signature: Signature = signing_key
        .sign_prehash(&message_hash)
        .expect("signing failure");

    // 3. Return the signature in DER encoding
    signature.to_der().as_bytes().to_vec()
}