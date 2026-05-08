// Cargo.toml:
// [dependencies]
// openssl = "0.10"

use openssl::rand::rand_bytes;
use openssl::symm::{Cipher, Crypter, Mode};

fn encrypt(data: &[u8], key: &[u8], iv: &[u8]) -> Vec<u8> {
    // Select AES variant based on key length (matches Python's algorithms.AES(key))
    let cipher = match key.len() {
        16 => Cipher::aes_128_cbc(),
        24 => Cipher::aes_192_cbc(),
        32 => Cipher::aes_256_cbc(),
        _ => panic!("Invalid AES key length"),
    };
    let block_size = cipher.block_size();

    let mut crypter = Crypter::new(cipher, Mode::Encrypt, key, Some(iv)).unwrap();
    // Python's cryptography Cipher doesn't pad by default; OpenSSL does, so disable it.
    crypter.pad(false);

    let mut ciphertext = vec![0u8; data.len() + block_size];
    let count = crypter.update(data, &mut ciphertext).unwrap();
    let rest = crypter.finalize(&mut ciphertext[count..]).unwrap();
    ciphertext.truncate(count + rest);
    ciphertext
}

fn decrypt(data: &[u8], key: &[u8], iv: &[u8]) -> Vec<u8> {
    let cipher = match key.len() {
        16 => Cipher::aes_128_cbc(),
        24 => Cipher::aes_192_cbc(),
        32 => Cipher::aes_256_cbc(),
        _ => panic!("Invalid AES key length"),
    };
    let block_size = cipher.block_size();

    let mut crypter = Crypter::new(cipher, Mode::Decrypt, key, Some(iv)).unwrap();
    crypter.pad(false);

    let mut plaintext = vec![0u8; data.len() + block_size];
    let count = crypter.update(data, &mut plaintext).unwrap();
    let rest = crypter.finalize(&mut plaintext[count..]).unwrap();
    plaintext.truncate(count + rest);
    plaintext
}

fn test() {
    let data = b"a secret message"; // 16 bytes, exactly one AES block
    let mut key = [0u8; 32];
    let mut iv = [0u8; 16];
    rand_bytes(&mut key).unwrap();
    rand_bytes(&mut iv).unwrap();

    let e = encrypt(data, &key, &iv);
    let d = decrypt(&e, &key, &iv);
    assert_eq!(d.as_slice(), data);
}

fn main() {
    test();
    println!("Test passed!");
}