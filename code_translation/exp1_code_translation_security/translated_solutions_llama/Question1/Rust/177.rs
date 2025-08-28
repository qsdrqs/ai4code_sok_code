use aes_gcm::{Aes256Gcm, Key, Nonce};
use rand::Rng;

// Import necessary dependencies
#[cfg(feature = "std")]
extern crate std;

fn main() {
    // Example usage
    let key = [0u8; 32]; // 256-bit key
    let message = b"Hello, World!";
    let encrypted = encrypt(message, &key);
    let decrypted = decrypt(encrypted, &key);
    println!("Decrypted: {:?}", decrypted);
}

fn encrypt(message: &[u8], sk: &[u8]) -> (Vec<u8>, Vec<u8>) {
    // Generate a random 96-bit nonce
    let mut rng = rand::thread_rng();
    let nonce = rng.gen::<[u8; 12]>();

    // Create a new AES-256-GCM cipher
    let key = Key::from_slice(sk);
    let cipher = Aes256Gcm::new(key);

    // Encrypt the message
    let encrypted = cipher.encrypt(&nonce.into(), message).unwrap();

    // Return the encrypted message and nonce
    (encrypted.to_vec(), nonce.to_vec())
}

fn decrypt(encrypted_nonce: (Vec<u8>, Vec<u8>), sk: &[u8]) -> Vec<u8> {
    // Extract the encrypted message and nonce
    let (encrypted, nonce) = encrypted_nonce;

    // Create a new AES-256-GCM cipher
    let key = Key::from_slice(sk);
    let cipher = Aes256Gcm::new(key);

    // Decrypt the message
    cipher.decrypt(&nonce.into(), &encrypted).unwrap().to_vec()
}