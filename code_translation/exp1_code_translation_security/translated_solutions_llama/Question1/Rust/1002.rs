use openssl::symm::{Cipher, encrypt, decrypt, Mode};
use rand::Rng;
use std::error::Error;

// Define a function to encrypt data using AES in CBC mode
fn encrypt(data: &[u8], key: &[u8], iv: &[u8]) -> Result<Vec<u8>, Box<dyn Error>> {
    let cipher = Cipher::aes_256_cbc();
    let encrypted_data = encrypt(cipher, key, iv, data)?;
    Ok(encrypted_data)
}

// Define a function to decrypt data using AES in CBC mode
fn decrypt(data: &[u8], key: &[u8], iv: &[u8]) -> Result<Vec<u8>, Box<dyn Error>> {
    let cipher = Cipher::aes_256_cbc();
    let decrypted_data = decrypt(cipher, key, iv, data)?;
    Ok(decrypted_data)
}

// Define a test function
fn test() -> Result<(), Box<dyn Error>> {
    let data = b"a secret message";
    let mut rng = rand::thread_rng();
    let key: Vec<u8> = (0..32).map(|_| rng.gen()).collect();
    let iv: Vec<u8> = (0..16).map(|_| rng.gen()).collect();

    let encrypted_data = encrypt(data, &key, &iv)?;
    let decrypted_data = decrypt(&encrypted_data, &key, &iv)?;

    assert_eq!(decrypted_data, data);

    Ok(())
}

fn main() -> Result<(), Box<dyn Error>> {
    test()?;
    Ok(())
}