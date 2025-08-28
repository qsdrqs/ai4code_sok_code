// This Rust code is adapted from the Python pycryptodome example:
// https://pycryptodome.readthedocs.io/en/latest/src/examples.html

use aead::{Aead, Key, NewAead, Nonce};
use aes::Aes128; // Using AES with a 128-bit (16-byte) key
use eax::Eax;
use rand::rngs::OsRng;
use rand::RngCore;

/// Encrypts a string message using AES-128-EAX.
///
/// # Arguments
/// * `message` - The string slice to encrypt.
/// * `key` - A 16-byte key.
/// * `nonce` - A 16-byte nonce (must be unique for each message encrypted with the same key).
///
/// # Returns
/// A `Vec<u8>` containing the ciphertext and authentication tag, concatenated.
fn encode_string(message: &str, key: &[u8], nonce: &[u8]) -> Vec<u8> {
    // Create a new AES-128-EAX cipher instance.
    // The `Key` and `Nonce` types from the `aead` crate provide type safety.
    let key = Key::<Aes128>::from_slice(key);
    let cipher = Eax::<Aes128>::new(key);
    let nonce = Nonce::from_slice(nonce);

    // The `encrypt` method handles padding, encryption, and tag generation.
    // It returns a `Result`, so we use `expect` for simple error handling.
    let ciphertext = cipher
        .encrypt(nonce, message.as_bytes())
        .expect("encryption failed!");

    ciphertext
}

/// Decrypts a ciphertext using AES-128-EAX and verifies its authenticity.
///
/// # Arguments
/// * `ciphertext` - The encrypted data, including the authentication tag.
/// * `key` - The 16-byte key used for encryption.
/// * `nonce` - The 16-byte nonce used for encryption.
///
/// # Returns
/// The original decrypted `String`. Panics if decryption or verification fails.
fn decode_string(ciphertext: &[u8], key: &[u8], nonce: &[u8]) -> String {
    // Create a new AES-128-EAX cipher instance with the same key.
    let key = Key::<Aes128>::from_slice(key);
    let cipher = Eax::<Aes128>::new(key);
    let nonce = Nonce::from_slice(nonce);

    // The `decrypt` method automatically verifies the authentication tag.
    // If the tag is invalid or the data is corrupt, it will return an error.
    let decrypted_bytes = cipher
        .decrypt(nonce, ciphertext)
        .expect("decryption failed! (check key, nonce, or data integrity)");

    // Convert the decrypted bytes back to a UTF-8 string.
    String::from_utf8(decrypted_bytes)
        .expect("failed to convert decrypted bytes to a valid UTF-8 string")
}

fn main() {
    let original_message = "secret data";

    // Generate a random 16-byte (128-bit) key.
    // OsRng is a cryptographically secure random number generator.
    let mut key = [0u8; 16];
    OsRng.fill_bytes(&mut key);

    // Generate a random 16-byte nonce.
    // In a real application, you must ensure the nonce is never reused with the same key.
    let mut nonce = [0u8; 16];
    OsRng.fill_bytes(&mut nonce);

    println!("Original Message: {}", original_message);

    // Encrypt the message
    let ciphertext = encode_string(original_message, &key, &nonce);
    // In a real application, you would transmit or store `ciphertext`, `nonce`.
    // The key must be kept secret.

    // Decrypt the message
    let decoded_string = decode_string(&ciphertext, &key, &nonce);

    println!("Decoded Message:  {}", decoded_string);

    // Verify that the messages match
    assert_eq!(original_message, decoded_string);
    println!("\nSuccessfully encrypted and decrypted the message!");
}