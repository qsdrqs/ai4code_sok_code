use std::hash::{Hash, Hasher};
use std::io::{self, Write};
use std::str;

use aes_gcm::{Aes256Gcm, Key, Nonce};
use base64;
use rand::Rng;
use sha2::Sha256;

/// A class for AES encryption and decryption
pub struct AESCipher {
    key: Key,
    bs: usize,
}

impl AESCipher {
    /// Creates a new AESCipher instance with the given key
    pub fn new(key: &str) -> Self {
        let mut hasher = Sha256::new();
        hasher.write(key.as_bytes()).unwrap();
        let key = hasher.finalize().to_vec();
        AESCipher {
            key: Key::from_slice(&key),
            bs: 32,
        }
    }

    /// Encrypts the given raw string
    pub fn encrypt(&self, raw: &str) -> io::Result<String> {
        let raw = self._pad(raw);
        let mut rng = rand::thread_rng();
        let nonce = Aes256Gcm::generate_nonce(&mut rng).unwrap();
        let cipher = Aes256Gcm::new(self.key);
        let encrypted = cipher.encrypt(&nonce, raw.as_bytes()).unwrap();
        let mut result = Vec::new();
        result.extend_from_slice(&nonce);
        result.extend_from_slice(&encrypted);
        Ok(base64::encode(&result))
    }

    /// Decrypts the given encrypted string
    pub fn decrypt(&self, enc: &str) -> io::Result<String> {
        let enc = base64::decode(enc).unwrap();
        let nonce = &enc[..12];
        let encrypted = &enc[12..];
        let cipher = Aes256Gcm::new(self.key);
        let decrypted = cipher.decrypt(nonce, encrypted).unwrap();
        Ok(self._unpad(&decrypted).to_string())
    }

    /// Pads the given string to a multiple of the block size
    fn _pad(&self, s: &str) -> String {
        let padding_len = self.bs - (s.len() % self.bs);
        let mut padded = s.to_string();
        for _ in 0..padding_len {
            padded.push((padding_len as u8) as char);
        }
        padded
    }

    /// Unpads the given string
    fn _unpad(&self, s: &[u8]) -> &str {
        let padding_len = s[s.len() - 1] as usize;
        str::from_utf8(&s[..s.len() - padding_len]).unwrap()
    }
}

fn main() {
    let key = "mysecretpassword";
    let cipher = AESCipher::new(key);
    let encrypted = cipher.encrypt("Secret Message A").unwrap();
    let decrypted = cipher.decrypt(&encrypted).unwrap();

    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}