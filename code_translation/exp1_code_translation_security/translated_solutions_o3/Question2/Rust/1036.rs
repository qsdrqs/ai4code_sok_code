// Cargo.toml
//
// [dependencies]
// secp256k1 = "0.27"
// sha2       = "0.10"
// hex        = "0.4"
// -----------------------------------------------------------------------------
// src/main.rs

use sha2::{Digest, Sha256};
use secp256k1::{Message, Secp256k1, SecretKey};

/// Hash‐then‐sign a UTF-8 message with a raw 32-byte Secp256k1 private key.
///
/// The result is returned as a lower-case hex string containing the DER-encoded
/// ECDSA signature (exactly what the original Python code produced).
fn sign_message(message: &str, private_key_hex: &str) -> Result<String, Box<dyn std::error::Error>> {
    /* -------------------------------------------------------------------- */
    /* 1.  SHA-256 the message                                              */
    /* -------------------------------------------------------------------- */
    let mut hasher = Sha256::new();
    hasher.update(message.as_bytes());
    let digest = hasher.finalize();                    // 32-byte array

    /* -------------------------------------------------------------------- */
    /* 2.  Build the secp256k1::Message object from the digest              */
    /* -------------------------------------------------------------------- */
    let msg = Message::from_slice(&digest)?;           // also 32 bytes

    /* -------------------------------------------------------------------- */
    /* 3.  Convert the hex private key to a SecretKey                       */
    /* -------------------------------------------------------------------- */
    let key_bytes = hex::decode(private_key_hex)?;
    let secret_key = SecretKey::from_slice(&key_bytes)?;  // must be 32 bytes

    /* -------------------------------------------------------------------- */
    /* 4.  Deterministically sign using RFC-6979 (the crate’s default)      */
    /* -------------------------------------------------------------------- */
    let secp = Secp256k1::new();
    let sig = secp.sign_ecdsa(&msg, &secret_key);      // returns `ecdsa::Signature`

    /* -------------------------------------------------------------------- */
    /* 5.  DER-encode + hex-encode the signature (matches Python output)    */
    /* -------------------------------------------------------------------- */
    Ok(hex::encode(sig.serialize_der()))
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    // Same test vector used in the Python snippet
    let private_key =
        "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
    let message = "Hello";

    let signature = sign_message(message, private_key)?;
    println!("{}", signature);

    Ok(())
}