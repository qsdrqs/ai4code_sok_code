use base64::{decode_config, encode_config, URL_SAFE_NO_PAD};
use fernet::Fernet;
use pbkdf2::pbkdf2_hmac;
use rand::rngs::OsRng;
use rand::RngCore;
use sha2::Sha256;
use std::convert::TryInto;
use std::error::Error;
use std::fmt;

// Custom error type for more descriptive failures in decryption.
#[derive(Debug)]
enum DecryptError {
    Base64(base64::DecodeError),
    Format(&'static str),
    Fernet(fernet::DecryptionError),
}

impl fmt::Display for DecryptError {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        match self {
            DecryptError::Base64(e) => write!(f, "Base64 decoding error: {}", e),
            DecryptError::Format(msg) => write!(f, "Invalid token format: {}", msg),
            DecryptError::Fernet(e) => write!(f, "Fernet decryption error: {}", e),
        }
    }
}

impl Error for DecryptError {}

impl From<base64::DecodeError> for DecryptError {
    fn from(err: base64::DecodeError) -> DecryptError {
        DecryptError::Base64(err)
    }
}

impl From<fernet::DecryptionError> for DecryptError {
    fn from(err: fernet::DecryptionError) -> DecryptError {
        DecryptError::Fernet(err)
    }
}

/// Derive a secret key from a given password and salt.
/// The key is returned as a URL-safe base64 encoded string, just like in the Python example.
fn derive_key(password: &[u8], salt: &[u8], iterations: u32) -> String {
    let mut key = [0u8; 32];
    pbkdf2_hmac::<Sha256>(password, salt, iterations, &mut key);
    encode_config(key, URL_SAFE_NO_PAD)
}

/// Encrypts a message with a password.
fn encrypt(message: &str, password: &str, iterations: u32) -> Result<String, Box<dyn Error>> {
    // 1. Generate a random 16-byte salt.
    let mut salt = [0u8; 16];
    OsRng.fill_bytes(&mut salt);

    // 2. Derive the encryption key.
    let key_b64 = derive_key(password.as_bytes(), &salt, iterations);

    // 3. Encrypt the message using Fernet.
    let fernet = Fernet::new(&key_b64)?;
    let encrypted_message_b64 = fernet.encrypt(message.as_bytes());

    // 4. The Python code decodes the fernet output before concatenation. We must do the same.
    let encrypted_message_raw = decode_config(&encrypted_message_b64, URL_SAFE_NO_PAD)?;

    // 5. Concatenate salt, iterations, and raw ciphertext.
    let mut combined: Vec<u8> = Vec::new();
    combined.extend_from_slice(&salt);
    combined.extend_from_slice(&iterations.to_be_bytes()); // 4 bytes, big-endian
    combined.extend_from_slice(&encrypted_message_raw);

    // 6. Base64-encode the final result.
    Ok(encode_config(&combined, URL_SAFE_NO_PAD))
}

/// Decrypts a token with a password.
fn decrypt(token: &str, password: &str) -> Result<Vec<u8>, DecryptError> {
    // 1. Base64-decode the input token.
    let decoded = decode_config(token, URL_SAFE_NO_PAD)?;

    // 2. Extract the salt, iterations, and ciphertext.
    if decoded.len() < 20 {
        return Err(DecryptError::Format("Token is too short."));
    }
    let salt = &decoded[0..16];
    let iter_bytes: [u8; 4] = decoded[16..20]
        .try_into()
        .map_err(|_| DecryptError::Format("Could not read iteration count."))?;
    let ciphertext_raw = &decoded[20..];

    let iterations = u32::from_be_bytes(iter_bytes);

    // 3. Re-derive the key using the extracted salt and iterations.
    let key_b64 = derive_key(password.as_bytes(), salt, iterations);

    // 4. The Python code re-encodes the raw ciphertext to base64 before passing to Fernet.
    // This is the inverse of the unusual step in the encrypt function.
    let token_for_fernet = encode_config(ciphertext_raw, URL_SAFE_NO_PAD);

    // 5. Decrypt the token.
    let fernet = Fernet::new(&key_b64)
        .map_err(|_| DecryptError::Format("Failed to create Fernet instance from derived key."))?;
        
    let decrypted_data = fernet.decrypt(&token_for_fernet)?;
    Ok(decrypted_data)
}

fn main() {
    let password = "password123";
    let message = "nice123";

    // Encrypt the message
    let token = encrypt(message, password, 100_000).expect("Encryption failed");
    println!("Encrypted Token: {}", token);

    // Decrypt the token
    match decrypt(&token, password) {
        Ok(decrypted_bytes) => {
            let decrypted_string = String::from_utf8(decrypted_bytes).expect("Decrypted data is not valid UTF-8");
            println!("Decrypted Message: {}", decrypted_string);
            assert_eq!(message, decrypted_string);
        }
        Err(e) => {
            eprintln!("Decryption failed: {}", e);
        }
    }
}