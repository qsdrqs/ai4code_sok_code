use aes::Aes256;
use eax::{
    aead::{Aead, KeyInit, OsRng},
    Eax, Nonce,
};

// Define a type alias for our AES-256-EAX cipher
type Aes256Eax = Eax<Aes256>;

// Define a type alias for the error type for cleaner function signatures
type EaxError = eax::Error;

/// Encrypts a message using AES-256-EAX with a random nonce.
///
/// # Arguments
/// * `m` - The message (plaintext) to encrypt.
/// * `sk` - The 32-byte secret key for AES-256.
///
/// # Returns
/// A `Result` containing a tuple of (`ciphertext`, `nonce`) on success.
/// The ciphertext includes the authentication tag.
/// The nonce is a 16-byte vector.
fn encrypt(m: &[u8], sk: &[u8; 32]) -> Result<(Vec<u8>, Vec<u8>), EaxError> {
    // Create a new AES-256-EAX cipher instance from the secret key.
    let cipher = Aes256Eax::new(sk.into());

    // Generate a cryptographically secure random nonce.
    // EAX uses a 16-byte (128-bit) nonce.
    let nonce = Aes256Eax::generate_nonce(&mut OsRng);

    // Encrypt the message. The `encrypt` method handles both encryption
    // and computes the authentication tag, appending it to the ciphertext.
    let ciphertext = cipher.encrypt(&nonce, m)?;

    // Return the ciphertext (with tag) and the nonce.
    Ok((ciphertext, nonce.to_vec()))
}

/// Decrypts a message using AES-256-EAX.
///
/// # Arguments
/// * `cn` - A tuple containing the `ciphertext` and the `nonce`.
/// * `sk` - The 32-byte secret key used for encryption.
///
/// # Returns
/// A `Result` containing the original plaintext `Vec<u8>` if decryption and
/// authentication are successful. Returns an `Err` if the key is wrong or
/// the ciphertext has been tampered with.
fn decrypt(cn: (Vec<u8>, Vec<u8>), sk: &[u8; 32]) -> Result<Vec<u8>, EaxError> {
    // Unpack the ciphertext and nonce from the tuple.
    let (c, nonce_bytes) = cn;

    // Create a new AES-256-EAX cipher instance from the secret key.
    let cipher = Aes256Eax::new(sk.into());

    // Convert the nonce Vec<u8> back into a Nonce object.
    let nonce = Nonce::from_slice(&nonce_bytes);

    // Decrypt the message. This method simultaneously decrypts and verifies
    // the authentication tag. If the tag is invalid, it will return an error.
    let plaintext = cipher.decrypt(nonce, c.as_slice())?;

    Ok(plaintext)
}

fn main() {
    // --- DEMONSTRATION ---

    // 1. Define a secret key and a message.
    // The key MUST be 32 bytes for AES-256.
    let secret_key = b"this-is-a-very-secret-key-1234";
    let message = b"Hello, Rust! This is a secret.";

    println!("Original Message: {}", String::from_utf8_lossy(message));
    println!("Secret Key:       {}", hex::encode(secret_key));
    println!("-----------------------------------------");

    // 2. Encrypt the message.
    let (ciphertext, nonce) = encrypt(message, secret_key).expect("Encryption failed!");

    println!("Nonce:            {}", hex::encode(&nonce));
    println!("Ciphertext:       {}", hex::encode(&ciphertext));
    println!("-----------------------------------------");

    // 3. Decrypt the message.
    let decrypted_message =
        decrypt((ciphertext.clone(), nonce.clone()), secret_key).expect("Decryption failed!");

    println!(
        "Decrypted Message: {}",
        String::from_utf8_lossy(&decrypted_message)
    );

    // 4. Assert that the decrypted message matches the original.
    assert_eq!(message, decrypted_message.as_slice());
    println!("\n✅ Success: Decrypted message matches the original.");

    // --- DEMONSTRATE AUTHENTICATION FAILURE ---
    println!("\n--- Tampering Demo ---");
    let mut tampered_ciphertext = ciphertext.clone();
    // Flip the last bit of the last byte of the ciphertext
    if let Some(last_byte) = tampered_ciphertext.last_mut() {
        *last_byte ^= 0x01; // XOR with 1
    }

    println!("Tampered Ciphertext: {}", hex::encode(&tampered_ciphertext));

    // Attempt to decrypt the tampered data. This MUST fail.
    let decryption_result = decrypt((tampered_ciphertext, nonce), secret_key);

    assert!(decryption_result.is_err());
    println!("✅ Success: Decryption of tampered data failed as expected.");
}