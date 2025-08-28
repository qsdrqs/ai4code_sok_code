use aes::Aes128;
use block_modes::{BlockMode, Cbc};
use block_modes::block_padding::Pkcs7;
use hex_literal::hex;

// Type alias for AES-128-CBC with PKCS7 padding
type Aes128Cbc = Cbc<Aes128, Pkcs7>;

pub fn encrypt(message: &str, key: &[u8; 16], iv: &[u8; 16]) -> Vec<u8> {
    // Convert message to bytes
    let message_bytes = message.as_bytes();

    // Create cipher
    let cipher = Aes128Cbc::new_from_slices(key, iv).unwrap();

    // Encrypt message
    let ciphertext = cipher.encrypt_vec(message_bytes);

    // Return ciphertext
    ciphertext
}

pub fn decrypt(ciphertext: &[u8], key: &[u8; 16], iv: &[u8; 16]) -> String {
    // Create cipher
    let cipher = Aes128Cbc::new_from_slices(key, iv).unwrap();

    // Decrypt message
    let decrypted_bytes = cipher.decrypt_vec(ciphertext).unwrap();

    // Convert decrypted bytes to string
    let message = String::from_utf8(decrypted_bytes).unwrap();

    // Return message
    message
}

fn main() {
    // Example usage
    let key = b"0123456789abcdef"; // 16-byte key
    let iv = b"abcdef9876543210"; // 16-byte IV

    let message = "Hello, Rust!";
    println!("Original message: {}", message);

    // Encrypt the message
    let ciphertext = encrypt(message, key, iv);
    println!("Ciphertext: {:?}", ciphertext);

    // Decrypt the message
    let decrypted_message = decrypt(&ciphertext, key, iv);
    println!("Decrypted message: {}", decrypted_message);
}