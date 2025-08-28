extern crate ring;
extern crate base64;
extern crate rand;

use ring::pbkdf2;
use ring::rand::{SecureRandom, SystemRandom};
use ring::aead::{Aad, LessSafeKey, UnboundKey, Nonce, AES_256_GCM, aead};
use base64::{encode_config, decode_config, URL_SAFE_NO_PAD};
use std::num::NonZeroU32;
use std::str;

fn derive_key(password: &[u8], salt: &[u8], iterations: u32) -> Vec<u8> {
    let mut key = [0u8; 32];
    let pbkdf2_iterations = NonZeroU32::new(iterations).unwrap();
    pbkdf2::derive(
        pbkdf2::PBKDF2_HMAC_SHA256,
        pbkdf2_iterations,
        salt,
        password,
        &mut key,
    );
    key.to_vec()
}

fn encrypt(message: &str, password: &str, iterations: u32) -> String {
    let rng = SystemRandom::new();
    let mut salt = [0u8; 16];
    rng.fill(&mut salt).unwrap();

    let key = derive_key(password.as_bytes(), &salt, iterations);
    let unbound_key = UnboundKey::new(&AES_256_GCM, &key).unwrap();
    let less_safe_key = LessSafeKey::new(unbound_key);

    let nonce = Nonce::assume_unique_for_key([0u8; 12]); // Normally, you'd use a random nonce
    let mut in_out = message.as_bytes().to_vec();
    less_safe_key.seal_in_place_append_tag(nonce, Aad::empty(), &mut in_out).unwrap();

    let mut result = Vec::new();
    result.extend_from_slice(&salt);
    result.extend_from_slice(&iterations.to_be_bytes());
    result.extend_from_slice(&in_out);

    encode_config(&result, URL_SAFE_NO_PAD)
}

fn decrypt(token: &str, password: &str) -> String {
    let decoded = decode_config(token, URL_SAFE_NO_PAD).unwrap();
    let salt = &decoded[..16];
    let iter_bytes = &decoded[16..20];
    let iterations = u32::from_be_bytes(iter_bytes.try_into().unwrap());
    let encrypted_message = &decoded[20..];

    let key = derive_key(password.as_bytes(), salt, iterations);
    let unbound_key = UnboundKey::new(&AES_256_GCM, &key).unwrap();
    let less_safe_key = LessSafeKey::new(unbound_key);

    let nonce = Nonce::assume_unique_for_key([0u8; 12]); // Use the same nonce as in encryption
    let mut in_out = encrypted_message.to_vec();
    less_safe_key.open_in_place(nonce, Aad::empty(), &mut in_out).unwrap();

    str::from_utf8(&in_out).unwrap().to_string()
}

fn main() {
    let token = encrypt("nice123", "password123", 100_000);
    println!("{}", token);
    let decrypted_message = decrypt(&token, "password123");
    println!("{}", decrypted_message);
}