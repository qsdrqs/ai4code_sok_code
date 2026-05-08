// Import cryptographic libraries
use aes::Aes128;
use eax::Eax;
use eax::aead::{Aead, KeyInit};
use eax::aead::generic_array::GenericArray;
use rand::rngs::OsRng;
use rand::RngCore;

// Type alias for AES-128 in EAX mode
type Aes128Eax = Eax<Aes128>;

/// Function to encrypt a string using a symmetric key
pub fn encrypt(key: &[u8], data: &str) -> (Vec<u8>, Vec<u8>) {
    // Convert the data to bytes
    let data_bytes = data.as_bytes();
    // Create a cipher object using the key
    let cipher = Aes128Eax::new(GenericArray::from_slice(key));
    // Generate a random nonce (16 bytes, matching pycryptodome's default for EAX)
    let mut nonce_bytes = [0u8; 16];
    OsRng.fill_bytes(&mut nonce_bytes);
    let nonce = GenericArray::from_slice(&nonce_bytes);
    // Encrypt the data (the returned ciphertext has the authentication tag appended)
    let ciphertext = cipher
        .encrypt(nonce, data_bytes)
        .expect("encryption failure!");
    // Return the ciphertext and the nonce
    (ciphertext, nonce_bytes.to_vec())
}

/// Function to decrypt a string
pub fn decrypt(key: &[u8], nonce: &[u8], ciphertext: &[u8]) -> Vec<u8> {
    // Create a cipher object using the key
    let cipher = Aes128Eax::new(GenericArray::from_slice(key));
    let nonce = GenericArray::from_slice(nonce);
    // Decrypt the data
    cipher
        .decrypt(nonce, ciphertext)
        .expect("decryption failure!")
}

/// Function to generate a symmetric key
pub fn generate_key() -> Vec<u8> {
    // Generate a random key (16 bytes for AES-128)
    let mut key = [0u8; 16];
    OsRng.fill_bytes(&mut key);
    key.to_vec()
}

/*
// Example
fn main() {
    let key = generate_key();
    let test_data = "Jim's test";
    let (encrypted, nonce) = encrypt(&key, test_data);
    let restored_data = decrypt(&key, &nonce, &encrypted);
    println!("{}", String::from_utf8(restored_data).unwrap());
}
*/