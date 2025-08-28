//!
//! Functions that one encrypts and the other decrypts a given string using a given symmetric key
//!

use aes::Aes256;
use base64::{engine::general_purpose::STANDARD, Engine as _};
use block_modes::block_padding::Padding; // Generic padding trait, though we implement our own
use block_modes::{BlockMode, Cbc};
use rand::{rngs::OsRng, RngCore};
use sha2::{Digest, Sha256};
use thiserror::Error;

// Define a specific cipher type for convenience.
// AES-256 in CBC mode.
type Aes256Cbc = Cbc<Aes256, Pkcs7CustomPadding>;

// --- Custom Error Type for better error handling ---
#[derive(Debug, Error)]
pub enum CipherError {
    #[error("Base64 decoding error: {0}")]
    Base64(#[from] base64::DecodeError),
    #[error("Invalid ciphertext: length is too short")]
    InvalidLength,
    #[error("Decryption error: {0}")]
    Decryption(#[from] block_modes::Error),
    #[error("Invalid padding: The decrypted data has invalid padding bytes.")]
    InvalidPadding,
    #[error("Decoded data is not valid UTF-8: {0}")]
    Utf8(#[from] std::str::Utf8Error),
}

// --- Custom Padding Implementation ---
// The Python code uses a non-standard padding block size of 32, while AES's block size is 16.
// We must replicate this specific behavior.
struct Pkcs7CustomPadding;

impl Padding for Pkcs7CustomPadding {
    // The block size for padding, as defined in the Python code (`self.bs = 32`).
    const BLOCK_SIZE: usize = 32;

    fn pad(buf: &mut Vec<u8>, _pos: usize) -> Result<(), block_modes::PadError> {
        let pad_len = Self::BLOCK_SIZE - (buf.len() % Self::BLOCK_SIZE);
        let pad_byte = pad_len as u8;
        buf.resize(buf.len() + pad_len, pad_byte);
        Ok(())
    }

    fn unpad(buf: &[u8]) -> Result<&[u8], block_modes::UnpadError> {
        if buf.is_empty() {
            return Err(block_modes::UnpadError);
        }
        let last_byte = buf[buf.len() - 1];
        let pad_len = last_byte as usize;

        if pad_len == 0 || pad_len > buf.len() || pad_len > Self::BLOCK_SIZE {
            return Err(block_modes::UnpadError);
        }

        let (unpadded_data, padding) = buf.split_at(buf.len() - pad_len);
        if padding.iter().any(|&b| b != last_byte) {
            return Err(block_modes::UnpadError);
        }
        Ok(unpadded_data)
    }
}

// --- The AESCipher Struct and its Implementation ---
pub struct AESCipher {
    key: [u8; 32], // SHA-256 produces a 32-byte hash
}

impl AESCipher {
    /// Creates a new AESCipher instance.
    /// The key is derived from the input string using SHA-256, matching the Python implementation.
    pub fn new(key: &str) -> Self {
        let mut hasher = Sha256::new();
        hasher.update(key.as_bytes());
        let key_hash = hasher.finalize();
        AESCipher {
            key: key_hash.into(),
        }
    }

    /// Encrypts a raw string.
    /// The process is: Pad -> Encrypt -> Prepend IV -> Base64 Encode.
    pub fn encrypt(&self, raw: &str) -> Result<String, CipherError> {
        // AES block size is 16 bytes (128 bits)
        const AES_BLOCK_SIZE: usize = 16;
        let mut iv = [0u8; AES_BLOCK_SIZE];
        OsRng.fill_bytes(&mut iv);

        // The cipher is created with the derived key and the random IV.
        let cipher = Aes256Cbc::new_from_slices(&self.key, &iv).unwrap();

        // The Python code pads the data *before* encryption. We do the same.
        let mut buffer = raw.as_bytes().to_vec();
        Pkcs7CustomPadding::pad(&mut buffer, raw.len())
            .expect("Padding should not fail with Vec");

        // Encrypt the padded data.
        let ciphertext = cipher.encrypt_vec(&buffer);

        // Prepend the IV to the ciphertext, as done in the Python code.
        let mut result_vec = Vec::with_capacity(iv.len() + ciphertext.len());
        result_vec.extend_from_slice(&iv);
        result_vec.extend_from_slice(&ciphertext);

        // Return the Base64 encoded string.
        Ok(STANDARD.encode(&result_vec))
    }

    /// Decrypts a Base64 encoded string.
    /// The process is: Base64 Decode -> Split IV and Ciphertext -> Decrypt -> Unpad.
    pub fn decrypt(&self, enc: &str) -> Result<String, CipherError> {
        const AES_BLOCK_SIZE: usize = 16;

        // 1. Base64 decode the input string.
        let enc_data = STANDARD.decode(enc)?;

        // 2. Ensure the data is long enough to contain an IV.
        if enc_data.len() < AES_BLOCK_SIZE {
            return Err(CipherError::InvalidLength);
        }

        // 3. Split the IV from the ciphertext.
        let (iv, ciphertext) = enc_data.split_at(AES_BLOCK_SIZE);

        // 4. Create the cipher and decrypt.
        let cipher = Aes256Cbc::new_from_slices(&self.key, iv).unwrap();
        let decrypted_padded_bytes = cipher.decrypt_vec(ciphertext)?;

        // 5. Unpad the decrypted bytes using our custom logic.
        let unpadded_bytes =
            Pkcs7CustomPadding::unpad(&decrypted_padded_bytes).map_err(|_| CipherError::InvalidPadding)?;

        // 6. Decode the final bytes as a UTF-8 string.
        let decrypted_string = std::str::from_utf8(unpadded_bytes)?.to_string();

        Ok(decrypted_string)
    }
}

// --- Main function to demonstrate usage ---
fn main() {
    let key = "mysecretpassword";
    let cipher = AESCipher::new(key);
    let plaintext = "Secret Message A";

    println!("Plaintext: '{}'", plaintext);

    let encrypted = cipher.encrypt(plaintext).expect("Encryption failed");
    println!("Encrypted (Base64): {}", encrypted);

    let decrypted = cipher.decrypt(&encrypted).expect("Decryption failed");
    println!("Decrypted: '{}'", decrypted);

    assert_eq!(plaintext, decrypted);
    println!("\nSuccess! Original and decrypted messages match.");
}