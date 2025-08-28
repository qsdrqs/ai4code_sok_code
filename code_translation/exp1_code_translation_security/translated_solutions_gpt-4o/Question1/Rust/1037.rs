extern crate base64;
extern crate rand;
extern crate ring;

use base64::{decode, encode};
use rand::Rng;
use ring::aead::*;
use std::str;

fn generate_key() -> [u8; 32] {
    rand::thread_rng().gen()
}

fn encrypt(input: &[u8], key: &[u8; 32]) -> Vec<u8> {
    let sealing_key = SealingKey::new(&AES_256_GCM, key).unwrap();
    let mut in_out = input.to_vec();
    in_out.extend_from_slice(&[0u8; 16]); // space for the tag
    seal_in_place(&sealing_key, &[], &mut in_out, 16, &[]).unwrap();
    in_out
}

fn decrypt(input: &[u8], key: &[u8; 32]) -> Vec<u8> {
    let opening_key = OpeningKey::new(&AES_256_GCM, key).unwrap();
    let mut in_out = input.to_vec();
    open_in_place(&opening_key, &[], &mut in_out, 0, &[]).unwrap().to_vec()
}

fn main() {
    let key = generate_key();
    let input = b"super secret";
    let encrypted_input = encrypt(input, &key);
    let decrypted_input = decrypt(&encrypted_input, &key);
    println!("{}", str::from_utf8(input).unwrap());
    println!("{}", str::from_utf8(&decrypted_input).unwrap());
}