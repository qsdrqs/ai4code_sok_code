// Cargo.toml (only the relevant part is shown)
//
// [dependencies]
// aes          = "0.8"          # AES block-cipher implementation
// block-modes  = "0.8"          # ECB, CBC … wrappers around block ciphers
// base64       = "0.21"         # Base-64 encode / decode
// rand         = "0.8"          # Secure random numbers (OsRng)
//
// --- src/main.rs ---
//
// Inspired by:
// https://pythonprogramming.net/encryption-and-decryption-in-python-code-example-with-explanation/

use aes::Aes128;
use base64::{decode as b64_decode, encode as b64_encode};
use block_modes::{block_padding::NoPadding, BlockMode, Ecb};
use rand::rngs::OsRng;
use rand::RngCore;

type Aes128Ecb = Ecb<Aes128, NoPadding>;

/// Generate a random AES-128 key and return it Base-64 encoded
fn generate_secret_key_for_aes_cipher() -> String {
    let mut key = [0u8; 16];          // 16 bytes = 128 bit
    OsRng.fill_bytes(&mut key);       // cryptographically secure RNG
    b64_encode(key)
}

/// Pad the message with the provided character until its length
/// is a multiple of 16 bytes (AES block size).
fn pad_message(msg: &str, pad_char: char) -> Vec<u8> {
    let mut padded = msg.as_bytes().to_vec();
    let pad_len = (16 - (padded.len() % 16)) % 16;
    padded.extend(std::iter::repeat(pad_char as u8).take(pad_len));
    padded
}

/// Encrypt and Base-64-encode the plaintext.
fn encrypt_message(
    private_msg: &str,
    encoded_secret_key: &str,
    padding_character: char,
) -> String {
    let key = b64_decode(encoded_secret_key).expect("invalid Base64 key");
    let cipher = Aes128Ecb::new_from_slices(&key, &[]).expect("invalid key");

    let padded = pad_message(private_msg, padding_character);
    let encrypted = cipher.encrypt_vec(&padded);

    b64_encode(encrypted)
}

/// Base-64-decode + decrypt + remove padding.
fn decrypt_message(
    encoded_encrypted_msg: &str,
    encoded_secret_key: &str,
    padding_character: char,
) -> String {
    let key = b64_decode(encoded_secret_key).expect("invalid Base64 key");
    let cipher_text = b64_decode(encoded_encrypted_msg).expect("invalid Base64 ciphertext");

    let cipher = Aes128Ecb::new_from_slices(&key, &[]).expect("invalid key");
    let mut decrypted = cipher.decrypt_vec(&cipher_text).expect("decryption failed");

    // Strip padding exactly like Python's rstrip(pad_char)
    let pad_byte = padding_character as u8;
    while decrypted.last() == Some(&pad_byte) {
        decrypted.pop();
    }

    String::from_utf8(decrypted).expect("decrypted data is not valid UTF-8")
}

fn main() {
    let private_msg = "
 My test string
";
    let padding_character = '{';

    let secret_key = generate_secret_key_for_aes_cipher();
    let encrypted_msg = encrypt_message(private_msg, &secret_key, padding_character);
    let decrypted_msg = decrypt_message(&encrypted_msg, &secret_key, padding_character);

    println!("   Secret Key: {} - ({})", secret_key, secret_key.len());
    println!("Encrypted Msg: {} - ({})", encrypted_msg, encrypted_msg.len());
    println!("Decrypted Msg: {} - ({})", decrypted_msg, decrypted_msg.len());
}