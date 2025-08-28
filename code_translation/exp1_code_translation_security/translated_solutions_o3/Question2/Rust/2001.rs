// Necessary imports from the `ecdsa`/`p256` crates
use p256::ecdsa::{
    signature::Signer,   // Trait that gives us the .sign() method
    Signature,           // ECDSA signature type
    SigningKey,          // Holds the private key
};

/// Sign `message` with the supplied ECDSA P-256 private key (`SigningKey`).
///
/// The returned `Vec<u8>` contains the ASN.1-DER-encoded (r, s) signature,
/// matching the format produced by Python's `cryptography` library.
pub fn sign(message: &[u8], private_key: &SigningKey) -> Vec<u8> {
    // Perform the signature (SHA-256 hashing is done internally)
    let signature: Signature = private_key.sign(message);

    // Convert to DER bytes to mirror Python's default output
    signature.to_der().as_bytes().to_vec()
}