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

fn encrypt(plaintext: &str, key: &[u8; 32]) -> String {
    let nonce = rand::thread_rng().gen::<[u8; 12]>();
    let sealing_key = SealingKey::new(&AES_256_GCM, key).unwrap();
    let mut in_out = plaintext.as_bytes().to_vec();
    in_out.extend_from_slice(&[0u8; 16]); // space for the tag
    seal_in_place(&sealing_key, &nonce, Aad::empty(), &mut in_out, 16).unwrap();
    let mut result = nonce.to_vec();
    result.extend_from_slice(&in_out);
    encode(&result)
}

fn decrypt(ciphertext: &str, key: &[u8; 32]) -> String {
    let decoded = decode(ciphertext).unwrap();
    let (nonce, in_out) = decoded.split_at(12);
    let opening_key = OpeningKey::new(&AES_256_GCM, key).unwrap();
    let mut in_out = in_out.to_vec();
    let decrypted_data = open_in_place(&opening_key, nonce, Aad::empty(), 0, &mut in_out).unwrap();
    str::from_utf8(decrypted_data).unwrap().to_string()
}

fn main() {
    let ex_key = generate_key();
    let encrypted = encrypt("Hello World!", &ex_key);
    println!("Encrypted: {}", encrypted);
    let decrypted = decrypt(&encrypted, &ex_key);
    println!("Decrypted: {}", decrypted);
}