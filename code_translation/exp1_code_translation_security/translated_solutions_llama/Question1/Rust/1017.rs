use aes_gcm::{Aes256Gcm, Key, Nonce};
use rand::Rng;

// Function to encrypt a given string using a given symmetric key.
pub fn encrypt(key: &[u8], plaintext: &[u8]) -> (Vec<u8>, Vec<u8>, Vec<u8>) {
    // Generate a random 96-bit nonce.
    let mut rng = rand::thread_rng();
    let nonce = rng.gen::<[u8; 12]>();

    // Create a new AES-GCM cipher.
    let key = Key::from_slice(key);
    let cipher = Aes256Gcm::new(key);

    // Encrypt the plaintext.
    let encrypted = cipher.encrypt(Nonce::from_slice(&nonce), plaintext).unwrap();

    // Return the nonce, encrypted data.
    (nonce.to_vec(), encrypted)
}

// Function to decrypt a given string using a given symmetric key.
pub fn decrypt(key: &[u8], ciphertext: (Vec<u8>, Vec<u8>)) -> Vec<u8> {
    let (nonce, encrypted) = ciphertext;

    // Create a new AES-GCM cipher.
    let key = Key::from_slice(key);
    let cipher = Aes256Gcm::new(key);

    // Decrypt the ciphertext.
    cipher.decrypt(Nonce::from_slice(&nonce), &encrypted).unwrap()
}

fn main() {
    // Example usage:
    let key = [0u8; 32]; // 256-bit key
    let plaintext = b"Hello, World!";
    let (nonce, encrypted) = encrypt(&key, plaintext);
    println!("Nonce: {:?}", nonce);
    println!("Encrypted: {:?}", encrypted);

    let decrypted = decrypt(&key, (nonce, encrypted));
    println!("Decrypted: {:?}", String::from_utf8_lossy(&decrypted));
}