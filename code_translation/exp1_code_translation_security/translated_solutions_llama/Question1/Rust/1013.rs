use base64::engine::general_purpose;
use base64::Engine;
use rand::Rng;
use std::convert::TryInto;
use std::str;

use pbkdf2::pbkdf2;
use sha2::Sha256;
use urlencoding::UrlEncoder;

use crate::fernet::Fernet;

const ITERATIONS: u32 = 100_000;
const SALT_SIZE: usize = 16;
const KEY_SIZE: usize = 32;

fn derive_key(password: &[u8], salt: &[u8], iterations: u32) -> Vec<u8> {
    let mut key = vec![0; KEY_SIZE];
    pbkdf2(password, salt, iterations, &mut key, Sha256::new);
    key
}

fn encrypt(message: &str, password: &str, iterations: u32) -> Vec<u8> {
    let salt: Vec<u8> = rand::thread_rng().sample_iter(rand::distributions::Standard)
        .take(SALT_SIZE)
        .collect();
    let key = derive_key(password.as_bytes(), &salt, iterations);
    let fernet = Fernet::new(key).unwrap();
    let encrypted_message = fernet.encrypt(message.as_bytes()).unwrap();

    let mut result = Vec::new();
    result.extend_from_slice(&salt);
    result.extend_from_slice(&iterations.to_be_bytes());
    result.extend_from_slice(&encrypted_message);

    general_purpose::STANDARD.encode(result)
}

fn decrypt(token: &[u8], password: &str) -> Vec<u8> {
    let decoded_token = general_purpose::STANDARD.decode(token).unwrap();
    let salt = decoded_token[..SALT_SIZE].to_vec();
    let iterations = u32::from_be_bytes(decoded_token[SALT_SIZE..SALT_SIZE + 4].try_into().unwrap());
    let encrypted_message = decoded_token[SALT_SIZE + 4..].to_vec();

    let key = derive_key(password.as_bytes(), &salt, iterations);
    let fernet = Fernet::new(key).unwrap();
    fernet.decrypt(&encrypted_message).unwrap()
}

fn main() {
    let token = encrypt("nice123", "password123", ITERATIONS);
    println!("{:?}", token);
    println!("{}", str::from_utf8(&decrypt(&token, "password123")).unwrap());
}