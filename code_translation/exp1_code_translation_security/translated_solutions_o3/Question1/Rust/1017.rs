// Cargo.toml (dependencies will be supplied for you)
// [dependencies]
// aes   = "0.8"          # AES block-cipher implementation
// eax   = "0.7"          # EAX AEAD construction
// rand  = "0.8"          # Random-number generation for nonces

use aes::Aes256;                // or Aes128 / Aes192 – must match key length
use eax::{
    aead::{AeadInPlace, KeyInit, generic_array::GenericArray},
    Eax,
};
use rand::RngCore;

// Convenience alias for “AES-256 in EAX mode”.
type Aes256Eax = Eax<Aes256>;

/// Encrypt `plaintext` with `key`.
///
/// Returns a tuple `(nonce, tag, ciphertext)` that mirrors the Python version.
pub fn encrypt(
    key: &[u8],
    plaintext: &[u8],
) -> Result<(Vec<u8>, Vec<u8>, Vec<u8>), Box<dyn std::error::Error>> {
    // Create a cipher instance from the provided key.
    let cipher = Aes256Eax::new_from_slice(key)?;

    // Generate a fresh 16-byte nonce (EAX allows 0-..=16 byte nonces; 16 is common).
    let mut nonce = [0u8; 16];
    rand::thread_rng().fill_bytes(&mut nonce);
    let nonce_ga = GenericArray::from_slice(&nonce);

    // Copy the plaintext into a mutable buffer because `encrypt_in_place_detached`
    // works in-place.
    let mut buffer = plaintext.to_vec();

    // Encrypt and obtain the authentication tag separately.
    let tag = cipher.encrypt_in_place_detached(nonce_ga, b"", &mut buffer)?;

    Ok((nonce.to_vec(), tag.to_vec(), buffer))
}

/// Decrypt a `(nonce, tag, ciphertext)` triplet with `key`.
///
/// On success the original plaintext is returned; authentication failure yields an
/// error.
pub fn decrypt(
    key: &[u8],
    data: (Vec<u8>, Vec<u8>, Vec<u8>),
) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    let (nonce, tag, mut ciphertext) = data;

    let cipher = Aes256Eax::new_from_slice(key)?;

    let nonce_ga = GenericArray::from_slice(&nonce);
    let tag_ga   = GenericArray::from_slice(&tag);

    // `decrypt_in_place_detached` verifies the tag and, if valid,
    // decrypts the buffer in place.
    cipher.decrypt_in_place_detached(nonce_ga, b"", &mut ciphertext, tag_ga)?;

    Ok(ciphertext)
}