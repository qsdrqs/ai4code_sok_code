use aes::Aes256;
use block_modes::BlockMode;
use block_modes::Cbc;
use block_modes::block_padding::NoPadding;
use sha2::{Digest, Sha256};
use rand::Rng;
use base64;

/// AESCipher struct that holds the AES key derived from a password.
pub struct AESCipher {
    key: [u8; 32],
}

impl AESCipher {
    /// Creates a new AESCipher instance using the given key string.
    /// The key is hashed using SHA-256 to produce a 32-byte AES-256 key.
    pub fn new(key: &str) -> Self {
        let mut hasher = Sha256::new();
        hasher.update(key.as_bytes());
        let key_bytes = hasher.finalize();
        let mut key_256 = [0u8; 32];
        key_256.copy_from_slice(&key_bytes);
        AESCipher { key: key_256 }
    }

    /// Encrypts the given plaintext string using AES-256 in CBC mode.
    /// Returns the base64-encoded string of the IV + encrypted data.
    pub fn encrypt(&self, raw: &str) -> String {
        let padded = Self::pad(raw.as_bytes(), 32);
        let iv = Self::generate_iv();
        let cipher = Cbc::<Aes256, NoPadding>::new(&self.key, &iv);
        let encrypted = cipher.encrypt(&padded).expect("Encryption failed");
        let mut result = iv.to_vec();
        result.extend_from_slice(&encrypted);
        base64::encode(result)
    }

    /// Decrypts the given base64-encoded string using AES-256 in CBC mode.
    /// Returns the original plaintext string.
    pub fn decrypt(&self, enc: &str) -> String {
        let decoded = base64::decode(enc).expect("Invalid base64");
        let (iv, data) = decoded.split_at(16);
        let cipher = Cbc::<Aes256, NoPadding>::new(&self.key, iv);
        let decrypted = cipher.decrypt(data).expect("Decryption failed");
        let unpaded = Self::unpad(&decrypted);
        String::from_utf8(unpaded.to_vec()).expect("Invalid UTF-8")
    }

    /// Pads the input data to a multiple of the given block size.
    /// The padding value is equal to the number of padding bytes added.
    fn pad(data: &[u8], block_size: usize) -> Vec<u8> {
        let padding_len = block_size - (data.len() % block_size);
        let padding = vec![padding_len as u8; padding_len];
        [data, padding.as_slice()].concat()
    }

    /// Removes padding from the end of the data.
    /// Assumes the last byte indicates the number of padding bytes.
    fn unpad(data: &[u8]) -> &[u8] {
        if data.is_empty() {
            return data;
        }
        let padding_len = data[data.len() - 1] as usize;
        &data[..data.len() - padding_len]
    }

    /// Generates a 16-byte random IV using a secure random number generator.
    fn generate_iv() -> [u8; 16] {
        let mut iv = [0u8; 16];
        rand::thread_rng().fill(&mut iv);
        iv
    }
}

// Example usage:
// fn main() {
//     let key = "mysecretpassword";
//     let cipher = AESCipher::new(key);
//     let encrypted = cipher.encrypt("Secret Message A");
//     let decrypted = cipher.decrypt(&encrypted);
//     println!("Encrypted: {}", encrypted);
//     println!("Decrypted: {}", decrypted);
// }