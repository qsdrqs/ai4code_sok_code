use aes::cipher::{block_padding::Pkcs7, BlockDecryptMut, BlockEncryptMut, KeyIvInit};
use base64::{engine::general_purpose, Engine as _};
use rand::RngCore;
use sha2::{Digest, Sha256};

type Aes256CbcEnc = cbc::Encryptor<aes::Aes256>;
type Aes256CbcDec = cbc::Decryptor<aes::Aes256>;

fn encode_str(plain_text: &str, key: &str) -> String {
    // Derive AES-256 key: SHA-256 of the password (matches cryptocode)
    let private_key = Sha256::digest(key.as_bytes());

    // Generate random IV (16 bytes = AES block size)
    let mut iv = [0u8; 16];
    rand::thread_rng().fill_bytes(&mut iv);

    // Encrypt using AES-256-CBC with PKCS7 padding
    let cipher = Aes256CbcEnc::new_from_slices(&private_key, &iv).unwrap();
    let cipher_text = cipher.encrypt_padded_vec_mut::<Pkcs7>(plain_text.as_bytes());

    // Concatenate iv + ciphertext and base64-encode
    let mut combined = Vec::with_capacity(16 + cipher_text.len());
    combined.extend_from_slice(&iv);
    combined.extend_from_slice(&cipher_text);

    general_purpose::STANDARD.encode(&combined)
}

fn decode_str(str_encoded: &str, key: &str) -> String {
    // Derive the same AES-256 key from the password
    let private_key = Sha256::digest(key.as_bytes());

    // Base64-decode and split IV (first 16 bytes) and ciphertext
    let combined = general_purpose::STANDARD.decode(str_encoded).unwrap();
    let iv = &combined[..16];
    let cipher_text = &combined[16..];

    // Decrypt using AES-256-CBC with PKCS7 unpadding
    let cipher = Aes256CbcDec::new_from_slices(&private_key, iv).unwrap();
    let plain_text = cipher
        .decrypt_padded_vec_mut::<Pkcs7>(cipher_text)
        .unwrap();

    String::from_utf8(plain_text).unwrap()
}

fn main() {
    let str_encoded1 = encode_str("I am okay", "wow");
    println!("{}", decode_str(&str_encoded1, "wow"));
}