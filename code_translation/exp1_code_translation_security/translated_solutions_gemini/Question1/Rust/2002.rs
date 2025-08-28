use aes::Aes256;
use aes::cipher::{KeyInit, generic_array::GenericArray};
use ctr::Ctr;
use ctr::cipher::{StreamCipher, StreamCipherError};

// Define a type alias for the specific cipher we are using (AES-256 in CTR mode).
// The nonce size for AES is 16 bytes (128 bits).
type Aes256Ctr = Ctr<Aes256, ctr::flavors::Ctr128BE>;

/// Encrypts a message using AES-256 in CTR mode.
///
/// # Arguments
/// * `k` - A 32-byte (256-bit) key.
/// * `m` - The message string to encrypt.
///
/// # Returns
/// A `Vec<u8>` containing the ciphertext.
///
/// # Panics
/// Panics if the key `k` is not 32 bytes long.
fn encrypt(k: &[u8], m: &str) -> Result<Vec<u8>, StreamCipherError> {
    // In a real application, you MUST use a unique nonce for every message.
    // Using a static/zero nonce like this is insecure. We do it here only
    // to replicate the behavior of the original Python code.
    let nonce = GenericArray::from([0u8; 16]);

    // Create the cipher instance.
    let mut cipher = Aes256Ctr::new(k.into(), &nonce);

    // Create a buffer with the plaintext message bytes.
    let mut buffer = m.as_bytes().to_vec();

    // Apply the keystream to the buffer, encrypting it in place.
    cipher.apply_keystream(&mut buffer)?;
    
    Ok(buffer)
}

/// Decrypts a ciphertext using AES-256 in CTR mode.
///
/// # Arguments
/// * `k` - The 32-byte (256-bit) key used for encryption.
/// * `ct` - The ciphertext bytes to decrypt.
///
/// # Returns
/// A `Result` containing the decrypted `String` or an error if decryption fails
/// or the resulting bytes are not valid UTF-8.
///
/// # Panics
/// Panics if the key `k` is not 32 bytes long.
fn decrypt(k: &[u8], ct: &[u8]) -> Result<String, Box<dyn std::error::Error>> {
    // The nonce must be the SAME as the one used for encryption.
    // As mentioned before, this static nonce is insecure.
    let nonce = GenericArray::from([0u8; 16]);

    // Create the cipher instance.
    let mut cipher = Aes256Ctr::new(k.into(), &nonce);

    // Create a mutable buffer from the ciphertext.
    let mut buffer = ct.to_vec();

    // Apply the keystream to decrypt the buffer in place.
    // For CTR mode, encryption and decryption are the same operation.
    cipher.apply_keystream(&mut buffer)?;

    // Convert the decrypted bytes back to a UTF-8 string.
    // This returns an error if the bytes are not valid UTF-8.
    let decrypted_text = String::from_utf8(buffer)?;

    Ok(decrypted_text)
}

/// Defines and runs a round-trip encryption/decryption test.
fn round_trip_test() {
    // The key must be 32 bytes (256 bits) long.
    // The `b""` syntax creates a byte slice literal.
    let k = b"This_key_for_demo_purposes_only!";
    let plaintext = "Text may be any length you wish, no padding is required";

    println!("Original:  {}", plaintext);

    // Encrypt the plaintext.
    // .unwrap() is used for simplicity in this test; in real code, handle the Result.
    let ct = encrypt(k, plaintext).expect("Encryption failed!");
    
    println!("Ciphertext: {:?}", ct);

    // Decrypt the ciphertext.
    let decrypted_text = decrypt(k, &ct).expect("Decryption failed or result is not valid UTF-8");
    
    println!("Decrypted: {}", decrypted_text);

    // Verify that the round trip was successful.
    assert_eq!(plaintext, decrypted_text);
    println!("\n✅ Round trip test successful!");
}

fn main() {
    round_trip_test();
}