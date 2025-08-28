use aes_gcm::{Aes256Gcm, Key, Nonce};
use rand::Rng;

// Function to encode a string
fn encode_string(message: &str, key: &[u8], nonce: &[u8]) -> (Vec<u8>, Vec<u8>) {
    let key = Key::from_slice(key);
    let nonce = Nonce::from_slice(nonce);
    let cipher = Aes256Gcm::new(key);

    let message_bytes = message.as_bytes();
    let ciphertext = cipher.encrypt(nonce, message_bytes).unwrap();
    let tag = cipher.finalize(nonce).unwrap();

    (ciphertext, tag.to_vec())
}

// Function to decode a string
fn decode_string(ciphertext: &[u8], tag: &[u8], key: &[u8], nonce: &[u8]) -> String {
    let key = Key::from_slice(key);
    let nonce = Nonce::from_slice(nonce);
    let cipher = Aes256Gcm::new(key);

    let plaintext = cipher.decrypt(nonce, ciphertext, &tag).unwrap();
    String::from_utf8(plaintext).unwrap()
}

fn main() {
    let original_message = "secret data";
    let mut rng = rand::thread_rng();
    let nonce: Vec<u8> = rng.gen();
    let key: Vec<u8> = rng.gen();

    println!("{}", original_message);
    let (ciphertext, tag) = encode_string(original_message, &key, &nonce);
    let decoded_string = decode_string(&ciphertext, &tag, &key, &nonce);
    println!("{}", decoded_string);
}