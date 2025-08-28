use aes::Aes128;
use aes_gcm::aead::{Aead, KeyInit, OsRng};
use aes_gcm::Aes128Eax; // AES with EAX mode
use aes_gcm::Nonce; // Nonce size for AES-EAX

// Function to encrypt a message
fn encrypt(message: &[u8], secret_key: &[u8]) -> (Vec<u8>, Vec<u8>) {
    // Create a new AES-EAX cipher instance
    let cipher = Aes128Eax::new_from_slice(secret_key).expect("Invalid key length");

    // Generate a random nonce
    let nonce = Nonce::generate(&mut OsRng);

    // Encrypt the message
    let ciphertext = cipher
        .encrypt(&nonce, message)
        .expect("Encryption failed");

    // Return the ciphertext and nonce
    (ciphertext, nonce.to_vec())
}

// Function to decrypt a message
fn decrypt(ciphertext_and_nonce: (Vec<u8>, Vec<u8>), secret_key: &[u8]) -> Vec<u8> {
    let (ciphertext, nonce) = ciphertext_and_nonce;

    // Create a new AES-EAX cipher instance
    let cipher = Aes128Eax::new_from_slice(secret_key).expect("Invalid key length");

    // Convert the nonce back to the expected type
    let nonce = Nonce::from_slice(&nonce);

    // Decrypt the ciphertext
    cipher
        .decrypt(nonce, ciphertext.as_ref())
        .expect("Decryption failed")
}

fn main() {
    // Example usage
    let secret_key = b"anexamplekey123"; // 16-byte key for AES-128
    let message = b"Hello, Rust AES!";

    // Encrypt the message
    let (ciphertext, nonce) = encrypt(message, secret_key);
    println!("Ciphertext: {:?}", ciphertext);
    println!("Nonce: {:?}", nonce);

    // Decrypt the message
    let decrypted_message = decrypt((ciphertext, nonce), secret_key);
    println!("Decrypted message: {:?}", String::from_utf8(decrypted_message).unwrap());
}