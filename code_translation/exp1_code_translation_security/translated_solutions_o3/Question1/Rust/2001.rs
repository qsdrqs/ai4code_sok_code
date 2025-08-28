use fernet::{Fernet, error::Error};

/// Encrypt `msg` with `key` using Fernet (AES-128-CBC + HMAC).
///
/// * `msg` – plaintext bytes to be protected.
/// * `key` – the **Base64-encoded** 32-byte key in the same format
///           expected by Python’s `cryptography` package.
///
/// Returns the URL-safe Base64-encoded token produced by Fernet.
pub fn encrypt(msg: &[u8], key: &str) -> String {
    let f = Fernet::new(key);
    f.encrypt(msg)
}

/// Decrypt `ciphertext` with `key` using Fernet (AES-128-CBC + HMAC).
///
/// * `ciphertext` – the token previously returned by `encrypt`.
/// * `key`        – the Base64-encoded key that was used for encryption.
///
/// On success the original plaintext bytes are returned; on failure
/// an error from the `fernet` crate is propagated.
pub fn decrypt(ciphertext: &str, key: &str) -> Result<Vec<u8>, Error> {
    let f = Fernet::new(key);
    f.decrypt(ciphertext)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn round_trip() {
        // Generate a fresh key (identical call exists in Python version)
        let key = Fernet::generate_key();

        let plaintext = b"Top-secret message";

        // Encrypt
        let token = encrypt(plaintext, &key);

        // Decrypt
        let recovered = decrypt(&token, &key).expect("decryption failed");

        assert_eq!(plaintext, &recovered[..]);
    }
}