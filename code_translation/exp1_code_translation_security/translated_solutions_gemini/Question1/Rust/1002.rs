// main.rs

// Import necessary components from the openssl crate.
// `symm` is for symmetric ciphers, `ErrorStack` is for handling errors.
use openssl::symm::{Cipher, Crypter, Mode};
use openssl::error::ErrorStack;

// Import the `RngCore` trait from the `rand` crate to generate random bytes.
use rand::RngCore;

/// Encrypts data using AES-256-CBC.
///
/// This function is a more direct, low-level translation of the Python code,
/// showing the creation of a `Crypter` and the `update`/`finalize` steps.
///
/// # Arguments
/// * `data` - The plaintext data to encrypt.
/// * `key` - A 32-byte key for AES-256.
/// * `iv` - A 16-byte initialization vector.
///
/// # Returns
/// A `Result` containing the ciphertext as a `Vec<u8>` or an `ErrorStack` on failure.
fn encrypt(data: &[u8], key: &[u8], iv: &[u8]) -> Result<Vec<u8>, ErrorStack> {
    // In Rust, we explicitly choose the cipher. `aes_256_cbc()` corresponds to
    // Python's `algorithms.AES(key)` and `modes.CBC(iv)`.
    let cipher = Cipher::aes_256_cbc();

    // Create an encryptor (Crypter) with the specified cipher, mode, key, and IV.
    let mut encryptor = Crypter::new(cipher, Mode::Encrypt, key, Some(iv))?;

    // The `cryptography` library in Python requires padding for CBC mode, and so does OpenSSL.
    // We enable it here. This is on by default for high-level functions but good to be explicit here.
    encryptor.pad(true);

    // Create a buffer to hold the encrypted data.
    // It needs to be slightly larger than the input data to accommodate padding and the final block.
    let mut ciphertext = vec![0; data.len() + cipher.block_size()];

    // Perform the encryption. `update` encrypts the main part of the data.
    let count = encryptor.update(data, &mut ciphertext)?;

    // `finalize` encrypts the final block and adds padding.
    let rest = encryptor.finalize(&mut ciphertext[count..])?;

    // The total length of the ciphertext is the sum of bytes from update and finalize.
    ciphertext.truncate(count + rest);

    Ok(ciphertext)
}

/// Decrypts data using AES-256-CBC.
///
/// This function mirrors the structure of the `encrypt` function.
///
/// # Arguments
/// * `data` - The ciphertext to decrypt.
/// * `key` - The 32-byte key used for encryption.
/// * `iv` - The 16-byte initialization vector used for encryption.
///
/// # Returns
/// A `Result` containing the original plaintext as a `Vec<u8>` or an `ErrorStack` on failure.
fn decrypt(data: &[u8], key: &[u8], iv: &[u8]) -> Result<Vec<u8>, ErrorStack> {
    let cipher = Cipher::aes_256_cbc();

    // Create a decryptor. Note the `Mode::Decrypt`.
    let mut decryptor = Crypter::new(cipher, Mode::Decrypt, key, Some(iv))?;
    decryptor.pad(true);

    // Create a buffer for the plaintext. It will be at most the size of the ciphertext.
    let mut plaintext = vec![0; data.len() + cipher.block_size()];

    // Perform the decryption.
    let count = decryptor.update(data, &mut plaintext)?;

    // `finalize` removes padding and processes the last block.
    let rest = decryptor.finalize(&mut plaintext[count..])?;

    // The total length of the plaintext.
    plaintext.truncate(count + rest);

    Ok(plaintext)
}

/// A test function to verify that encryption and decryption work correctly.
fn test() {
    // The original message, represented as a byte slice.
    let data = b"a secret message";

    // Generate a random 32-byte (256-bit) key.
    // We create a mutable array and fill it with random bytes.
    let mut key = [0u8; 32];
    rand::thread_rng().fill_bytes(&mut key);

    // Generate a random 16-byte (128-bit) IV.
    let mut iv = [0u8; 16];
    rand::thread_rng().fill_bytes(&mut iv);

    println!("Original data: {:?}", String::from_utf8_lossy(data));
    println!("Key: {:?}", key);
    println!("IV:  {:?}", iv);

    // Encrypt the data. `.expect()` will panic if encryption fails.
    let encrypted_data = encrypt(data, &key, &iv).expect("Encryption failed");
    println!("Encrypted (hex): {}", hex::encode(&encrypted_data));

    // Decrypt the data.
    let decrypted_data = decrypt(&encrypted_data, &key, &iv).expect("Decryption failed");
    println!("Decrypted data: {:?}", String::from_utf8_lossy(&decrypted_data));

    // Assert that the decrypted data matches the original data.
    // `assert_eq!` is the standard way to check for equality in tests.
    assert_eq!(data, decrypted_data.as_slice());

    println!("\n✅ Test passed: Decrypted data matches original data!");
}

fn main() {
    // To run the test, we simply call the test function.
    test();
}

// Note: To print the hex representation of the encrypted data, you would also need
// the `hex` crate. Add `hex = "0.4"` to your Cargo.toml.
// This part is added for better demonstration.