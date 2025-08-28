use aes_gcm::{Aes128Gcm, Key, Nonce};
use aes_gcm::aead::{Aead, NewAead};
use rand::RngCore;

fn encode_string(message: &str, key: &[u8; 16], nonce: &[u8; 12]) -> (Vec<u8>, Vec<u8>) {
    let key = Key::from_slice(key);
    let cipher = Aes128Gcm::new(key);
    let nonce = Nonce::from_slice(nonce);
    
    let ciphertext = cipher.encrypt(nonce, message.as_bytes())
        .expect("encryption failure!");
    
    // In AES-GCM, the tag is appended to the ciphertext
    // We need to split them for compatibility with the Python version
    let tag_start = ciphertext.len() - 16;
    let (cipher_part, tag_part) = ciphertext.split_at(tag_start);
    
    (cipher_part.to_vec(), tag_part.to_vec())
}

fn decode_string(ciphertext_and_tag: (Vec<u8>, Vec<u8>), key: &[u8; 16], nonce: &[u8; 12]) -> String {
    let key = Key::from_slice(key);
    let cipher = Aes128Gcm::new(key);
    let nonce = Nonce::from_slice(nonce);
    
    // Combine ciphertext and tag back together
    let mut combined = ciphertext_and_tag.0;
    combined.extend_from_slice(&ciphertext_and_tag.1);
    
    let plaintext = cipher.decrypt(nonce, combined.as_ref())
        .expect("decryption failure!");
    
    String::from_utf8(plaintext).expect("Invalid UTF-8")
}

fn get_random_bytes<const N: usize>() -> [u8; N] {
    let mut bytes = [0u8; N];
    rand::thread_rng().fill_bytes(&mut bytes);
    bytes
}

fn main() {
    let original_message = "secret data";
    let nonce = get_random_bytes::<12>(); // AES-GCM typically uses 12-byte nonces
    let key = get_random_bytes::<16>();
    
    println!("{}", original_message);
    let ciphertext = encode_string(original_message, &key, &nonce);
    let decoded_string = decode_string(ciphertext, &key, &nonce);
    println!("{}", decoded_string);
}