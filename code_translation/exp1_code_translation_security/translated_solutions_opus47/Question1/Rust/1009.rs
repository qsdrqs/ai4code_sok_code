// Cargo.toml dependencies:
// [dependencies]
// aes = "0.8"
// eax = "0.5"
// rand = "0.8"

use aes::Aes128;
use eax::aead::generic_array::GenericArray;
use eax::aead::{AeadInPlace, KeyInit};
use eax::Eax;
use rand::RngCore;

type Aes128Eax = Eax<Aes128>;

fn encode_string(message: &str, key: &[u8; 16], nonce: &[u8; 16]) -> (Vec<u8>, Vec<u8>) {
    let cipher = Aes128Eax::new(GenericArray::from_slice(key));
    // Encode string as bytes
    let mut buffer = message.as_bytes().to_vec();
    let tag = cipher
        .encrypt_in_place_detached(GenericArray::from_slice(nonce), b"", &mut buffer)
        .unwrap();
    (buffer, tag.to_vec())
}

fn decode_string(p: &(Vec<u8>, Vec<u8>), key: &[u8; 16], nonce: &[u8; 16]) -> String {
    let cipher = Aes128Eax::new(GenericArray::from_slice(key));
    let mut buffer = p.0.clone();
    cipher
        .decrypt_in_place_detached(
            GenericArray::from_slice(nonce),
            b"",
            &mut buffer,
            GenericArray::from_slice(&p.1),
        )
        .unwrap();
    // Decode from bytes to string
    String::from_utf8(buffer).unwrap()
}

fn main() {
    let original_message = "secret data";
    let mut nonce = [0u8; 16];
    let mut key = [0u8; 16];
    rand::thread_rng().fill_bytes(&mut nonce);
    rand::thread_rng().fill_bytes(&mut key);

    println!("{}", original_message);
    let ciphertext = encode_string(original_message, &key, &nonce);
    let decoded_string = decode_string(&ciphertext, &key, &nonce);
    println!("{}", decoded_string);
}