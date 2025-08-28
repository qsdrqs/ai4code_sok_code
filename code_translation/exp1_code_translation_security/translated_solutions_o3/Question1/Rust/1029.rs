// Cargo.toml must contain (already supplied as requested):
// [dependencies]
// aes          = "0.8"
// block-modes  = { version = "0.9", default-features = false, features = ["aes", "no-padding"] }

use aes::{Aes128, Aes192, Aes256};
use block_modes::{BlockMode, Ecb};
use block_modes::block_padding::NoPadding;

/// Helper enum so we can accept 128/192/256-bit keys just like PyCrypto.
enum CipherImpl {
    A128(Ecb<Aes128, NoPadding>),
    A192(Ecb<Aes192, NoPadding>),
    A256(Ecb<Aes256, NoPadding>),
}

impl CipherImpl {
    fn encrypt_vec(&self, data: &[u8]) -> Vec<u8> {
        match self {
            CipherImpl::A128(c) => c.encrypt_vec(data),
            CipherImpl::A192(c) => c.encrypt_vec(data),
            CipherImpl::A256(c) => c.encrypt_vec(data),
        }
    }

    fn decrypt_vec(&self, data: &[u8]) -> Vec<u8> {
        match self {
            CipherImpl::A128(c) => c.decrypt_vec(data).expect("decryption failed"),
            CipherImpl::A192(c) => c.decrypt_vec(data).expect("decryption failed"),
            CipherImpl::A256(c) => c.decrypt_vec(data).expect("decryption failed"),
        }
    }
}

/// A faithful port of the original Python `AEScipher` class.
pub struct AESCipher {
    cipher: CipherImpl,
    blk_sz: usize,
}

impl AESCipher {
    /// `key` must be 16, 24 or 32 bytes.
    pub fn new(key: &[u8], blk_sz: usize) -> Self {
        let cipher = match key.len() {
            16 => CipherImpl::A128(Ecb::<Aes128, NoPadding>::new_from_slices(key, &[]).unwrap()),
            24 => CipherImpl::A192(Ecb::<Aes192, NoPadding>::new_from_slices(key, &[]).unwrap()),
            32 => CipherImpl::A256(Ecb::<Aes256, NoPadding>::new_from_slices(key, &[]).unwrap()),
            _  => panic!("Invalid AES key length. Must be 16/24/32 bytes."),
        };
        Self { cipher, blk_sz }
    }

    pub fn encrypt(&self, msg: &[u8]) -> Vec<u8> {
        // --- PKCS#7 padding -------------------------------------------------
        let mut data = msg.to_vec();
        let pad_len = self.blk_sz - (data.len() % self.blk_sz);
        data.extend(std::iter::repeat(pad_len as u8).take(pad_len));
        // --------------------------------------------------------------------
        self.cipher.encrypt_vec(&data)
    }

    pub fn decrypt(&self, ciphertext: &[u8]) -> Vec<u8> {
        let mut plain = self.cipher.decrypt_vec(ciphertext);
        // --- remove PKCS#7 padding -----------------------------------------
        let pad_len = *plain.last().expect("empty plaintext") as usize;
        plain.truncate(plain.len() - pad_len);
        // --------------------------------------------------------------------
        plain
    }
}

/* --------------------------------------------------------------------------
   Small usage example

fn main() {
    let key     = b"YELLOW SUBMARINE"; // 16 bytes => AES-128
    let message = b"Attack at dawn!";

    let aes = AESCipher::new(key, 16);

    let ct = aes.encrypt(message);
    println!("ciphertext: {:02x?}", ct);

    let pt = aes.decrypt(&ct);
    println!("decrypted : {:?}", String::from_utf8(pt).unwrap());
}
-------------------------------------------------------------------------- */