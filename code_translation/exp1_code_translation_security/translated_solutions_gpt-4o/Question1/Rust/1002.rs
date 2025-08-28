use aes::Aes256;
use block_modes::{BlockMode, Cbc};
use block_modes::block_padding::Pkcs7;
use rand::Rng;

// Type alias for AES-256-CBC
type Aes256Cbc = Cbc<Aes256, Pkcs7>;

fn encrypt(data: &[u8], key: &[u8], iv: &[u8]) -> Vec<u8> {
    // Create a new AES-256-CBC cipher
    let cipher = Aes256Cbc::new_from_slices(key, iv).expect("Invalid key or IV length");
    // Encrypt the data
    cipher.encrypt_vec(data)
}

fn decrypt(data: &[u8], key: &[u8], iv: &[u8]) -> Vec<u8> {
    // Create a new AES-256-CBC cipher
    let cipher = Aes256Cbc::new_from_slices(key, iv).expect("Invalid key or IV length");
    // Decrypt the data
    cipher.decrypt_vec(data).expect("Decryption failed")
}

fn test() {
    let data = b"a secret message";
    let key: [u8; 32] = rand::thread_rng().gen(); // Generate a random 32-byte key
    let iv: [u8; 16] = rand::thread_rng().gen();  // Generate a random 16-byte IV

    let encrypted = encrypt(data, &key, &iv);
    let decrypted = decrypt(&encrypted, &key, &iv);

    assert_eq!(decrypted, data);
}

fn main() {
    test();
    println!("Test passed!");
}