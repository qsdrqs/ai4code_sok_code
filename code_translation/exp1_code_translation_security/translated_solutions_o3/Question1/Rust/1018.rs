// src/lib.rs
//
// This snippet shows a 1-to-1 translation of the two Python helpers
// that rely on `cryptography.fernet` into Rust.  
// The crate `fernet` (available on crates.io) offers the same API
// semantics, so the implementation is almost identical in spirit.
//
// Cargo.toml will just need:
//
// [dependencies]
// fernet = "0.3"      # or whatever version you are shipping
//

use fernet::Fernet;

/// Encrypt `input` with the supplied Fernet `key`.
///
/// `key` must be the same URL-safe base64-encoded 32-byte key that the
/// original Python implementation expects.
pub fn encrypt(input: &str, key: &str) -> String {
    let fernet = Fernet::new(key);
    fernet.encrypt(input.as_bytes())
}

/// Decrypt `token` with the supplied Fernet `key`.
///
/// If the token is invalid or the plaintext is not valid UTF-8 an error
/// is returned.
pub fn decrypt(token: &str, key: &str) -> Result<String, &'static str> {
    let fernet = Fernet::new(key);

    // `decrypt` can fail (bad key, modified token, etc.)
    let bytes = fernet
        .decrypt(token)
        .map_err(|_| "unable to decrypt token")?;

    // Convert the resulting byte vector into UTF-8 text.
    String::from_utf8(bytes).map_err(|_| "decrypted data is not valid UTF-8")
}

#[cfg(test)]
mod tests {
    use super::*;
    use fernet::Fernet;

    #[test]
    fn round_trip() {
        // Generate a random key for the test
        let key = Fernet::generate_key();
        let message = "hello rust!";

        let token = encrypt(message, &key);
        let plain = decrypt(&token, &key).unwrap();

        assert_eq!(plain, message);
    }
}