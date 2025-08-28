extern crate base64;
extern crate rand;
extern crate ring;

use base64::{decode, encode};
use rand::Rng;
use ring::aead::*;
use ring::rand::{SecureRandom, SystemRandom};
use std::str;

fn generate_key() -> [u8; 32] {
    let mut key = [0u8; 32];
    let rng = SystemRandom::new();
    rng.fill(&mut key).unwrap();
    key
}

fn encrypt(key: &[u8; 32], message: &str) -> Vec<u8> {
    let sealing_key = SealingKey::new(&AES_256_GCM, key).unwrap();
    let mut nonce = [0u8; 12];
    rand::thread_rng().fill(&mut nonce);

    let mut in_out = message.as_bytes().to_vec();
    in_out.extend_from_slice(&[0u8; 16]); // space for the tag

    let aad = Aad::empty();
    seal_in_place(&sealing_key, &nonce, aad, &mut in_out, 16).unwrap();

    let mut result = nonce.to_vec();
    result.extend_from_slice(&in_out);
    result
}

fn decrypt(key: &[u8; 32], enc_message: &[u8]) -> String {
    let opening_key = OpeningKey::new(&AES_256_GCM, key).unwrap();
    let (nonce, in_out) = enc_message.split_at(12);

    let aad = Aad::empty();
    let decrypted_data = open_in_place(&opening_key, nonce, aad, 0, in_out).unwrap();

    str::from_utf8(decrypted_data).unwrap().to_string()
}

fn main() {
    let message = "the solutions are here somewhere";
    println!("Secret Message: {}", message);

    let key = generate_key();
    let enc_message = encrypt(&key, message);
    println!("Encrypted Message: {}", encode(&enc_message));

    let dec_message = decrypt(&key, &enc_message);
    println!("Decrypted Message: {}", dec_message);
}