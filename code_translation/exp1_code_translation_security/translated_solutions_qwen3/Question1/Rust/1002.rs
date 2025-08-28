// Import necessary crates
extern crate openssl;
extern crate rand;

use openssl::symm::{Cipher, Crypter, Mode, Padding};
use rand::RngCore;

// AES-256-CBC encryption function
fn encrypt(data: &[u8], key: &[u8], iv: &[u8]) -> Vec<u8> {
    let cipher = Cipher::aes_256_cbc();
    let mut crypter = Crypter::new(cipher, Mode::Encrypt, key, Some(iv)).unwrap();
    crypter.set_padding(Padding::None).unwrap(); // Disable padding

    let block_size = cipher.block_size();
    let mut out = vec![0; data.len() + block_size]; // Allocate enough space
    let mut count = crypter.update(data, &mut out).unwrap();
    count += crypter.finalize(&mut out).unwrap();
    out.truncate(count);
    out
}

// AES-256-CBC decryption function
fn decrypt(data: &[u8], key: &[u8], iv: &[u8]) -> Vec<u8> {
    let cipher = Cipher::aes_256_cbc();
    let mut crypter = Crypter::new(cipher, Mode::Decrypt, key, Some(iv)).unwrap();
    crypter.set_padding(Padding::None).unwrap(); // Disable padding

    let block_size = cipher.block_size();
    let mut out = vec![0; data.len() + block_size]; // Allocate enough space
    let mut count = crypter.update(data, &mut out).unwrap();
    count += crypter.finalize(&mut out).unwrap();
    out.truncate(count);
    out
}

// Test function to verify encryption and decryption
fn test() {
    let data = b"a secret message"; // 16 bytes, exactly one AES block
    let mut key = [0u8; 32]; // 256-bit key
    let mut iv = [0u8; 16];  // 128-bit IV

    // Generate random key and IV
    rand::thread_rng().fill(&mut key);
    rand::thread_rng().fill(&mut iv);

    // Encrypt and decrypt
    let encrypted = encrypt(data, &key, &iv);
    let decrypted = decrypt(&encrypted, &key, &iv);

    // Assert that the decrypted data matches the original
    assert_eq!(data, decrypted.as_slice());
}

fn main() {
    test();
    println!("Test passed: encryption and decryption are correct.");
}