use aes::Eax;
use aes::cipher::{AeadCore, AeadInPlace, KeyInit};
use rand::prelude::*;

fn encode_string(message: &str, key: &[u8; 16], nonce: &[u8; 16]) -> (Vec<u8>, Vec<u8>) {
    // Initialize the EAX cipher with the given key and nonce
    let cipher = Eax::new(key, nonce);

    // Convert the message to bytes and prepare a mutable buffer
    let mut buffer = message.as_bytes().to_vec();

    // Encrypt the buffer in-place and retrieve the authentication tag
    let tag = cipher
        .encrypt_in_place_detached(&mut buffer)
        .expect("Encryption failed");

    // Return the encrypted buffer and the tag as a Vec<u8>
    (buffer, tag.to_vec())
}

fn decode_string(ciphertext: Vec<u8>, tag: Vec<u8>, key: &[u8; 16], nonce: &[u8; 16]) -> String {
    // Initialize the EAX cipher with the same key and nonce
    let cipher = Eax::new(key, nonce);

    // Convert the tag from Vec<u8> to the appropriate Tag type
    let tag = <Eax as AeadCore>::Tag::from_slice(&tag);

    // Prepare a mutable buffer with the ciphertext
    let mut buffer = ciphertext;

    // Decrypt and verify the buffer in-place using the tag
    cipher
        .decrypt_in_place_detached(&mut buffer, tag)
        .expect("Decryption or authentication failed");

    // Convert the decrypted bytes back to a UTF-8 string
    String::from_utf8(buffer).expect("Invalid UTF-8 in decrypted data")
}

fn main() {
    // Original message to be encrypted
    let original_message = "secret data";
    println!("Original message: {}", original_message);

    // Generate a 16-byte key and a 16-byte nonce using a secure random number generator
    let key: [u8; 16] = thread_rng().gen();
    let nonce: [u8; 16] = thread_rng().gen();

    // Encrypt the message
    let (ciphertext, tag) = encode_string(original_message, &key, &nonce);

    // Decrypt the message
    let decoded_message = decode_string(ciphertext, tag, &key, &nonce);

    // Print the decrypted message
    println!("Decrypted message: {}", decoded_message);
}