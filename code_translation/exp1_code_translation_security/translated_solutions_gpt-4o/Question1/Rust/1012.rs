use aes_gcm::{Aes256Gcm, Key, Nonce}; // Or `aes-gcm-siv` for SIV mode
use aes_gcm::aead::{Aead, NewAead};
use rand::Rng;

fn generate_key() -> Vec<u8> {
    let mut key = vec![0u8; 32]; // 256-bit key
    rand::thread_rng().fill(&mut key[..]);
    key
}

fn encrypt_text(plain_text: &str, key: &[u8]) -> Result<String, Box<dyn std::error::Error>> {
    let cipher = Aes256Gcm::new(Key::from_slice(key));
    let nonce = rand::thread_rng().gen::<[u8; 12]>(); // 96-bit nonce
    let nonce = Nonce::from_slice(&nonce);

    let encrypted_text = cipher.encrypt(nonce, plain_text.as_bytes())?;
    let mut result = nonce.to_vec();
    result.extend_from_slice(&encrypted_text);

    Ok(base64::encode(result))
}

fn decrypt_text(encrypted_text: &str, key: &[u8]) -> Result<String, Box<dyn std::error::Error>> {
    let cipher = Aes256Gcm::new(Key::from_slice(key));
    let encrypted_data = base64::decode(encrypted_text)?;
    let (nonce, ciphertext) = encrypted_data.split_at(12); // Split nonce and ciphertext
    let nonce = Nonce::from_slice(nonce);

    let decrypted_text = cipher.decrypt(nonce, ciphertext)?;
    Ok(String::from_utf8(decrypted_text)?)
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    let key = generate_key();

    let plain_text = "Hello, world!";
    let encrypted_text = encrypt_text(plain_text, &key)?;
    println!("Encrypted: {}", encrypted_text);

    let decrypted_text = decrypt_text(&encrypted_text, &key)?;
    println!("Decrypted: {}", decrypted_text);

    Ok(())
}