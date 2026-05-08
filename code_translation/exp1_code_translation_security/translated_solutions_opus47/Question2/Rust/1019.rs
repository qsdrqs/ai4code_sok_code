// Cargo.toml dependencies:
// [dependencies]
// p384 = "0.13"
// sha2 = "0.10"

use p384::ecdsa::{signature::hazmat::PrehashSigner, Signature, SigningKey};
use sha2::{Digest, Sha256};

pub fn sign_string(private_key: &SigningKey, data: &[u8]) -> Vec<u8> {
    // Hash the data using SHA-256 (matches Python's `hashes.SHA256()`)
    let hash = Sha256::digest(data);

    // Sign the prehash using ECDSA on the P-384 curve
    let signature: Signature = private_key
        .sign_prehash(&hash)
        .expect("signing failed");

    // Return the DER-encoded signature (matching Python's default output format)
    signature.to_der().as_bytes().to_vec()
}

// Example usage (similar to the commented-out Python code):
// fn main() {
//     use rand_core::OsRng;
//     let signing_key = SigningKey::random(&mut OsRng);
//     let sig = sign_string(&signing_key, b"hello world");
//
//     // Equivalent of Python's `utils.decode_dss_signature(...)`:
//     let decoded = Signature::from_der(&sig).unwrap();
//     println!("r = {:?}, s = {:?}", decoded.r(), decoded.s());
// }