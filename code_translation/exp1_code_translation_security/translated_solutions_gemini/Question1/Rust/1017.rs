// main.rs

use aes::Aes256; // Using AES-256, but can be Aes128 or Aes192
use eax::Eax;
use eax::aead::{Aead, KeyInit, OsRng, generic_array::GenericArray};
use eax::aead::consts::U16; // For the 16-byte EAX tag size

// Define a type alias for our AEAD cipher for convenience.
// We are using AES-256 in EAX mode.
type Aes256Eax = Eax<Aes256>;

// The size of the key in bytes (32 bytes for AES-256).
const KEY_SIZE: usize = 32;
// The size of the nonce in bytes (16 bytes is standard for EAX).
const NONCE_SIZE: usize = 16;
// The size of the authentication tag in bytes (16 bytes for EAX).
const TAG_SIZE: usize = 16;


/// Function in Rust where one encrypts a given string using a given symmetric key.
///
/// Corresponds to the Python `encrypt` function.
/// Returns a tuple of (nonce, tag, ciphertext) on success.
fn encrypt(key: &[u8], plaintext: &[u8]) -> Result<(Vec<u8>, Vec<u8>, Vec<u8>), &'static str> {
    if key.len() != KEY_SIZE {
        return Err("Invalid key size");
    }

    // The `KeyInit` trait creates a new cipher instance from a key.
    // The key is wrapped in a `GenericArray` for type safety.
    let key = GenericArray::from_slice(key);
    let cipher = Aes256Eax::new(key);

    // Generate a random nonce. The `getrandom` feature on the `aead` crate
    // allows `OsRng` to be used for cryptographically secure random number generation.
    let nonce = Aes256Eax::generate_nonce(&mut OsRng); // 16-bytes for EAX

    // Encrypt the plaintext. The `encrypt` method is part of the `Aead` trait.
    // It returns a `Vec<u8>` containing the ciphertext and the authentication tag appended to it.
    let ciphertext_with_tag = cipher.encrypt(&nonce, plaintext)
        .map_err(|_| "Encryption failed!")?;

    // To match the Python function's output, we split the tag from the ciphertext.
    let ciphertext_len = ciphertext_with_tag.len() - TAG_SIZE;
    let ciphertext = ciphertext_with_tag[..ciphertext_len].to_vec();
    let tag = ciphertext_with_tag[ciphertext_len..].to_vec();

    Ok((nonce.to_vec(), tag, ciphertext))
}

/// Function in Rust where one decrypts a given string using a given symmetric key.
///
/// Corresponds to the Python `decrypt` function.
/// Note: The input is split into its components for clarity, unlike the Python version
/// which takes a single tuple.
fn decrypt(key: &[u8], nonce: &[u8], tag: &[u8], ciphertext: &[u8]) -> Result<Vec<u8>, &'static str> {
    if key.len() != KEY_SIZE {
        return Err("Invalid key size");
    }
    if nonce.len() != NONCE_SIZE {
        return Err("Invalid nonce size");
    }
    if tag.len() != TAG_SIZE {
        return Err("Invalid tag size");
    }

    let key = GenericArray::from_slice(key);
    let cipher = Aes256Eax::new(key);

    let nonce = GenericArray::from_slice(nonce);

    // The `decrypt` method requires the ciphertext and tag to be concatenated.
    let mut ciphertext_with_tag = ciphertext.to_vec();
    ciphertext_with_tag.extend_from_slice(tag);

    // Decrypt the data. This will fail if the tag verification does not succeed,
    // protecting against tampered data.
    let decrypted_data = cipher.decrypt(nonce, ciphertext_with_tag.as_slice())
        .map_err(|_| "Decryption failed! The data may be tampered or the key is wrong.")?;

    Ok(decrypted_data)
}


fn main() {
    // --- DEMONSTRATION ---

    // 1. Generate a random 32-byte key for AES-256.
    // In a real application, this key must be securely stored and managed.
    let mut key = [0u8; KEY_SIZE];
    getrandom::getrandom(&mut key).expect("Failed to generate random key");

    let plaintext = b"This is a secret message that needs to be encrypted.";

    println!("Original Plaintext: {}", String::from_utf8_lossy(plaintext));
    println!("Using Key: {}", hex::encode(key));
    println!("-------------------------------------------------");

    // 2. Encrypt the data
    let (nonce, tag, ciphertext) = match encrypt(&key, plaintext) {
        Ok(result) => result,
        Err(e) => {
            eprintln!("Error during encryption: {}", e);
            return;
        }
    };

    println!("Encryption Successful!");
    println!("Nonce: {}", hex::encode(&nonce));
    println!("Authentication Tag: {}", hex::encode(&tag));
    println!("Ciphertext: {}", hex::encode(&ciphertext));
    println!("-------------------------------------------------");


    // 3. Decrypt the data
    let decrypted_data = match decrypt(&key, &nonce, &tag, &ciphertext) {
        Ok(data) => data,
        Err(e) => {
            eprintln!("Error during decryption: {}", e);
            return;
        }
    };

    println!("Decryption Successful!");
    println!("Decrypted Data: {}", String::from_utf8_lossy(&decrypted_data));

    // 4. Verify that the decrypted data matches the original plaintext
    assert_eq!(plaintext, decrypted_data.as_slice());
    println!("\n✅ Verification successful: Decrypted data matches original plaintext.");


    // 5. DEMONSTRATE FAILURE: Tamper with the ciphertext
    println!("\n--- Demonstrating Tampering Detection ---");
    let mut tampered_ciphertext = ciphertext.clone();
    tampered_ciphertext[0] ^= 0xff; // Flip the bits of the first byte

    println!("Attempting to decrypt with tampered ciphertext...");
    match decrypt(&key, &nonce, &tag, &tampered_ciphertext) {
        Ok(_) => println!("❌ Tampering NOT detected. This is a security failure!"),
        Err(e) => println!("✅ Correctly failed to decrypt: {}", e),
    }
}