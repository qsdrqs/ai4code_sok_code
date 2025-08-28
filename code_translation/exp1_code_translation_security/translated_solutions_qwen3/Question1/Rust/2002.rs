use aes::cipher::{BlockCipher, KeyInit};
use aes::Aes256;

// A custom counter for CTR mode
struct Counter {
    nonce: [u8; 8],
    counter: u64,
}

impl Counter {
    fn new(nonce: [u8; 8]) -> Self {
        Self {
            nonce,
            counter: 0,
        }
    }

    fn get_block(&self) -> [u8; 16] {
        let mut block = [0; 16];
        block[..8].copy_from_slice(&self.nonce);
        let c_bytes = self.counter.to_le_bytes();
        block[8..16].copy_from_slice(&c_bytes);
        block
    }

    fn increment(&mut self) {
        self.counter += 1;
    }
}

// Encrypts a UTF-8 string using AES-CTR with a 256-bit key
pub fn encrypt(key: &[u8], plaintext: &str) -> Vec<u8> {
    assert_eq!(key.len(), 32, "Key must be 32 bytes for AES-256");

    let cipher = Aes256::from_key_slice(key).expect("Invalid key length");
    let mut counter = Counter::new([0; 8]);
    let plaintext_bytes = plaintext.as_bytes();
    let mut encrypted = Vec::with_capacity(plaintext_bytes.len());

    for chunk in plaintext_bytes.chunks(16) {
        let mut key_stream = counter.get_block();
        cipher.encrypt_block(&mut key_stream);

        for (i, &byte) in chunk.iter().enumerate() {
            encrypted.push(byte ^ key_stream[i]);
        }

        counter.increment();
    }

    encrypted
}

// Decrypts a byte slice using AES-CTR with a 256-bit key
pub fn decrypt(key: &[u8], ciphertext: &[u8]) -> String {
    assert_eq!(key.len(), 32, "Key must be 32 bytes for AES-256");

    let cipher = Aes256::from_key_slice(key).expect("Invalid key length");
    let mut counter = Counter::new([0; 8]);
    let mut decrypted = Vec::with_capacity(ciphertext.len());

    for chunk in ciphertext.chunks(16) {
        let mut key_stream = counter.get_block();
        cipher.encrypt_block(&mut key_stream);

        for (i, &byte) in chunk.iter().enumerate() {
            decrypted.push(byte ^ key_stream[i]);
        }

        counter.increment();
    }

    String::from_utf8(decrypted).expect("Failed to convert decrypted bytes to UTF-8 string")
}

// Test function to verify round-trip encryption and decryption
fn test() {
    fn round_trip_test() {
        let key = "This_key_for_demo_purposes_only!".as_bytes();
        let plaintext = "Text may be any length you wish, no padding is required";
        let ciphertext = encrypt(key, plaintext);
        let decrypted = decrypt(key, &ciphertext);
        println!("{}", decrypted);
    }

    round_trip_test();
}

fn main() {
    test();
}