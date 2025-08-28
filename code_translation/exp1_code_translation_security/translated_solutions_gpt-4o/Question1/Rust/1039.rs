extern crate base64;
extern crate rust_crypto;

use rust_crypto::aessafe::{AesSafe256Encryptor, AesSafe256Decryptor};
use rust_crypto::blockmodes::{CbcEncryptor, CbcDecryptor, NoPadding};
use rust_crypto::buffer::{RefReadBuffer, RefWriteBuffer, BufferResult};
use base64::{encode, decode};

fn encrypt(message: &str, key: &[u8]) -> Result<String, &'static str> {
    let iv = [0u8; 16]; // Initialization vector
    let encryptor = CbcEncryptor::new(
        AesSafe256Encryptor::new(key),
        NoPadding,
        iv.to_vec(),
    );

    let mut read_buffer = RefReadBuffer::new(message.as_bytes());
    let mut buffer = [0; 4096];
    let mut write_buffer = RefWriteBuffer::new(&mut buffer);

    match encryptor.encrypt(&mut read_buffer, &mut write_buffer, true) {
        Ok(BufferResult::BufferUnderflow) | Ok(BufferResult::BufferOverflow) => {
            let encrypted_data = write_buffer.take_read_buffer().take_remaining().to_vec();
            Ok(encode(&encrypted_data))
        }
        Err(_) => Err("Encryption failed"),
    }
}

fn decrypt(message: &str, key: &[u8]) -> Result<String, &'static str> {
    let iv = [0u8; 16]; // Initialization vector
    let decryptor = CbcDecryptor::new(
        AesSafe256Decryptor::new(key),
        NoPadding,
        iv.to_vec(),
    );

    let encrypted_data = decode(message).map_err(|_| "Decoding failed")?;
    let mut read_buffer = RefReadBuffer::new(&encrypted_data);
    let mut buffer = [0; 4096];
    let mut write_buffer = RefWriteBuffer::new(&mut buffer);

    match decryptor.decrypt(&mut read_buffer, &mut write_buffer, true) {
        Ok(BufferResult::BufferUnderflow) | Ok(BufferResult::BufferOverflow) => {
            let decrypted_data = write_buffer.take_read_buffer().take_remaining().to_vec();
            String::from_utf8(decrypted_data).map_err(|_| "Decryption failed")
        }
        Err(_) => Err("Decryption failed"),
    }
}

fn main() {
    let key = b"an example very very secret key."; // 32 bytes for AES-256
    let message = "Hello, world!";

    match encrypt(message, key) {
        Ok(encrypted_message) => {
            println!("Encrypted: {}", encrypted_message);

            match decrypt(&encrypted_message, key) {
                Ok(decrypted_message) => println!("Decrypted: {}", decrypted_message),
                Err(e) => println!("Decryption error: {}", e),
            }
        }
        Err(e) => println!("Encryption error: {}", e),
    }
}