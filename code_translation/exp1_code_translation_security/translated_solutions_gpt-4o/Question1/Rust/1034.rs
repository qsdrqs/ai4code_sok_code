use aes_gcm_siv::aead::{Aead, KeyInit, OsRng};
use aes_gcm_siv::{Aes256GcmSiv, Key, Nonce}; // Or `Aes128GcmSiv`
use base64::{encode, decode};

fn encode_str(plain_text: &str, key: &str) -> String {
    let key = Key::from_slice(key.as_bytes());
    let cipher = Aes256GcmSiv::new(key);

    let nonce = Nonce::from_slice(b"unique nonce"); // 12-bytes; unique per message
    let ciphertext = cipher.encrypt(nonce, plain_text.as_bytes())
        .expect("encryption failure!");

    encode(ciphertext)
}

fn decode_str(str_encoded: &str, key: &str) -> String {
    let key = Key::from_slice(key.as_bytes());
    let cipher = Aes256GcmSiv::new(key);

    let nonce = Nonce::from_slice(b"unique nonce"); // 12-bytes; unique per message
    let ciphertext = decode(str_encoded).expect("decoding failure!");
    let plaintext = cipher.decrypt(nonce, ciphertext.as_ref())
        .expect("decryption failure!");

    String::from_utf8(plaintext).expect("utf-8 conversion failure")
}

fn main() {
    let str_encoded1 = encode_str("I am okay", "wow");
    println!("{}", decode_str(&str_encoded1, "wow"));
}