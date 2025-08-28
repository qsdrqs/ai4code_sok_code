use aes::Aes256; // To use AES-128, import `aes::Aes128`. For AES-192, import `aes::Aes192`.
use base64::{engine::general_purpose::STANDARD as base64_engine, Engine as _};
use cbc::{Decryptor, Encryptor};
use cipher::{
    block_padding::Pkcs7, BlockDecryptMut, BlockEncryptMut, KeyIvInit,
};
use rand::{RngCore, rngs::OsRng};
use thiserror::Error;

// Define a custom error type for cleaner error handling.
#[derive(Debug, Error)]
pub enum CryptoError {
    #[error("Invalid key length: {0}")]
    InvalidKeyLength(#[from] cipher::InvalidKeyLength),
    #[error("Cipher operation failed: {0}")]
    CipherError(#[from] cipher::block_padding::UnpadError),
    #[error("Base64 decoding error: {0}")]
    Base64Decode(#[from] base64::DecodeError),
    #[error("Invalid ciphertext: must be at least 16 bytes for the IV")]
    InvalidCiphertextLength,
}

// Define type aliases for the specific AES-256-CBC cipher modes.
// This makes the code cleaner and easier to change if you want a different AES variant.
type Aes256CbcEnc = Encryptor<Aes256>;
type Aes256CbcDec = Decryptor<Aes256>;

/// Encrypts plaintext using AES-256-CBC, prepends the IV, and Base64 encodes the result.
///
/// # Arguments
/// * `key` - A 32-byte (256-bit) key.
/// * `plaintext` - The data to encrypt.
///
/// # Returns
/// A Base64 encoded string of `[IV || CIPHERTEXT]`.
pub fn encrypt(key: &[u8], plaintext: &[u8]) -> Result<String, CryptoError> {
    // 1. Generate a random 16-byte IV.
    // os.urandom(16) is equivalent to using a cryptographically secure RNG.
    let mut iv = [0u8; 16];
    OsRng.fill_bytes(&mut iv);

    // 2. Create the AES-CBC cipher instance.
    // The `KeyIvInit` trait provides the `new` method.
    let cipher = Aes256CbcEnc::new_from_slices(key, &iv)?;

    // 3. Pad the plaintext using PKCS#7 and encrypt it.
    // The `encrypt_padded_vec_mut` method handles both padding and encryption.
    let ciphertext = cipher.encrypt_padded_vec_mut::<Pkcs7>(plaintext);

    // 4. Prepend the IV to the ciphertext.
    let mut result = iv.to_vec();
    result.extend_from_slice(&ciphertext);

    // 5. Base64 encode the (IV + ciphertext)
    Ok(base64_engine.encode(&result))
}

/// Decrypts a Base64 encoded ciphertext using AES-256-CBC.
///
/// # Arguments
/// * `key` - The 32-byte (256-bit) key used for encryption.
/// * `b64_ciphertext` - The Base64 encoded string from the `encrypt` function.
///
/// # Returns
/// The original plaintext as a vector of bytes.
pub fn decrypt(key: &[u8], b64_ciphertext: &str) -> Result<Vec<u8>, CryptoError> {
    // 1. Base64 decode the ciphertext.
    let decoded_data = base64_engine.decode(b64_ciphertext)?;

    // 2. Extract the IV (first 16 bytes).
    if decoded_data.len() < 16 {
        return Err(CryptoError::InvalidCiphertextLength);
    }
    let (iv, ciphertext) = decoded_data.split_at(16);

    // 3. Create the AES-CBC cipher instance for decryption.
    let cipher = Aes256CbcDec::new_from_slices(key, iv)?;

    // 4. Decrypt the data and unpad it using PKCS#7.
    // The `decrypt_padded_vec_mut` method handles both decryption and unpadding.
    // It will return an `UnpadError` if the padding is invalid.
    let plaintext = cipher.decrypt_padded_vec_mut::<Pkcs7>(ciphertext)?;

    Ok(plaintext)
}

fn main() {
    // --- DEMONSTRATION ---

    // A 32-byte (256-bit) key. In a real application, this should be
    // securely generated and managed (e.g., from a config file or secrets manager).
    let key = b"this-is-a-very-secret-key-32-b";
    let plaintext = b"Hello, Rust cryptography!";

    println!("Original Plaintext: {}", String::from_utf8_lossy(plaintext));
    println!("Key ({} bytes): {:?}", key.len(), key);

    // Encrypt the data
    let encrypted_b64 = match encrypt(key, plaintext) {
        Ok(data) => data,
        Err(e) => {
            eprintln!("Encryption failed: {}", e);
            return;
        }
    };
    println!("\nEncrypted (Base64): {}", encrypted_b64);

    // Decrypt the data
    let decrypted_bytes = match decrypt(key, &encrypted_b64) {
        Ok(data) => data,
        Err(e) => {
            eprintln!("Decryption failed: {}", e);
            return;
        }
    };
    println!(
        "Decrypted Plaintext: {}",
        String::from_utf8_lossy(&decrypted_bytes)
    );

    // Verify that the decrypted data matches the original plaintext
    assert_eq!(plaintext, decrypted_bytes.as_slice());
    println!("\n✅ Success: Decrypted data matches original plaintext!");
}