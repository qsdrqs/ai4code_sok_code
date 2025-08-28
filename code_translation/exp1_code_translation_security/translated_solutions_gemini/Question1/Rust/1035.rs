use eax::{Eax, Aes128Eax}; // Specific implementation of EAX mode with AES-128
use aes::Aes128;           // The AES-128 block cipher
use aead::{Aead, AeadCore, KeyInit, OsRng}; // Core AEAD traits and OS random number generator
use aead::generic_array::GenericArray; // For handling fixed-size arrays like keys and nonces

// Define a type alias for the nonce for convenience. EAX uses a 128-bit (16-byte) nonce.
type Nonce = GenericArray<u8, <Aes128Eax as AeadCore>::NonceSize>;

/// Encrypts a message using AES-128-EAX.
///
/// In the Rust `aead` ecosystem, the authentication tag is appended to the ciphertext
/// automatically, so we return a single `Vec<u8>` for both. The nonce is generated
/// randomly and must also be returned.
///
/// # Arguments
/// * `key` - A 16-byte (128-bit) key.
/// * `msg` - The message to encrypt.
///
/// # Returns
/// A `Result` containing a tuple of the (ciphertext + tag) and the nonce,
/// or an error if encryption fails.
fn encrypt(key: &[u8; 16], msg: &[u8]) -> Result<(Vec<u8>, Nonce), aead::Error> {
    // Create a new cipher instance from the key.
    let cipher = Aes128Eax::new(key.into());

    // Generate a cryptographically secure random nonce.
    // The nonce must be unique for every message encrypted with the same key.
    let nonce = Aes128Eax::generate_nonce(&mut OsRng);

    // Encrypt the message. The `encrypt` method handles padding and tag generation.
    // The returned `Vec<u8>` contains the ciphertext followed by the authentication tag.
    let ciphertext_with_tag = cipher.encrypt(&nonce, msg)?;

    Ok((ciphertext_with_tag, nonce))
}

/// Decrypts a message using AES-128-EAX.
///
/// The `decrypt` method automatically verifies the authentication tag. If the tag is
/// invalid or the ciphertext has been tampered with, it will return an error.
///
/// # Arguments
/// * `key` - The same 16-byte key used for encryption.
/// * `ciphertext_with_tag` - The combined ciphertext and tag.
/// * `nonce` - The same nonce used for encryption.
///
/// # Returns
/// A `Result` containing the original plaintext `Vec<u8>`, or an error if
/// decryption or authentication fails.
fn decrypt(key: &[u8; 16], ciphertext_with_tag: &[u8], nonce: &Nonce) -> Result<Vec<u8>, aead::Error> {
    // Create a new cipher instance from the key.
    let cipher = Aes128Eax::new(key.into());

    // Decrypt the message. This method also verifies the authentication tag.
    // If verification fails, it returns an `Err`.
    let plaintext = cipher.decrypt(nonce, ciphertext_with_tag)?;

    Ok(plaintext)
}

fn main() {
    // The same 16-byte key as in the Python example.
    let key = b"Sixteen byte key";

    // The message to be encrypted.
    let message = b"message";

    println!("Original: {}", String::from_utf8_lossy(message));

    // Encrypt the message.
    // We use `unwrap()` for simplicity in this example. In production code,
    // you should handle the `Result` properly (e.g., with `match` or `?`).
    let (ciphertext, nonce) = encrypt(key, message).expect("Encryption failed!");

    println!("Encrypted (ciphertext + tag): {:x?}", ciphertext);
    println!("Nonce: {:x?}", nonce.as_slice());

    // Decrypt the message.
    let plaintext = decrypt(key, &ciphertext, &nonce).expect("Decryption failed!");

    println!("Decrypted: {}", String::from_utf8_lossy(&plaintext));

    // Verify that the decrypted message matches the original.
    assert_eq!(message, plaintext.as_slice());
    println!("\nSuccessfully encrypted and decrypted the message!");
}