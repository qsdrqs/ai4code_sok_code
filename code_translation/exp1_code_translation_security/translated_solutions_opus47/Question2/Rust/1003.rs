use p256::ecdsa::{signature::{Signer, Verifier}, Signature, SigningKey, VerifyingKey};

pub fn sign_nist256(message: &[u8], signing_key_raw: &[u8]) -> Vec<u8> {
    // SigningKey::from_slice expects the 32-byte scalar (big-endian), matching
    // the format produced by Python's ecdsa.SigningKey.from_string for NIST256p.
    // The Signer trait uses SHA-256 by default for the P-256 curve.
    let key = SigningKey::from_slice(signing_key_raw).expect("Invalid signing key");
    let signature: Signature = key.sign(message);
    signature.to_bytes().to_vec()
}

pub fn verify_nist256(message: &[u8], signature: &[u8], verifying_key_raw: &[u8]) -> bool {
    // Python's ecdsa library uses raw x||y concatenation (64 bytes for P-256),
    // while p256 expects SEC1 format with a leading 0x04 byte for uncompressed points.
    let mut sec1_bytes = Vec::with_capacity(1 + verifying_key_raw.len());
    sec1_bytes.push(0x04);
    sec1_bytes.extend_from_slice(verifying_key_raw);

    let key = match VerifyingKey::from_sec1_bytes(&sec1_bytes) {
        Ok(k) => k,
        Err(_) => return false,
    };
    let sig = match Signature::from_slice(signature) {
        Ok(s) => s,
        Err(_) => return false,
    };
    key.verify(message, &sig).is_ok()
}