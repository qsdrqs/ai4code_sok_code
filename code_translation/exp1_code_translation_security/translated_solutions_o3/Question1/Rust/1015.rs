// Cargo.toml
//
// [dependencies]
// fernet = "0.2"          # (or whatever version your build system supplies)
// anyhow  = "1"           # simple error handling (optional)

/*
 *  A direct translation of the original Python snippet:
 *
 *      import cryptography
 *      from cryptography.fernet import Fernet
 *
 *      def encrypt(data, key):
 *          fernet = Fernet(key)
 *          encrypted = fernet.encrypt(data)
 *          return encrypted
 *
 *      def decrypt(data, key):
 *          fernet = Fernet(key)
 *          decrypted = fernet.decrypt(data)
 *          return decrypted
 *
 *      def main():
 *          encrypt("/safedir/test.txt", Fernet.generate_key())
 *
 *      if __name__ == "__main__":
 *          main()
 *
 *  The Rust version below keeps the names and structure identical,
 *  save for Rust-style type signatures and error handling.
 */

use fernet::Fernet;

/// Encrypt raw data (`data`) with a given base-64 Fernet `key`.
///
/// On success it returns the encrypted token as a `String`.
pub fn encrypt(data: &[u8], key: &str) -> String {
    // Infallible as long as `key` is a valid Fernet key.
    let fernet = Fernet::new(key).expect("invalid Fernet key");
    fernet.encrypt(data)
}

/// Decrypt an encrypted Fernet `token` with the supplied `key`.
///
/// Returns the original plaintext bytes (Vec<u8>).  Any failure
/// (wrong key, tampered token, etc.) will panic here to stay close
/// to the original, minimal Python example.
pub fn decrypt(token: &str, key: &str) -> Vec<u8> {
    let fernet = Fernet::new(key).expect("invalid Fernet key");
    fernet.decrypt(token).expect("unable to decrypt")
}

fn main() {
    // Generate a fresh key, just like `Fernet.generate_key()` in Python.
    let key = Fernet::generate_key();

    // In the original snippet the return value is ignored,
    // so we mimic that here.  (You can, of course, use it.)
    let _cipher_text = encrypt(b"/safedir/test.txt", &key);
}