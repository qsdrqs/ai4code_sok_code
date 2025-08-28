// ---------------------------------------------------------------------------------------------------------------------
//  A pair of helper functions that (a) encrypt and (b) decrypt a UTF-8 string
//  with AES-256 in CBC mode.  The ciphertext is returned/accepted as Base-64.
// ---------------------------------------------------------------------------------------------------------------------
//
//  Python source for comparison:
//
//  class AESCipher(object):
//      def __init__(self, key):
//          self.bs  = 32
//          self.key = hashlib.sha256(key.encode()).digest()
//
//      def encrypt(self, raw):
//          raw    = self._pad(raw)
//          iv     = Random.new().read(AES.block_size)
//          cipher = AES.new(self.key, AES.MODE_CBC, iv)
//          return base64.b64encode(iv + cipher.encrypt(raw))
//
//      def decrypt(self, enc):
//          enc    = base64.b64decode(enc)
//          iv     = enc[:AES.block_size]
//          cipher = AES.new(self.key, AES.MODE_CBC, iv)
//          return self._unpad(cipher.decrypt(enc[AES.block_size:])).decode('utf-8')
//
//      def _pad(self, s):
//          return s + (self.bs - len(s) % self.bs) * chr(self.bs - len(s) % self.bs)
//
//      @staticmethod
//      def _unpad(s):
//          return s[:-ord(s[len(s)-1:])]
// ---------------------------------------------------------------------------------------------------------------------

// ─── External crates ─────────────────────────────────────────────────────────────
// (Cargo.toml)
//
// [dependencies]
// aes           = "0.8"         # AES block-cipher implementation
// block-modes   = "0.8"         # CBC, CFB, CTR … wrappers for block ciphers
// rand          = "0.8"         # Secure random-number generation (OsRng)
// sha2          = "0.10"        # SHA-2 family (SHA-256 here)
// base64        = "0.21"        # Base-64 encode/decode
// anyhow        = "1.0"         # (optional) simple error handling
// --------------------------------------------------------------------------------

use aes::Aes256;
use base64::{decode as b64_decode, encode as b64_encode};
use block_modes::{block_padding::Pkcs7, BlockMode, Cbc};
use rand::{rngs::OsRng, RngCore};
use sha2::{Digest, Sha256};

type Aes256Cbc = Cbc<Aes256, Pkcs7>;

pub struct AESCipher {
    key: [u8; 32], // 256-bit key (already SHA-256 hashed)
}

impl AESCipher {
    /// Construct a new cipher from an arbitrary UTF-8 password.
    pub fn new(password: &str) -> Self {
        // Same as `hashlib.sha256(password.encode()).digest()` in Python.
        let mut hasher = Sha256::new();
        hasher.update(password.as_bytes());
        let digest = hasher.finalize();

        let mut key = [0u8; 32];
        key.copy_from_slice(&digest);
        Self { key }
    }

    /// Encrypt a UTF-8 string and return Base-64 encoded ciphertext.
    pub fn encrypt(&self, plaintext: &str) -> anyhow::Result<String> {
        // ---------------------------------------------------------------------
        // 1.  Create a fresh, cryptographically-secure IV (16 bytes for AES).
        // 2.  Encrypt with AES-256-CBC + PKCS#7 padding.
        // 3.  Prepend IV to the ciphertext (Python version does the same).
        // 4.  Base-64 encode and return as String.
        // ---------------------------------------------------------------------
        let mut iv = [0u8; 16];
        OsRng.fill_bytes(&mut iv);

        let cipher = Aes256Cbc::new_from_slices(&self.key, &iv)?;
        let ciphertext = cipher.encrypt_vec(plaintext.as_bytes());

        // Glue = IV || CIPHERTEXT
        let mut result = Vec::with_capacity(iv.len() + ciphertext.len());
        result.extend_from_slice(&iv);
        result.extend_from_slice(&ciphertext);

        Ok(b64_encode(result))
    }

    /// Decrypt a Base-64 string produced by `encrypt`.
    pub fn decrypt(&self, b64_ciphertext: &str) -> anyhow::Result<String> {
        // ---------------------------------------------------------------------
        // 1.  Base-64 decode.
        // 2.  Split IV and encrypted payload.
        // 3.  Decrypt with AES-256-CBC + PKCS#7 unpadding.
        // 4.  Convert bytes → UTF-8 String.
        // ---------------------------------------------------------------------
        let raw = b64_decode(b64_ciphertext)?;

        if raw.len() < 16 {
            anyhow::bail!("ciphertext is too short");
        }
        let (iv, ciphertext) = raw.split_at(16);

        let cipher = Aes256Cbc::new_from_slices(&self.key, iv)?;
        let decrypted = cipher.decrypt_vec(ciphertext)?;

        Ok(String::from_utf8(decrypted)?)
    }
}

// ─── Quick demo ──────────────────────────────────────────────────────────────────
//
// fn main() -> anyhow::Result<()> {
//     let cipher = AESCipher::new("mysecretpassword");
//
//     let encrypted = cipher.encrypt("Secret Message A")?;
//     let decrypted = cipher.decrypt(&encrypted)?;
//
//     println!("Encrypted: {}", encrypted);
//     println!("Decrypted: {}", decrypted);
//
//     Ok(())
// }
// ---------------------------------------------------------------------------------