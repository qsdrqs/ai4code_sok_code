// src/main.rs ---------------------------------------------------------------
use aes::Aes128;
use eax::{
    aead::{Aead, KeyInit, generic_array::GenericArray},
    Eax,
};
use rand::{rngs::OsRng, RngCore};

// Convenience alias: 128-bit AES in EAX mode
type Aes128Eax = Eax<Aes128>;

/// Generate a fresh 128-bit symmetric key
fn generate_key() -> [u8; 16] {
    let mut key = [0u8; 16];
    OsRng.fill_bytes(&mut key);      // system (OS) random source
    key
}

/// Encrypt a UTF-8 string and return (ciphertext, nonce)
fn encrypt(key: &[u8; 16], plaintext: &str) -> (Vec<u8>, [u8; 16]) {
    // Initialise cipher with the supplied key
    let cipher = Aes128Eax::new(GenericArray::from_slice(key));

    // Create a fresh random nonce (EAX supports 12-16 bytes; here we use 16)
    let mut nonce = [0u8; 16];
    OsRng.fill_bytes(&mut nonce);

    // Perform authenticated encryption
    let ciphertext = cipher
        .encrypt(GenericArray::from_slice(&nonce), plaintext.as_bytes())
        .expect("encryption failure");

    (ciphertext, nonce)
}

/// Decrypt and return the plaintext bytes
fn decrypt(key: &[u8; 16], nonce: &[u8; 16], ciphertext: &[u8]) -> Vec<u8> {
    let cipher = Aes128Eax::new(GenericArray::from_slice(key));

    cipher
        .decrypt(GenericArray::from_slice(nonce), ciphertext)
        .expect("decryption failure")           // also verifies authentication tag
}

fn main() {
    let key        = generate_key();
    let test_data  = "Jim's test";

    let (ct, nonce) = encrypt(&key, test_data);
    let pt          = decrypt(&key, &nonce, &ct);

    println!("{}", String::from_utf8(pt).unwrap());
}