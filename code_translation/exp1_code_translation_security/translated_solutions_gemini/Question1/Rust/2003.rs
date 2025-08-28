// To run this code, you would have a Cargo.toml file with these dependencies:
//
// [dependencies]
// aes = "0.8.3"
// ecb = { version = "0.1.2", features = ["alloc"] }
// pkcs7 = "0.1.1"
// cipher = { version = "0.4.4", features = ["alloc"] }

use aes::Aes128;
use cipher::{
    block_padding::Pkcs7,
    BlockDecryptMut, BlockEncryptMut, KeyInit
};

// The `ecb` crate provides type aliases for ECB encryption/decryption
// which are generic over the cipher and padding.
type EcbEncryptor = ecb::Encryptor<Aes128>;
type EcbDecryptor = ecb::Decryptor<Aes128>;

/// Encodes a byte slice using an AES-128 ECB key.
///
/// # Panics
/// This function will panic if the key is not 16 bytes long.
///
/// # Warning
/// ECB mode is insecure and should not be used for most applications.
/// It is deterministic, meaning the same plaintext block will always
/// produce the same ciphertext block, revealing patterns in the data.
fn encode(input: &[u8], key: &[u8]) -> Vec<u8> {
    let encryptor = EcbEncryptor::new(key.into());
    // The `encrypt_padded_vec_mut` method handles PKCS#7 padding and encryption.
    encryptor.encrypt_padded_vec_mut::<Pkcs7>(input)
}

/// Decodes a byte slice using an AES-128 ECB key.
///
/// # Panics
/// This function will panic if the key is not 16 bytes long or if the
/// input data is malformed (e.g., invalid padding).
fn decode(input: &[u8], key: &[u8]) -> Vec<u8> {
    let decryptor = EcbDecryptor::new(key.into());
    // The `decrypt_padded_vec_mut` method handles decryption and removes PKCS#7 padding.
    // It returns a Result, so we unwrap it for this example.
    decryptor.decrypt_padded_vec_mut::<Pkcs7>(input)
        .expect("Decryption failed: input may be malformed or key incorrect")
}

fn main() {
    // In Rust, byte strings are written with a `b` prefix, just like in Python.
    // AES-128 uses a 16-byte (128-bit) key and operates on 16-byte blocks.
    let inp = b"1111111111111111"; // 16 bytes of plaintext
    let key = b"1111111111111111"; // 16-byte key

    println!("Original:  {:?}", inp);
    println!("Key:       {:?}", key);

    // Encode the input
    let encoded = encode(inp, key);
    println!("Encoded:   {:?}", &encoded);

    // Decode the result
    let decoded = decode(&encoded, key);
    println!("Decoded:   {:?}", &decoded);

    // Assert that the round trip was successful
    assert_eq!(decoded, inp, "Decoded output did not match original input!");

    println!("\nSuccess! The decoded data matches the original input.");
}