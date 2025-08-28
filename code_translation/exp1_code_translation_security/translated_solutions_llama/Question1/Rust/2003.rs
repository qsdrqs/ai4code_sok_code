use aes_gcm::{Aes256Gcm, Key, Nonce};
use rand::Rng;

fn main() {
    let inp = b"1111111111111111";
    let key = b"11111111111111111111111111111111"; // AES-256 requires a 32-byte key

    let encoded = encode(inp, key);
    let decoded = decode(&encoded, key);

    assert_eq!(decoded, inp);
}

fn encode(inp: &[u8], key: &[u8]) -> Vec<u8> {
    /**
     * Encodes a byte slice using an AES key in ECB mode.
     */
    let key = Key::from_slice(key);
    let cipher = Aes256Gcm::new(key);

    // ECB mode requires padding, but for simplicity, 
    // we will use a fixed-size input and a multiple of the block size.
    // In a real application, use a secure padding scheme.

    let encoded = cipher.encrypt(Nonce::from_slice(&[0u8; 12]), inp).unwrap();
    encoded
}

fn decode(inp: &[u8], key: &[u8]) -> Vec<u8> {
    /**
     * Decodes a byte slice using an AES key in ECB mode.
     */
    let key = Key::from_slice(key);
    let cipher = Aes256Gcm::new(key);

    let decoded = cipher.decrypt(Nonce::from_slice(&[0u8; 12]), inp).unwrap();
    decoded
}