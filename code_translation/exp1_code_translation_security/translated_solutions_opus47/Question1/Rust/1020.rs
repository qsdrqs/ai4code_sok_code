// Cargo.toml dependencies:
// [dependencies]
// aes = "0.8"
// cbc = { version = "0.1", features = ["alloc"] }
// base64 = "0.21"
// rand = "0.8"

use aes::{Aes128, Aes192, Aes256};
use base64::{engine::general_purpose, Engine as _};
use cbc::cipher::{block_padding::Pkcs7, BlockDecryptMut, BlockEncryptMut, KeyIvInit};
use rand::rngs::OsRng;
use rand::RngCore;

type Aes128CbcEnc = cbc::Encryptor<Aes128>;
type Aes128CbcDec = cbc::Decryptor<Aes128>;
type Aes192CbcEnc = cbc::Encryptor<Aes192>;
type Aes192CbcDec = cbc::Decryptor<Aes192>;
type Aes256CbcEnc = cbc::Encryptor<Aes256>;
type Aes256CbcDec = cbc::Decryptor<Aes256>;

// Encrypt plaintext using symmetric key (AES-CBC with PKCS7 padding)
pub fn encrypt(key: &[u8], key_size: usize, plaintext: &[u8]) -> Vec<u8> {
    // Equivalent to os.urandom(16)
    let mut iv = [0u8; 16];
    OsRng.fill_bytes(&mut iv);

    // Pad + encrypt. PyCryptodome defaults to PKCS7 for CBC mode.
    let cipher_txt = match key_size {
        16 => Aes128CbcEnc::new_from_slices(key, &iv)
            .unwrap()
            .encrypt_padded_vec_mut::<Pkcs7>(plaintext),
        24 => Aes192CbcEnc::new_from_slices(key, &iv)
            .unwrap()
            .encrypt_padded_vec_mut::<Pkcs7>(plaintext),
        32 => Aes256CbcEnc::new_from_slices(key, &iv)
            .unwrap()
            .encrypt_padded_vec_mut::<Pkcs7>(plaintext),
        _ => panic!("Invalid key size"),
    };

    // Concatenate IV + ciphertext, then base64-encode
    let mut combined = Vec::with_capacity(16 + cipher_txt.len());
    combined.extend_from_slice(&iv);
    combined.extend_from_slice(&cipher_txt);

    general_purpose::STANDARD.encode(&combined).into_bytes()
}

// Decrypt ciphertext using symmetric key
pub fn decrypt(key: &[u8], key_size: usize, ciphertext: &[u8]) -> Vec<u8> {
    // base64 decode
    let data = general_purpose::STANDARD
        .decode(ciphertext)
        .expect("Invalid base64");

    // Split IV (first 16 bytes) from ciphertext
    let iv = &data[..16];
    let encrypted = &data[16..];

    // Decrypt and unpad (PKCS7)
    match key_size {
        16 => Aes128CbcDec::new_from_slices(key, iv)
            .unwrap()
            .decrypt_padded_vec_mut::<Pkcs7>(encrypted)
            .expect("Decryption failed"),
        24 => Aes192CbcDec::new_from_slices(key, iv)
            .unwrap()
            .decrypt_padded_vec_mut::<Pkcs7>(encrypted)
            .expect("Decryption failed"),
        32 => Aes256CbcDec::new_from_slices(key, iv)
            .unwrap()
            .decrypt_padded_vec_mut::<Pkcs7>(encrypted)
            .expect("Decryption failed"),
        _ => panic!("Invalid key size"),
    }
}

fn main() {
    let key = b"0123456789abcdef"; // 16-byte key for AES-128
    let plaintext = b"Hello, World! This is a test message.";

    let encrypted = encrypt(key, 16, plaintext);
    println!("Encrypted: {}", String::from_utf8_lossy(&encrypted));

    let decrypted = decrypt(key, 16, &encrypted);
    println!("Decrypted: {}", String::from_utf8_lossy(&decrypted));
}