// Cargo.toml dependencies:
// aes = "0.8"

use aes::Aes128;
use aes::cipher::{BlockEncrypt, BlockDecrypt, KeyInit, generic_array::GenericArray};

pub struct AEScipher {
    key: Vec<u8>,
    blk_sz: usize,
}

impl AEScipher {
    pub fn new(key: Vec<u8>, blk_sz: usize) -> Self {
        AEScipher { key, blk_sz }
    }

    pub fn encrypt(&self, msg: &[u8]) -> Vec<u8> {
        // Padding (PKCS7)
        let pad_len = self.blk_sz - (msg.len() % self.blk_sz);
        let mut padded_msg = msg.to_vec();
        padded_msg.extend(std::iter::repeat(pad_len as u8).take(pad_len));

        // Encryption
        let cipher = Aes128::new(GenericArray::from_slice(&self.key));
        let mut ciphertext = Vec::with_capacity(padded_msg.len());

        for chunk in padded_msg.chunks(self.blk_sz) {
            let mut block = GenericArray::clone_from_slice(chunk);
            cipher.encrypt_block(&mut block);
            ciphertext.extend_from_slice(&block);
        }

        ciphertext
    }

    pub fn decrypt(&self, ciphertext: &[u8]) -> Vec<u8> {
        // Decryption
        let cipher = Aes128::new(GenericArray::from_slice(&self.key));
        let mut msg = Vec::with_capacity(ciphertext.len());

        for chunk in ciphertext.chunks(self.blk_sz) {
            let mut block = GenericArray::clone_from_slice(chunk);
            cipher.decrypt_block(&mut block);
            msg.extend_from_slice(&block);
        }

        // Remove padding
        let pad_len = *msg.last().unwrap() as usize;
        msg.truncate(msg.len() - pad_len);
        msg
    }
}

// Example usage
fn main() {
    let key = b"0123456789abcdef".to_vec(); // 16-byte key for AES-128
    let cipher = AEScipher::new(key, 16);

    let plaintext = b"Hello, World!";
    let encrypted = cipher.encrypt(plaintext);
    println!("Encrypted: {:?}", encrypted);

    let decrypted = cipher.decrypt(&encrypted);
    println!("Decrypted: {}", String::from_utf8_lossy(&decrypted));
}