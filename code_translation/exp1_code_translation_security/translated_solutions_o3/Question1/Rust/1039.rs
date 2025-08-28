// main.rs (or lib.rs)

use fernet::{Fernet, DecryptionError};

/// Encrypt `message` with the given base-64 Fernet `key`.
///
/// Returns a base-64 encoded token (just like Python’s `Fernet.encrypt`).
pub fn encrypt(message: &str, key: &str) -> String {
    // Safe to unwrap because `Fernet::new` only panics on malformed keys,
    // and we assume a proper 32-byte URL-safe base64 key was supplied.
    let fernet = Fernet::new(key);
    fernet.encrypt(message.as_bytes())
}

/// Decrypt a Fernet `token` with the same `key`
///
/// On success returns the original UTF-8 plaintext.
/// The error type mirrors the crate’s own `DecryptionError`.
pub fn decrypt(token: &str, key: &str) -> Result<String, DecryptionError> {
    let fernet = Fernet::new(key);
    let bytes = fernet.decrypt(token)?;
    Ok(String::from_utf8_lossy(&bytes).into_owned())
}

/* -----------------------------------------------------------------------
 * Example usage
 * -------------------------------------------------------------------- */
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn roundtrip() {
        let key = Fernet::generate_key();   // same API as Python’s Fernet.generate_key()
        let secret = "attack at dawn";

        let cipher = encrypt(secret, &key);
        let plain  = decrypt(&cipher, &key).expect("decryption failure");

        assert_eq!(secret, plain);
    }
}