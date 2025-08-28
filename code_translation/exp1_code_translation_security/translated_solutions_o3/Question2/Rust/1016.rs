//! A function in Rust that signs a given message with a raw 32-byte
//! secp256k1 (ECDSA) private key.
//
// Dependencies (add to Cargo.toml):
//
// [dependencies]
// k256 = { version = "0.13", features = ["ecdsa"] }   // or whatever version is supplied
//
// The `k256` crate is a pure-Rust implementation of the secp256k1
// curve; it provides convenient, safe wrappers for ECDSA signing.

use k256::ecdsa::{
    signature::{Signer, SignatureEncoding},
    Signature, SigningKey,
};

/// Sign `message` with the supplied secp256k1 private key.
///
/// * `message`     – Arbitrary data to be signed (any byte slice).
/// * `private_key` – Raw 32-byte secp256k1 secret key.
///
/// The returned value is a DER-encoded ECDSA signature, which is the
/// same format produced by the original Python implementation
/// (`ecdsa.SigningKey.sign`).
pub fn sign(message: &[u8], private_key: &[u8]) -> Result<Vec<u8>, k256::ecdsa::Error> {
    // Construct a signing key from the raw 32-byte secret key.
    let signing_key = SigningKey::from_bytes(private_key.into())?;

    // Create the ECDSA signature.
    let signature: Signature = signing_key.sign(message);

    // Convert it to DER and hand back the raw bytes.
    Ok(signature.to_der().as_bytes().to_vec())
}

/* --------------------------------------------------------------------
   Example usage (for completeness only – remove if not needed):

fn main() -> Result<(), Box<dyn std::error::Error>> {
    // 32-byte test key (DON'T use in production!).
    let priv_key = [0x11u8; 32];
    let msg = b"hello world";

    let sig = sign(msg, &priv_key)?;
    println!("Signature (hex): {}", hex::encode(sig));
    Ok(())
}
--------------------------------------------------------------------- */