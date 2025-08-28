use aes::{Aes128, Aes192, Aes256, BlockCipher, NewBlockCipher};
use aes::cipher::{NewCipher, StreamCipher};
use rand::Rng;
use base64;

const BLOCK_SIZE: usize = 16;

// Function to pad the plaintext
fn pad(plaintext: &[u8]) -> Vec<u8> {
    let padding_len = BLOCK_SIZE - (plaintext.len() % BLOCK_SIZE);
    let mut padded_txt = plaintext.to_vec();
    padded_txt.resize(plaintext.len() + padding_len, padding_len as u8);
    padded_txt
}

// Function to unpad the plaintext
fn unpad(padded_txt: &[u8]) -> Vec<u8> {
    let padding_len = padded_txt[padded_txt.len() - 1] as usize;
    let mut plaintext = padded_txt.to_vec();
    plaintext.truncate(padded_txt.len() - padding_len);
    plaintext
}

// Function to encrypt the plaintext using a symmetric key
fn encrypt(key: &[u8], plaintext: &[u8]) -> Vec<u8> {
    let mut iv = [0u8; BLOCK_SIZE];
    rand::thread_rng().fill_bytes(&mut iv);

    let cipher = match key.len() {
        16 => Aes128::new_from_slice(key).unwrap(),
        24 => Aes192::new_from_slice(key).unwrap(),
        32 => Aes256::new_from_slice(key).unwrap(),
        _ => panic!("Invalid key size"),
    };

    let mut cipher_ctx = cipher.encryptor().unwrap();
    let mut padded_txt = pad(plaintext);
    let mut cipher_txt = vec![0; padded_txt.len()];
    cipher_ctx.process(&padded_txt, &mut cipher_txt).unwrap();

    let mut result = Vec::with_capacity(BLOCK_SIZE + cipher_txt.len());
    result.extend_from_slice(&iv);
    result.extend_from_slice(&cipher_txt);

    base64::encode(result).into_bytes()
}

// Function to decrypt the ciphertext using a symmetric key
fn decrypt(key: &[u8], ciphertext: &[u8]) -> Vec<u8> {
    let ciphertext = base64::decode(ciphertext).unwrap();
    let iv = &ciphertext[..BLOCK_SIZE];
    let cipher_txt = &ciphertext[BLOCK_SIZE..];

    let cipher = match key.len() {
        16 => Aes128::new_from_slice(key).unwrap(),
        24 => Aes192::new_from_slice(key).unwrap(),
        32 => Aes256::new_from_slice(key).unwrap(),
        _ => panic!("Invalid key size"),
    };

    let mut cipher_ctx = cipher.decryptor().unwrap();
    let mut plaintext = vec![0; cipher_txt.len()];
    cipher_ctx.process(cipher_txt, &mut plaintext).unwrap();

    unpad(&plaintext)
}

fn main() {
    let key = [0u8; 32]; // Replace with your 256-bit key
    let plaintext = b"Hello, World!";
    let ciphertext = encrypt(&key, plaintext);
    println!("Ciphertext: {}", String::from_utf8_lossy(&ciphertext));

    let decrypted_text = decrypt(&key, &ciphertext);
    println!("Decrypted Text: {}", String::from_utf8_lossy(&decrypted_text));
}