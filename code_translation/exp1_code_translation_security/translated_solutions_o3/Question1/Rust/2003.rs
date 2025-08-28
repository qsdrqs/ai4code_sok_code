use aes::Aes128;
use block_modes::{BlockMode, Ecb};
use block_modes::block_padding::NoPadding; // same “no-padding” semantics used in the Python code

type Aes128Ecb = Ecb<Aes128, NoPadding>;

/// Encrypt `input` with AES-128/ECB (no padding).
/// The buffer length must be a multiple of 16 bytes.
fn encode(input: &[u8], key: &[u8]) -> Vec<u8> {
    assert_eq!(input.len() % 16, 0, "Input length must be a multiple of 16");
    assert_eq!(key.len(), 16, "Key must be 16 bytes (AES-128)");
    let cipher = Aes128Ecb::new_from_slices(key, &[]).unwrap();
    cipher.encrypt_vec(input)
}

/// Decrypt `input` with AES-128/ECB (no padding).
fn decode(input: &[u8], key: &[u8]) -> Vec<u8> {
    assert_eq!(input.len() % 16, 0, "Input length must be a multiple of 16");
    assert_eq!(key.len(), 16, "Key must be 16 bytes (AES-128)");
    let cipher = Aes128Ecb::new_from_slices(key, &[]).unwrap();
    cipher.decrypt_vec(input).unwrap()
}

fn main() {
    let inp = b"1111111111111111"; // 16-byte plaintext
    let key = b"1111111111111111"; // 16-byte key

    let encrypted = encode(inp, key);
    let decrypted = decode(&encrypted, key);

    assert_eq!(&decrypted[..], inp);
    println!("round-trip succeeded");
}