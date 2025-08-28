// --- dependencies (Cargo.toml) ---------------
// [dependencies]
// aes          = "0.8"          # AES block-cipher implementation
// block-modes  = "0.9"          # Common block-cipher modes of operation
// ------------------------------------------------

use aes::{Aes128, Aes192, Aes256};
use block_modes::{BlockMode, Ecb};
use block_modes::block_padding::NoPadding;

/// Type aliases for AES-ECB without padding (block size = 16 bytes)
type Aes128Ecb = Ecb<Aes128, NoPadding>;
type Aes192Ecb = Ecb<Aes192, NoPadding>;
type Aes256Ecb = Ecb<Aes256, NoPadding>;

/// Encrypt `data` with an AES key using ECB mode (no padding).
///
/// `data` *must* already be a multiple of 16 bytes, just like the
/// original Python version that used Crypto.Cipher.AES in ECB mode
/// without specifying padding.
pub fn encrypt_bytes_aes(data: &[u8], key: &[u8]) -> Vec<u8> {
    match key.len() {
        16 => Aes128Ecb::new_from_slices(key, &[]).unwrap()
                  .encrypt_vec(data),
        24 => Aes192Ecb::new_from_slices(key, &[]).unwrap()
                  .encrypt_vec(data),
        32 => Aes256Ecb::new_from_slices(key, &[]).unwrap()
                  .encrypt_vec(data),
        _  => panic!("Invalid AES key length: {} (expected 16/24/32 bytes)", key.len()),
    }
}

/// Decrypt `data` with an AES key using ECB mode (no padding).
///
/// Returns the raw plaintext bytes.
pub fn decrypt_bytes_aes(data: &[u8], key: &[u8]) -> Vec<u8> {
    match key.len() {
        16 => Aes128Ecb::new_from_slices(key, &[]).unwrap()
                  .decrypt_vec(data).unwrap(),
        24 => Aes192Ecb::new_from_slices(key, &[]).unwrap()
                  .decrypt_vec(data).unwrap(),
        32 => Aes256Ecb::new_from_slices(key, &[]).unwrap()
                  .decrypt_vec(data).unwrap(),
        _  => panic!("Invalid AES key length: {} (expected 16/24/32 bytes)", key.len()),
    }
}