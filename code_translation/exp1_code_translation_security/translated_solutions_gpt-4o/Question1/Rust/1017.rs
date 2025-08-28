use aes_gcm::aead::{Aead, KeyInit, OsRng};
use aes_gcm::{Aes256Gcm, Nonce}; // Or `Aes128Gcm`
use aes_gcm::aead::generic_array::GenericArray;

// Function in Rust to encrypt a given string using a given symmetric key.
fn encrypt(key: &[u8], plaintext: &[u8]) -> (Vec<u8>, Vec<u8>, Vec<u8>) {
    let cipher = Aes256Gcm::new(GenericArray::from_slice(key));
    let nonce = Aes256Gcm::generate_nonce(&mut OsRng); // 96-bits; unique per message
    let ciphertext = cipher.encrypt(&nonce, plaintext).expect("encryption failure!");
    let tag = ciphertext[ciphertext.len() - 16..].to_vec(); // Extract the tag
    (nonce.to_vec(), tag, ciphertext)
}

// Function in Rust to decrypt a given string using a given symmetric key.
fn decrypt(key: &[u8], ciphertext: (Vec<u8>, Vec<u8>, Vec<u8>)) -> Vec<u8> {
    let (nonce, tag, ciphertext) = ciphertext;
    let cipher = Aes256Gcm::new(GenericArray::from_slice(key));
    let mut full_ciphertext = ciphertext.clone();
    full_ciphertext.extend_from_slice(&tag); // Append the tag to the ciphertext
    let plaintext = cipher.decrypt(Nonce::from_slice(&nonce), full_ciphertext.as_ref())
        .expect("decryption failure!");
    plaintext
}

fn main() {
    let key = b"an example very very secret key."; // 32 bytes for AES-256
    let plaintext = b"Hello, world!";

    let (nonce, tag, ciphertext) = encrypt(key, plaintext);
    println!("Nonce: {:?}", nonce);
    println!("Tag: {:?}", tag);
    println!("Ciphertext: {:?}", ciphertext);

    let decrypted_data = decrypt(key, (nonce, tag, ciphertext));
    println!("Decrypted: {:?}", String::from_utf8(decrypted_data).unwrap());
}