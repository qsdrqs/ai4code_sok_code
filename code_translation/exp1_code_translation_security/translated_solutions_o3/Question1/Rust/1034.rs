use aes_gcm::{
    aead::{Aead, KeyInit, OsRng},
    Aes256Gcm,           // AES-256 in GCM mode
    Key, Nonce,          // re-exports from `aead`
};
use base64::{engine::general_purpose, Engine as _};
use sha2::{Digest, Sha256};

/// Derive a fixed-size 32-byte key from an arbitrary string (the password)
fn derive_key(password: &str) -> Key<Aes256Gcm> {
    let digest = Sha256::digest(password.as_bytes());
    // `digest` is a 32-byte array; turn it into the Key type required by aes-gcm
    *Key::<Aes256Gcm>::from_slice(&digest)
}

/// Encrypt a plain string with `password`, returning a Base-64 string
pub fn encode_str(plain_text: &str, password: &str) -> String {
    // 1) derive a usable AES-256 key
    let key = derive_key(password);
    let cipher = Aes256Gcm::new(&key);

    // 2) make a fresh 96-bit (12-byte) nonce
    let nonce = Aes256Gcm::generate_nonce(&mut OsRng); // random, unique

    // 3) encrypt
    let cipher_text = cipher
        .encrypt(&nonce, plain_text.as_bytes())
        .expect("encryption failure!"); // should not fail

    // 4) prepend nonce so we can recover it for decryption, then Base-64
    let mut out = Vec::with_capacity(nonce.len() + cipher_text.len());
    out.extend_from_slice(&nonce);       // [nonce | cipher_text]
    out.extend_from_slice(&cipher_text);
    general_purpose::STANDARD.encode(out)
}

/// Decrypt a Base-64 string that was produced by `encode_str`
pub fn decode_str(encoded_text: &str, password: &str) -> Result<String, String> {
    // 1) Base-64 decode
    let data = general_purpose::STANDARD
        .decode(encoded_text)
        .map_err(|e| format!("Base64 error: {e}"))?;

    if data.len() < 12 {
        return Err("Cipher text too short".into());
    }

    // 2) split nonce and cipher text
    let (nonce_bytes, cipher_text) = data.split_at(12);
    let nonce = Nonce::from_slice(nonce_bytes);

    // 3) derive the same key and decrypt
    let key = derive_key(password);
    let cipher = Aes256Gcm::new(&key);
    let plain = cipher
        .decrypt(nonce, cipher_text)
        .map_err(|_| "Decryption failed (wrong key or corrupted data)".to_string())?;

    // 4) turn UTF-8 bytes back into String
    String::from_utf8(plain).map_err(|e| format!("UTF-8 error: {e}"))
}

fn main() {
    // roughly the same mini-demo as in the Python snippet
    let encoded = encode_str("I am okay", "wow");
    match decode_str(&encoded, "wow") {
        Ok(s)  => println!("{s}"),   // prints: I am okay
        Err(e) => eprintln!("Error: {e}"),
    }
}