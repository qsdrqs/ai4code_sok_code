use aes::Aes256;
use block_modes::{BlockMode, Cbc};
use block_modes::block_padding::Pkcs7;
use rand::Rng;
use base64::{encode, decode};

// Type alias for AES-256-CBC
type Aes256Cbc = Cbc<Aes256, Pkcs7>;

// Function to encrypt plaintext
fn encrypt(key: &[u8], plaintext: &[u8]) -> String {
    // Generate a random IV (16 bytes)
    let iv = rand::thread_rng().gen::<[u8; 16]>();

    // Create the AES cipher in CBC mode
    let cipher = Aes256Cbc::new_from_slices(key, &iv).unwrap();

    // Encrypt the plaintext (automatically padded with PKCS7)
    let ciphertext = cipher.encrypt_vec(plaintext);

    // Concatenate IV and ciphertext, then encode in Base64
    let mut result = Vec::new();
    result.extend_from_slice(&iv);
    result.extend_from_slice(&ciphertext);
    encode(&result)
}

// Function to decrypt ciphertext
fn decrypt(key: &[u8], ciphertext: &str) -> Vec<u8> {
    // Decode the Base64-encoded ciphertext
    let decoded = decode(ciphertext).unwrap();

    // Extract the IV (first 16 bytes) and the actual ciphertext
    let (iv, ciphertext) = decoded.split_at(16);

    // Create the AES cipher in CBC mode
    let cipher = Aes256Cbc::new_from_slices(key, iv).unwrap();

    // Decrypt the ciphertext (automatically removes PKCS7 padding)
    cipher.decrypt_vec(ciphertext).unwrap()
}

fn main() {
    // Example usage
    let key = b"an_example_very_secure_key_32bytes!"; // 32-byte key for AES-256
    let plaintext = b"Hello, AES encryption in Rust!";

    // Encrypt the plaintext
    let encrypted = encrypt(key, plaintext);
    println!("Encrypted: {}", encrypted);

    // Decrypt the ciphertext
    let decrypted = decrypt(key, &encrypted);
    println!("Decrypted: {}", String::from_utf8(decrypted).unwrap());
}