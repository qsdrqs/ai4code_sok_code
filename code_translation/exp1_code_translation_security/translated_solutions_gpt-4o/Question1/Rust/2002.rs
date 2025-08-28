use aes::Aes256;
use ctr::cipher::{KeyIvInit, StreamCipher};
use ctr::Ctr128BE;

type Aes256Ctr = Ctr128BE<Aes256>;

fn encrypt(key: &[u8], message: &str) -> Vec<u8> {
    // Create a 16-byte IV (Initialization Vector) for CTR mode
    let iv = [0u8; 16]; // In a real-world scenario, use a random IV and send it with the ciphertext
    let mut cipher = Aes256Ctr::new_from_slices(key, &iv).expect("Invalid key or IV");
    let mut buffer = message.as_bytes().to_vec();
    cipher.apply_keystream(&mut buffer);
    buffer
}

fn decrypt(key: &[u8], ciphertext: &[u8]) -> String {
    // Use the same IV as in the encryption step
    let iv = [0u8; 16]; // In a real-world scenario, retrieve the IV from the ciphertext
    let mut cipher = Aes256Ctr::new_from_slices(key, &iv).expect("Invalid key or IV");
    let mut buffer = ciphertext.to_vec();
    cipher.apply_keystream(&mut buffer);
    String::from_utf8(buffer).expect("Decryption failed: invalid UTF-8")
}

fn test() {
    fn round_trip_test() {
        let key = b"This_key_for_demo_purposes_only!";
        let plaintext = "Text may be any length you wish, no padding is required";
        let ciphertext = encrypt(key, plaintext);
        let decrypted_text = decrypt(key, &ciphertext);
        println!("{}", decrypted_text);
    }

    round_trip_test();
}

fn main() {
    test();
}