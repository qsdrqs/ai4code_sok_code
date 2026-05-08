// Cargo.toml dependencies:
// aes = "0.8"
// cbc = { version = "0.1", features = ["alloc"] }
// sha2 = "0.10"
// base64 = "0.21"
// rand = "0.8"

//! Functions that one encrypts and the other decrypts a given string using a given symmetric key

use aes::Aes256;
use base64::{engine::general_purpose::STANDARD, Engine};
use cbc::cipher::{block_padding::NoPadding, BlockDecryptMut, BlockEncryptMut, KeyIvInit};
use rand::RngCore;
use sha2::{Digest, Sha256};

type Aes256CbcEnc = cbc::Encryptor<Aes256>;
type Aes256CbcDec = cbc::Decryptor<Aes256>;

const AES_BLOCK_SIZE: usize = 16;

pub struct AESCipher {
    bs: usize,
    key: [u8; 32],
}

impl AESCipher {
    pub fn new(key: &str) -> Self {
        let mut hasher = Sha256::new();
        hasher.update(key.as_bytes());
        let result = hasher.finalize();
        let mut key_bytes = [0u8; 32];
        key_bytes.copy_from_slice(&result);
        AESCipher {
            bs: 32,
            key: key_bytes,
        }
    }

    pub fn encrypt(&self, raw: &str) -> String {
        let padded = self.pad(raw.as_bytes());
        let mut iv = [0u8; AES_BLOCK_SIZE];
        rand::thread_rng().fill_bytes(&mut iv);

        let cipher = Aes256CbcEnc::new(&self.key.into(), &iv.into());
        let ct = cipher.encrypt_padded_vec_mut::<NoPadding>(&padded);

        let mut result = iv.to_vec();
        result.extend_from_slice(&ct);

        STANDARD.encode(&result)
    }

    pub fn decrypt(&self, enc: &str) -> String {
        let enc_bytes = STANDARD.decode(enc).unwrap();
        let iv: [u8; AES_BLOCK_SIZE] = enc_bytes[..AES_BLOCK_SIZE].try_into().unwrap();
        let ciphertext = &enc_bytes[AES_BLOCK_SIZE..];

        let cipher = Aes256CbcDec::new(&self.key.into(), &iv.into());
        let pt = cipher
            .decrypt_padded_vec_mut::<NoPadding>(ciphertext)
            .unwrap();

        let unpadded = Self::unpad(&pt);
        String::from_utf8(unpadded.to_vec()).unwrap()
    }

    fn pad(&self, s: &[u8]) -> Vec<u8> {
        let pad_len = self.bs - s.len() % self.bs;
        let mut result = s.to_vec();
        for _ in 0..pad_len {
            result.push(pad_len as u8);
        }
        result
    }

    fn unpad(s: &[u8]) -> &[u8] {
        let pad_len = s[s.len() - 1] as usize;
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