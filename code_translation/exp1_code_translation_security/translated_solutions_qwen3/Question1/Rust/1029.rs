use aes::{Aes128, Aes192, Aes256};
use block_modes::{BlockMode, Ecb};
use block_modes::block_padding::NoPadding;

/// A struct to perform AES encryption and decryption in ECB mode with manual PKCS#7 padding.
pub struct AesCipher {
    key: Vec<u8>,
    blk_sz: usize,
}

impl AesCipher {
    /// Creates a new `AesCipher` instance with the given key and block size.
    pub fn new(key: Vec<u8>, blk_sz: usize) -> Self {
        Self { key, blk_sz }
    }

    /// Encrypts the given message using AES in ECB mode with PKCS#7 padding.
    pub fn encrypt(&self, msg: &[u8]) -> Vec<u8> {
        // Apply PKCS#7 padding
        let pad_len = self.blk_sz - (msg.len() % self.blk_sz);
        let pad_val = pad_len as u8;
        let mut padded = msg.to_vec();
        padded.resize(padded.len() + pad_len, pad_val);

        // Dummy IV (ECB mode ignores it)
        let iv = [0u8; 16];

        // Choose the appropriate AES variant based on key length
        match self.key.len() {
            16 => {
                type Mode = Ecb<Aes128, NoPadding>;
                let cipher = Mode::new_from_slices(&self.key, &iv).expect("Invalid key length");
                cipher.encrypt_vec(&padded)
            }
            24 => {
                type Mode = Ecb<Aes192, NoPadding>;
                let cipher = Mode::new_from_slices(&self.key, &iv).expect("Invalid key length");
                cipher.encrypt_vec(&padded)
            }
            32 => {
                type Mode = Ecb<Aes256, NoPadding>;
                let cipher = Mode::new_from_slices(&self.key, &iv).expect("Invalid key length");
                cipher.encrypt_vec(&padded)
            }
            _ => panic!("Invalid key length"),
        }
    }

    /// Decrypts the given ciphertext using AES in ECB mode and removes PKCS#7 padding.
    pub fn decrypt(&self, ciphertext: &[u8]) -> Vec<u8> {
        // Dummy IV (ECB mode ignores it)
        let iv = [0u8; 16];

        // Choose the appropriate AES variant based on key length
        let padded = match self.key.len() {
            16 => {
                type Mode = Ecb<Aes128, NoPadding>;
                let cipher = Mode::new_from_slices(&self.key, &iv).expect("Invalid key length");
                cipher.decrypt_vec(ciphertext).expect("Decryption failed")
            }
            24 => {
                type Mode = Ecb<Aes192, NoPadding>;
                let cipher = Mode::new_from_slices(&self.key, &iv).expect("Invalid key length");
                cipher.decrypt_vec(ciphertext).expect("Decryption failed")
            }
            32 => {
                type Mode = Ecb<Aes256, NoPadding>;
                let cipher = Mode::new_from_slices(&self.key, &iv).expect("Invalid key length");
                cipher.decrypt_vec(ciphertext).expect("Decryption failed")
            }
            _ => panic!("Invalid key length"),
        };

        // Remove PKCS#7 padding
        let pad_len = *padded.last().expect("Empty data") as usize;
        padded[..padded.len() - pad_len].to_vec()
    }
}