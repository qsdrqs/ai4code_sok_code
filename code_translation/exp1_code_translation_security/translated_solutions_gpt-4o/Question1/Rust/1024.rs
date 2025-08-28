extern crate aes;
extern crate block_modes;

use aes::Aes128;
use block_modes::{BlockMode, Cfb};
use block_modes::block_padding::Pkcs7;
use hex_literal::hex;

// Create an alias for convenience
type AesCfb = Cfb<Aes128, Pkcs7>;

fn encrypt_string(message: &[u8], cipher: &AesCfb) -> Vec<u8> {
    cipher.encrypt_vec(message)
}

fn decrypt_string(ciphertext: &[u8], cipher: &AesCfb) -> Vec<u8> {
    cipher.decrypt_vec(ciphertext).expect("Decryption failed")
}

fn main() {
    let key = b"This is a key123"; // 16 bytes key
    let iv = b"This is an IV456";  // 16 bytes IV

    // Create cipher instances
    let cipher1 = AesCfb::new_var(key, iv).unwrap();
    let cipher2 = AesCfb::new_var(key, iv).unwrap();

    let message = b"The answer is no";
    let enc = encrypt_string(message, &cipher1);
    let dec = decrypt_string(&enc, &cipher2);

    println!("Encrypted: {:?}", enc);
    println!("Decrypted: {:?}", String::from_utf8(dec).unwrap());
}