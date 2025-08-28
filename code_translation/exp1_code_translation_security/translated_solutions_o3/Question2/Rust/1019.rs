use p384::ecdsa::{
    signature::Signer,   // trait that provides the `.sign()` method
    Signature,           // concrete signature type (r || s)
    SigningKey,          // private key type
};
use rand_core::OsRng;    // secure random generator

/// Sign arbitrary data with an existing P-384 private key.
///
/// `private_key` – reference to a p384::ecdsa::SigningKey  
/// `data`        – byte slice that will be hashed with SHA-256 and signed  
/// Returns the ASN.1-DER encoded ECDSA signature (same format returned by
/// Python’s `cryptography` library).
fn sign_string(private_key: &SigningKey, data: &[u8]) -> Vec<u8> {
    let sig: Signature = private_key.sign(data);          // sign (ECDSA + SHA-256)
    sig.to_der().as_bytes().to_vec()                      // convert to DER like Python
}

fn main() {
    // --- example usage (mirrors the Python sample) --------------------------
    let signing_key = SigningKey::random(&mut OsRng);          // generate a new key
    let data        = b"this is some data I'd like to sign";

    let signature = sign_string(&signing_key, data);
    println!("DER-encoded signature (hex): {:02x?}", signature);

    // If you want r/s values similar to `utils.decode_dss_signature(...)`:
    let sig = Signature::from_der(&signature).expect("DER signature");
    println!("r = {}", sig.r());
    println!("s = {}", sig.s());
}