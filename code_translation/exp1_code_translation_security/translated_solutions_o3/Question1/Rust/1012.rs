// Cargo.toml (for reference only)
// [dependencies]
// fernet    = "0.2"      # Fernet implementation for Rust
// once_cell = "1"        # For a lazily-initialized static key

use once_cell::sync::Lazy;
use fernet::{Fernet, DecryptError};

// Lazily generate a single global key, exactly like the Python snippet did.
static KEY: Lazy<String> = Lazy::new(|| Fernet::generate_key());

/// Encrypt a UTF-8 text string and return the resulting Fernet token
/// (base-64-encoded, just like the Python `cryptography` library).
pub fn encrypt_text(plain_text: &str) -> String {
    // `encrypt` returns a `String`, so no additional conversion is needed.
    Fernet::encrypt(&KEY, plain_text)
}

/// Decrypt a Fernet token back into a UTF-8 `String`.
pub fn decrypt_text(encrypted_text: &str) -> Result<String, DecryptError> {
    Fernet::decrypt(&KEY, encrypted_text)
}

// --- Example usage ---------------------------------------------------------
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn round_trip() {
        let msg  = "Hello, Rust!";
        let enc  = encrypt_text(msg);
        let dec  = decrypt_text(&enc).expect("decryption failed");
        assert_eq!(msg, dec);
    }
}