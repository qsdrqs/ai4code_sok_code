use aes::Aes256;
use block_modes::{BlockMode, Cbc};
use block_modes::block_padding::Pkcs7;
use sha2::{Digest, Sha256};
use rand::Rng;
use base64::{encode, decode};
use std::str;

// Type alias for AES-256-CBC
type Aes256Cbc = Cbc<Aes256, Pkcs7>;

pub struct AESCipher {
    key: Vec<u8>,
    block_size: usize,
}

impl AESCipher {
    pub fn new(key: &str) -> Self {
        // Hash the key using SHA-256
        let mut hasher = Sha256::new();
        hasher.update(key.as_bytes());
        let key = hasher.finalize().to_vec();

        AESCipher {
            key,
            block_size: 32,
        }
    }

    pub fn encrypt(&self, raw: &str) -> String {
        let raw = self.pad(raw);
        let iv = rand::thread_rng().gen::<[u8; 16]>(); // Generate a random IV
        let cipher = Aes256Cbc::new_from_slices(&self.key, &iv).unwrap();
        let ciphertext = cipher.encrypt_vec(raw.as_bytes());
        let mut result = iv.to_vec();
        result.extend(ciphertext);
        encode(result) // Base64 encode the result
    }

    pub fn decrypt(&self, enc: &str) -> String {
        let enc = decode(enc).unwrap(); // Base64 decode the input
        let (iv, ciphertext) = enc.split_at(16); // Split into IV and ciphertext
        let cipher = Aes256Cbc::new_from_slices(&self.key, iv).unwrap();
        let decrypted_data = cipher.decrypt_vec(ciphertext).unwrap();
        let decrypted_str = str::from_utf8(&decrypted_data).unwrap();
        self.unpad(decrypted_str).to_string()
    }

    fn pad(&self, s: &str) -> String {
        let pad_len = self.block_size - (s.len() % self.block_size);
        let pad_char = pad_len as u8 as char;
        format!("{}{}", s, pad_char.to_string().repeat(pad_len))
    }

    fn unpad(&self, s: &str) -> &str {
        let pad_len = s.chars().last().unwrap() as usize;
        &s[..s.len() - pad_len]
    }
}

fn main() {
    let key = "mysecretpassword";
    let cipher = AESCipher::new(key);

    let encrypted = cipher.encrypt("Secret Message A");
    let decrypted = cipher.decrypt(&encrypted);

    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}