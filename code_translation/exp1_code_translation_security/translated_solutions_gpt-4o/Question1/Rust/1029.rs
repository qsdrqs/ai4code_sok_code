use aes::Aes128;
use block_modes::{BlockMode, Ecb};
use block_modes::block_padding::Pkcs7;
use std::str;

// Type alias for AES-128 in ECB mode with PKCS7 padding
type AesEcb = Ecb<Aes128, Pkcs7>;

pub struct AEScipher {
    key: Vec<u8>,
    blk_sz: usize,
}

impl AEScipher {
    pub fn new(key: Vec<u8>, blk_sz: usize) -> Self {
        AEScipher { key, blk_sz }
    }

    pub fn encrypt(&self, msg: &str) -> Vec<u8> {
        // Padding
        let mut padded_msg = msg.as_bytes().to_vec();
        let pad_len = self.blk_sz - (padded_msg.len() % self.blk_sz);
        padded_msg.extend(vec![pad_len as u8; pad_len]);

        // Encryption
        let cipher = AesEcb::new_from_slices(&self.key, Default::default())
            .expect("Invalid key length");
        cipher.encrypt_vec(&padded_msg)
    }

    pub fn decrypt(&self, ciphertext: &[u8]) -> String {
        // Decryption
        let cipher = AesEcb::new_from_slices(&self.key, Default::default())
            .expect("Invalid key length");
        let decrypted_data = cipher.decrypt_vec(ciphertext)
            .expect("Decryption failed");

        // Remove padding
        let pad_len = *decrypted_data.last().unwrap() as usize;
        let msg = &decrypted_data[..decrypted_data.len() - pad_len];
        str::from_utf8(msg).expect("Invalid UTF-8").to_string()
    }
}

fn main() {
    // Example usage
    let key = b"verysecretkey12".to_vec(); // 16-byte key for AES-128
    let blk_sz = 16; // Block size for AES
    let cipher = AEScipher::new(key, blk_sz);

    let plaintext = "Hello, AES!";
    let ciphertext = cipher.encrypt(plaintext);
    println!("Ciphertext: {:?}", ciphertext);

    let decrypted_text = cipher.decrypt(&ciphertext);
    println!("Decrypted text: {}", decrypted_text);
}