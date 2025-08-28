extern crate aes_gcm;
extern crate aes;
extern crate aes_gcm_siv;

use aes_gcm::{Aes256Gcm, Key, Nonce}; // Or `Aes128Gcm`
use aes_gcm::aead::{Aead, NewAead};

fn encrypt(key: &[u8], msg: &[u8]) -> (Vec<u8>, Vec<u8>, Vec<u8>) {
    let key = Key::from_slice(key);
    let cipher = Aes256Gcm::new(key);

    let nonce = aes_gcm::Nonce::from_slice(b"unique nonce"); // 12-bytes; unique per message
    let ciphertext = cipher.encrypt(nonce, msg).expect("encryption failure!");

    (ciphertext, Vec::new(), nonce.to_vec()) // Tag is not separately returned in aes_gcm
}

fn decrypt(key: &[u8], ciphertext: &[u8], nonce: &[u8], _tag: Option<&[u8]>) -> Vec<u8> {
    let key = Key::from_slice(key);
    let cipher = Aes256Gcm::new(key);

    let nonce = Nonce::from_slice(nonce);
    let plaintext = cipher.decrypt(nonce, ciphertext).expect("decryption failure!");

    plaintext
}

fn main() {
    let key = b"Sixteen byte keySixteen byte key"; // 32 bytes for AES-256
    let message = b"message";

    let (ctext, _tag, nonce) = encrypt(key, message);
    let decrypted_message = decrypt(key, &ctext, &nonce, None);

    println!("{:?}", String::from_utf8(decrypted_message).unwrap());
}