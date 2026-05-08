use aes::Aes128;
use eax::Eax;
use eax::aead::{Aead, KeyInit, generic_array::GenericArray};
use rand::{RngCore, rngs::OsRng};

type Aes128Eax = Eax<Aes128>;

pub fn encrypt(m: &[u8], sk: &[u8]) -> (Vec<u8>, Vec<u8>) {
    let cipher = Aes128Eax::new_from_slice(sk).expect("invalid key length");
    let mut nonce_bytes = [0u8; 16];
    OsRng.fill_bytes(&mut nonce_bytes);
    let nonce = GenericArray::from_slice(&nonce_bytes);
    let ciphertext = cipher.encrypt(nonce, m).expect("encryption failed");
    (ciphertext, nonce_bytes.to_vec())
}

pub fn decrypt(cn: (Vec<u8>, Vec<u8>), sk: &[u8]) -> Vec<u8> {
    let (c, nonce_bytes) = cn;
    let cipher = Aes128Eax::new_from_slice(sk).expect("invalid key length");
    let nonce = GenericArray::from_slice(&nonce_bytes);
    cipher.decrypt(nonce, c.as_ref()).expect("decryption failed")
}