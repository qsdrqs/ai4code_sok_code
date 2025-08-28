use aes::{Aes128, Aes192, Aes256, BlockCipher, NewCipher};
use aes_gcm::{Aes128Gcm, Aes192Gcm, Aes256Gcm, Key, Nonce};
use block_modes::{Cbc, block_padding::Pkcs7};
use rand::Rng;
use std::convert::TryInto;

// Define a function to encrypt a message
fn encrypt(message: &str, key: &[u8]) -> Vec<u8> {
    // Convert message to bytes
    let message = message.as_bytes();

    // Create a new AES cipher with the provided key
    let cipher = match key.len() {
        16 => Aes128::new_from_slice(key).unwrap(),
        24 => Aes192::new_from_slice(key).unwrap(),
        32 => Aes256::new_from_slice(key).unwrap(),
        _ => panic!("Invalid key size"),
    };

    // Create a random 128-bit IV
    let mut iv = [0u8; 16];
    rand::thread_rng().fill_bytes(&mut iv);

    // Create a CBC mode with PKCS#7 padding
    let mut encryptor = Cbc::new(cipher, iv.into());

    // Pad the message
    let padded_message = Pkcs7::pad(message, encryptor.block_size()).unwrap();

    // Encrypt the padded message
    let ciphertext = encryptor.encrypt(padded_message).unwrap();

    // Return the IV and ciphertext
    [&iv, &ciphertext].concat()
}

// Define a function to decrypt a ciphertext
fn decrypt(ciphertext: &[u8], key: &[u8]) -> String {
    // Extract the IV and ciphertext
    let iv = &ciphertext[..16];
    let ciphertext = &ciphertext[16..];

    // Create a new AES cipher with the provided key
    let cipher = match key.len() {
        16 => Aes128::new_from_slice(key).unwrap(),
        24 => Aes192::new_from_slice(key).unwrap(),
        32 => Aes256::new_from_slice(key).unwrap(),
        _ => panic!("Invalid key size"),
    };

    // Create a CBC mode with PKCS#7 padding
    let mut decryptor = Cbc::new(cipher, iv.into());

    // Decrypt the ciphertext
    let padded_message = decryptor.decrypt(ciphertext).unwrap();

    // Unpad the message
    let message = Pkcs7::unpad(&padded_message, decryptor.block_size()).unwrap();

    // Convert the message to a string
    String::from_utf8_lossy(message).into_owned()
}

fn main() {
    // Example usage
    let key = [0u8; 32]; // 256-bit key
    let message = "Hello, World!";
    let ciphertext = encrypt(message, &key);
    println!("Ciphertext: {:?}", ciphertext);

    let decrypted_message = decrypt(&ciphertext, &key);
    println!("Decrypted message: {}", decrypted_message);
}