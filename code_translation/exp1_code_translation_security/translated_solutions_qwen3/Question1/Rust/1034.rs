use aes::Aes256;
use block_modes::block_padding::Pkcs7;
use block_modes::{BlockMode, Cbc};
use sha2::{Digest, Sha256};
use rand::{Rng, rngs::OsRng};
use base64::{encode, decode};

// Define the AES-256 CBC mode with PKCS7 padding
type Aes256Cbc = Cbc<Aes256, Pkcs7>;

/// Encrypts a plaintext string using AES-256-CBC with a given key.
///
/// # Arguments
/// - `plain_text`: The string to encrypt.
/// - `key`: The encryption key (as a string).
///
/// # Returns
/// A Base64-encoded string containing the IV and encrypted data.
fn encode_str(plain_text: &str, key: &str) -> String {
    // Step 1: Derive a 32-byte key using SHA-256
    let mut hasher = Sha256::new();
    hasher.update(key.as_bytes());
    let key_bytes = hasher.finalize().to_vec();

    // Step 2: Generate a random 16-byte IV
    let mut iv = [0u8; 16];
    OsRng.fill(&mut iv);

    // Step 3: Create the AES-256-CBC cipher
    let cipher = Aes256Cbc::new_from_slices(&key_bytes, &iv)
        .expect("Invalid key or IV length");

    // Step 4: Encrypt the plaintext with padding
    let ciphertext = cipher.encrypt_vec(plain_text.as_bytes());

    // Step 5: Prepend the IV to the ciphertext
    let mut encrypted_data = Vec::with_capacity(iv.len() + ciphertext.len());
    encrypted_data.extend_from_slice(&iv);
    encrypted_data.extend_from_slice(&ciphertext);

    // Step 6: Base64 encode the result
    encode(&encrypted_data)
}

/// Decrypts a Base64-encoded string using AES-256-CBC with a given key.
///
/// # Arguments
/// - `encoded_data`: The Base64-encoded string to decrypt.
/// - `key`: The decryption key (as a string).
///
/// # Returns
/// An `Option<String>` containing the decrypted plaintext if successful, or `None` on failure.
fn decode_str(encoded_data: &str, key: &str) -> Option<String> {
    // Step 1: Base64 decode the input
    let encrypted_data = decode(encoded_data).ok()?;

    // Step 2: Ensure the data is at least 16 bytes (IV size)
    if encrypted_data.len() < 16 {
        return None;
    }

    // Step 3: Extract the IV and ciphertext
    let iv = &encrypted_data[..16];
    let ciphertext = &encrypted_data[16..];

    // Step 4: Derive the key using SHA-256
    let mut hasher = Sha256::new();
    hasher.update(key.as_bytes());
    let key_bytes = hasher.finalize().to_vec();

    // Step 5: Create the AES-256-CBC cipher
    let cipher = Aes256Cbc::new_from_slices(&key_bytes, iv)
        .expect("Invalid key or IV length");

    // Step 6: Decrypt the ciphertext
    let decrypted_padded = cipher.decrypt_vec(ciphertext).ok()?;

    // Step 7: Convert the decrypted bytes to a UTF-8 string
    String::from_utf8(decrypted_padded).ok()
}

fn main() {
    // Example usage
    let encoded = encode_str("I am okay", "wow");
    match decode_str(&encoded, "wow") {
        Some(s) => println!("{}", s),  // Should print: "I am okay"
        None => println!("Decryption failed"),
    }
}