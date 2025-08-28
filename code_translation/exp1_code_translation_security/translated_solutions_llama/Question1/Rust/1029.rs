use aes::Aes128;
use block_modes::{Ecb, block_modes::Mode};
use padding::Padding;
use rand::Rng;

// Define a struct to hold the AES cipher
struct AESCipher {
    key: Vec<u8>,
    blk_sz: usize,
}

impl AESCipher {
    // Create a new AES cipher instance
    fn new(key: Vec<u8>, blk_sz: usize) -> Self {
        AESCipher { key, blk_sz }
    }

    // Encrypt a message
    fn encrypt(&self, msg: Vec<u8>) -> Vec<u8> {
        // Padding
        let pad_len = self.blk_sz - (msg.len() % self.blk_sz);
        let mut padded_msg = msg.clone();
        padded_msg.resize(msg.len() + pad_len, pad_len as u8);

        // Encryption
        let cipher = Ecb::new(Aes128::new_from_slice(&self.key).unwrap());
        let encrypted_msg = cipher.encrypt(&padded_msg).unwrap();

        encrypted_msg
    }

    // Decrypt a ciphertext
    fn decrypt(&self, ciphertext: Vec<u8>) -> Vec<u8> {
        // Decryption
        let cipher = Ecb::new(Aes128::new_from_slice(&self.key).unwrap());
        let decrypted_msg = cipher.decrypt(&ciphertext).unwrap();

        // Remove padding
        let pad_len = decrypted_msg[decrypted_msg.len() - 1] as usize;
        &decrypted_msg[..decrypted_msg.len() - pad_len].to_vec().clone()
    }
}

fn main() {
    // Example usage
    let key = vec![0x00; 16]; // 128-bit key
    let blk_sz = 16; // Block size in bytes
    let cipher = AESCipher::new(key, blk_sz);

    let msg = b"Hello, World!";
    let encrypted_msg = cipher.encrypt(msg.to_vec());
    println!("Encrypted message: {:?}", encrypted_msg);

    let decrypted_msg = cipher.decrypt(encrypted_msg);
    println!("Decrypted message: {:?}", decrypted_msg);
}