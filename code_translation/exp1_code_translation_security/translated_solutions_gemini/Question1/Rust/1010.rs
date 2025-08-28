//! Import cryptographic crates
use aead::{Aead, NewAead, OsRng, generic_array::GenericArray};
use eax::{Eax, Aes128}; // AES-128 in EAX mode
use rand::RngCore;

/// Type alias for our AES-128 EAX cipher.
type Aes128Eax = Eax<Aes128>;
/// Type alias for the 128-bit (16-byte) key.
type Key = [u8; 16];
/// The nonce size for AES-128 EAX is 16 bytes.
const NONCE_SIZE: usize = 16;

/// Function to generate a symmetric key.
/// Returns a 16-byte (128-bit) key.
fn generate_key() -> Key {
    let mut key_bytes = [0u8; 16];
    // OsRng is a cryptographically secure random number generator
    OsRng.fill_bytes(&mut key_bytes);
    key_bytes
}

/// Function to encrypt a string using a symmetric key.
///
/// # Arguments
/// * `key` - A 16-byte key.
/// * `data` - The string data to encrypt.
///
/// # Returns
/// A Result containing a tuple of (ciphertext, nonce) or a crypto error.
/// The ciphertext includes the authentication tag.
fn encrypt(key: &Key, data: &str) -> Result<(Vec<u8>, Vec<u8>), aead::Error> {
    // Create a cipher instance from the key
    let cipher = Aes128Eax::new(GenericArray::from_slice(key));

    // Generate a random nonce. It must be unique for every encryption with the same key.
    let mut nonce_bytes = [0u8; NONCE_SIZE];
    OsRng.fill_bytes(&mut nonce_bytes);
    let nonce = GenericArray::from_slice(&nonce_bytes);

    // Encrypt the data. The `encrypt` method returns the ciphertext and authentication tag combined.
    let ciphertext = cipher.encrypt(nonce, data.as_bytes())?;

    // Return the ciphertext and the nonce used
    Ok((ciphertext, nonce_bytes.to_vec()))
}

/// Function to decrypt a string.
///
/// # Arguments
/// * `key` - The 16-byte key used for encryption.
/// * `nonce` - The nonce used during encryption.
/// * `ciphertext` - The encrypted data, including the authentication tag.
///
/// # Returns
/// A Result containing the decrypted data (as bytes) or a crypto error if decryption fails
/// (e.g., due to a wrong key or tampered ciphertext).
fn decrypt(key: &Key, nonce: &[u8], ciphertext: &[u8]) -> Result<Vec<u8>, aead::Error> {
    // Create a cipher instance from the key
    let cipher = Aes128Eax::new(GenericArray::from_slice(key));

    // Create the nonce object from the byte slice
    let nonce = GenericArray::from_slice(nonce);

    // Decrypt the data. This will fail if the key is wrong or the ciphertext has been tampered with.
    let decrypted_data = cipher.decrypt(nonce, ciphertext)?;

    Ok(decrypted_data)
}

fn main() {
    // Example usage
    let key = generate_key();
    let test_data = "Jim's test";

    println!("Original data: \"{}\"", test_data);
    println!("Generated Key (hex): {}", hex::encode(key));

    // Encrypt the data
    let (encrypted, nonce) = encrypt(&key, test_data).expect("Encryption failed!");
    println!("Encrypted (hex): {}", hex::encode(&encrypted));
    println!("Nonce (hex):     {}", hex::encode(&nonce));

    // Decrypt the data
    let restored_data_bytes = decrypt(&key, &nonce, &encrypted).expect("Decryption failed!");

    // Convert the restored bytes back to a string
    let restored_data = String::from_utf8(restored_data_bytes).expect("Invalid UTF-8 sequence");

    println!("Restored data:   \"{}\"", restored_data);

    // Verify that the restored data matches the original
    assert_eq!(test_data, restored_data);
    println!("\nSuccess! The restored data matches the original.");
}

// To print byte arrays as hex, we can add the `hex` crate to Cargo.toml
// [dependencies]
// hex = "0.4.3"
// Or just use this helper module if you don't want to add a dependency.
mod hex {
    pub fn encode<T: AsRef<[u8]>>(data: T) -> String {
        data.as_ref()
            .iter()
            .map(|b| format!("{:02x}", b))
            .collect()
    }
}