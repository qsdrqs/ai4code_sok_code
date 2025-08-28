use std::error::Error;
use std::fmt;

// Custom error type for encryption and decryption
#[derive(Debug)]
enum CaesarCipherError {
    InvalidKeyType,
    InvalidStringType,
    NegativeKey,
    EmptyString,
    KeyOutOfRange,
}

impl fmt::Display for CaesarCipherError {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        match self {
            CaesarCipherError::InvalidKeyType => write!(f, "Key must be an integer"),
            CaesarCipherError::InvalidStringType => write!(f, "String must be a string"),
            CaesarCipherError::NegativeKey => write!(f, "Key must be a non-negative integer"),
            CaesarCipherError::EmptyString => write!(f, "String must not be empty"),
            CaesarCipherError::KeyOutOfRange => write!(f, "Key must be between 0 and 26"),
        }
    }
}

impl Error for CaesarCipherError {}

// Function to encrypt a string using Caesar Cipher
fn encrypt(key: i32, string: &str) -> Result<String, CaesarCipherError> {
    if !key.is_integer() {
        return Err(CaesarCipherError::InvalidKeyType);
    }
    if string.is_empty() {
        return Err(CaesarCipherError::EmptyString);
    }
    if key < 0 {
        return Err(CaesarCipherError::NegativeKey);
    }
    let key = if key > 26 { key % 26 } else { key };

    let mut new_string = String::new();
    for c in string.chars() {
        if c.is_alphabetic() {
            let ascii_offset = if c.is_lowercase() { 97 } else { 65 };
            new_string.push(((c as u32 - ascii_offset as u32 + key as u32) % 26 + ascii_offset as u32) as u8 as char);
        } else {
            new_string.push(c);
        }
    }
    Ok(new_string)
}

// Function to decrypt a string using Caesar Cipher
fn decrypt(ciphertext: &str, key: i32) -> Result<String, CaesarCipherError> {
    if !key.is_integer() {
        return Err(CaesarCipherError::InvalidKeyType);
    }
    if ciphertext.is_empty() {
        return Err(CaesarCipherError::EmptyString);
    }
    if key < 0 {
        return Err(CaesarCipherError::NegativeKey);
    }
    let key = if key > 26 { key % 26 } else { key };

    let mut plaintext = String::new();
    for c in ciphertext.chars() {
        if c.is_alphabetic() {
            let ascii_offset = if c.is_lowercase() { 97 } else { 65 };
            plaintext.push(((c as u32 - ascii_offset as u32 - key as u32 + 26) % 26 + ascii_offset as u32) as u8 as char);
        } else {
            plaintext.push(c);
        }
    }
    Ok(plaintext)
}

fn main() {
    match encrypt(3, "Hello, World!") {
        Ok(encrypted) => println!("Encrypted: {}", encrypted),
        Err(e) => println!("Error: {}", e),
    }

    match decrypt("Khoor, Zruog!", 3) {
        Ok(decrypted) => println!("Decrypted: {}", decrypted),
        Err(e) => println!("Error: {}", e),
    }
}