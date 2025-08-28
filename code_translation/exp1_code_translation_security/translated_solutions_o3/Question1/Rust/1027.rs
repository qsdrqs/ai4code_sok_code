// ---------------------------------------------------------------------------
// AES-CBC with PKCS#7 padding – helpers equivalent to the original Python
// ---------------------------------------------------------------------------

use aes::Aes128;                                  // or Aes256 if you use a 32-byte key
use block_modes::{BlockMode, Cbc};
use block_modes::block_padding::Pkcs7;

type Aes128Cbc = Cbc<Aes128, Pkcs7>;

/// Encrypt `message` (UTF-8 string) with `key` (16-byte slice).
/// Returns the resulting ciphertext as a `Vec<u8>`.
pub fn encrypt(message: &str, key: &[u8]) -> Vec<u8> {
    // 16 zero bytes, to mimic the Python code that did not pass an IV
    // (PyCryptodome uses an all-zero IV when none is supplied for CBC).
    let iv = [0u8; 16];

    // Create cipher instance
    let cipher = Aes128Cbc::new_from_slices(key, &iv)
        .expect("invalid key or IV length");

    // `encrypt_vec` handles PKCS#7 padding for us
    cipher.encrypt_vec(message.as_bytes())
}

/// Decrypt `ciphertext` with `key` and return the plaintext string.
///
/// Panics if decryption fails or the result is not valid UTF-8
/// (mirrors the “nothing can go wrong” attitude of the original snippet).
pub fn decrypt(ciphertext: &[u8], key: &[u8]) -> String {
    let iv = [0u8; 16];

    // Cipher instance (same key / IV as for encryption)
    let cipher = Aes128Cbc::new_from_slices(key, &iv)
        .expect("invalid key or IV length");

    // `decrypt_vec` automatically removes PKCS#7 padding
    let decrypted_bytes = cipher
        .decrypt_vec(ciphertext)
        .expect("decryption error");

    String::from_utf8(decrypted_bytes)
        .expect("decrypted bytes are not valid UTF-8")
}