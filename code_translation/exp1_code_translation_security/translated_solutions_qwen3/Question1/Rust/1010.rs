use aes::Aes128;
use eax::{Eax, Tag};
use rand::RngCore;
use rand::rngs::OsRng;

// Use the AEAD interface for EAX
use eax::aead::{Aead, KeyInit};

// AES-128 in EAX mode
type AesEax = Eax<Aes128>;

// Tag size for EAX mode (16 bytes)
const TAG_SIZE: usize = 16;

/// Generate a 16-byte symmetric key
pub fn generate_key() -> [u8; 16] {
    let mut key = [0u8; 16];
    OsRng.fill_bytes(&mut key);
    key
}

/// Encrypt a string using AES in EAX mode
pub fn encrypt(key: &[u8; 16], data: &str) -> (Vec<u8>, Vec<u8>) {
    let cipher = AesEax::new_from_slice(key).expect("Invalid key length");

    // Generate a 16-byte nonce
    let mut nonce = vec![0u8; 16];
    OsRng.fill_bytes(&mut nonce);

    // Encrypt the data
    let mut ciphertext = data.as_bytes().to_vec();
    let tag = cipher
        .encrypt_in_place_detached(&nonce, b"", &mut ciphertext)
        .expect("Encryption failed");

    // Append the tag to the end of the ciphertext
    ciphertext.extend_from_slice(tag.as_ref());

    (ciphertext, nonce)
}

/// Decrypt a string using AES in EAX mode
pub fn decrypt(key: &[u8; 16], nonce: &[u8], ciphertext: &[u8]) -> Vec<u8> {
    let cipher = AesEax::new_from_slice(key).expect("Invalid key length");

    // Split the ciphertext and the tag
    if ciphertext.len() < TAG_SIZE {
        panic!("Ciphertext too short to contain a tag");
    }

    let (data, tag_bytes) = ciphertext.split_at(ciphertext.len() - TAG_SIZE);
    let tag = Tag::clone_from_slice(tag_bytes);

    // Decrypt and verify
    let mut buffer = data.to_vec();
    cipher
        .decrypt_in_place_detached(nonce, b"", &mut buffer, &tag)
        .expect("Decryption failed");

    buffer
}

fn main() {
    let key = generate_key();
    let test_data = "Jim's test";

    let (encrypted, nonce) = encrypt(&key, test_data);
    let restored_data = decrypt(&key, &nonce, &encrypted);

    println!("Decrypted data: {:?}", String::from_utf8_lossy(&restored_data));
}