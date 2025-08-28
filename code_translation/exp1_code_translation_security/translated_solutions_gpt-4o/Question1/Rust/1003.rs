use aes::Aes128;
use block_modes::{BlockMode, Ecb};
use block_modes::block_padding::NoPadding;

// Type alias for AES-128 in ECB mode with no padding
type AesEcb = Ecb<Aes128, NoPadding>;

pub fn encrypt_bytes_aes(data: &[u8], key: &[u8]) -> Vec<u8> {
    // Create a new AES cipher in ECB mode
    let cipher = AesEcb::new_from_slices(key, Default::default()).unwrap();

    // Encrypt the data
    cipher.encrypt_vec(data)
}

pub fn decrypt_bytes_aes(data: &[u8], key: &[u8]) -> Vec<u8> {
    // Create a new AES cipher in ECB mode
    let cipher = AesEcb::new_from_slices(key, Default::default()).unwrap();

    // Decrypt the data
    cipher.decrypt_vec(data).unwrap()
}