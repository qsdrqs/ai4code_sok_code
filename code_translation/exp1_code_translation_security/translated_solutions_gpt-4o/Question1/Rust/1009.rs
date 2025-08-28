extern crate aes;
extern crate rand;
extern crate aes_gcm_siv;

use aes::Aes128;
use aes_gcm_siv::{Aead, KeyInit, Nonce, aead::{AeadInPlace, NewAead}};
use aes_gcm_siv::aead::generic_array::GenericArray;
use rand::RngCore;
use std::str;

fn encode_string(message: &str, key: &[u8; 16], nonce: &[u8; 12]) -> (Vec<u8>, Vec<u8>) {
    let cipher = aes_gcm_siv::AesGcmSiv::new(GenericArray::from_slice(key));
    let nonce = Nonce::from_slice(nonce);

    let mut buffer = message.as_bytes().to_vec();
    let tag = cipher.encrypt_in_place_detached(nonce, b"", &mut buffer).expect("encryption failure!");

    (buffer, tag.to_vec())
}

fn decode_string(ciphertext: &[u8], tag: &[u8], key: &[u8; 16], nonce: &[u8; 12]) -> String {
    let cipher = aes_gcm_siv::AesGcmSiv::new(GenericArray::from_slice(key));
    let nonce = Nonce::from_slice(nonce);

    let mut buffer = ciphertext.to_vec();
    cipher.decrypt_in_place_detached(nonce, b"", &mut buffer, GenericArray::from_slice(tag))
        .expect("decryption failure!");

    String::from_utf8(buffer).expect("utf-8 conversion failure")
}

fn main() {
    let original_message = "secret data";
    let mut key = [0u8; 16];
    let mut nonce = [0u8; 12];
    rand::thread_rng().fill_bytes(&mut key);
    rand::thread_rng().fill_bytes(&mut nonce);

    println!("{}", original_message);
    let (ciphertext, tag) = encode_string(original_message, &key, &nonce);
    let decoded_string = decode_string(&ciphertext, &tag, &key, &nonce);
    println!("{}", decoded_string);
}