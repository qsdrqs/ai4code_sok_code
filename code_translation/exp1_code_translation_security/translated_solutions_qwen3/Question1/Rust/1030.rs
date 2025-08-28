use rand::RngCore;
use base64::{encode as b64encode, decode as b64decode};
use aes::Aes128;
use aes::BlockCipher;
use generic_array::GenericArray;
use std::error::Error;
use std::iter;

fn generate_secret_key_for_aes_cipher() -> String {
    let mut key = [0u8; 16];
    rand::thread_rng().fill_bytes(&mut key);
    b64encode(&key)
}

fn encrypt_message(
    private_msg: &str,
    encoded_secret_key: &str,
    padding_char: char,
) -> Result<String, Box<dyn Error>> {
    let secret_key = b64decode(encoded_secret_key)?;
    if secret_key.len() != 16 {
        return Err("Key must be 16 bytes".into());
    }

    // Pad message to multiple of 16 characters
    let msg_len = private_msg.chars().count();
    let pad_len = (16 - (msg_len % 16)) % 16;
    let pad_str: String = iter::repeat(padding_char).take(pad_len).collect();
    let padded_msg = format!("{}{}", private_msg, pad_str);
    let mut padded_bytes = padded_msg.into_bytes();

    // Encrypt each block
    let key = GenericArray::from_slice(&secret_key);
    let cipher = Aes128::new(key);

    for chunk in padded_bytes.chunks_mut(16) {
        let mut block = GenericArray::clone_from_slice(chunk);
        cipher.encrypt_block(&mut block);
        chunk.copy_from_slice(&block);
    }

    Ok(b64encode(&padded_bytes))
}

fn decrypt_message(
    encoded_encrypted_msg: &str,
    encoded_secret_key: &str,
    padding_char: char,
) -> Result<String, Box<dyn Error>> {
    let encrypted_bytes = b64decode(encoded_encrypted_msg)?;
    let secret_key = b64decode(encoded_secret_key)?;
    if secret_key.len() != 16 {
        return Err("Key must be 16 bytes".into());
    }

    // Decrypt each block
    let key = GenericArray::from_slice(&secret_key);
    let cipher = Aes128::new(key);

    let mut decrypted_bytes = encrypted_bytes.clone();

    for chunk in decrypted_bytes.chunks_mut(16) {
        let mut block = GenericArray::clone_from_slice(chunk);
        cipher.decrypt_block(&mut block);
        chunk.copy_from_slice(&block);
    }

    // Convert to string and unpad
    let decrypted_str = String::from_utf8(decrypted_bytes)?;
    let unpadded_str = decrypted_str.trim_end_matches(padding_char).to_string();

    Ok(unpadded_str)
}

fn main() {
    let private_msg = "\n My test string\n";
    let padding_char = '{';

    let secret_key = generate_secret_key_for_aes_cipher();
    let encrypted_msg = encrypt_message(private_msg, &secret_key, padding_char).expect("Encryption failed");
    let decrypted_msg = decrypt_message(&encrypted_msg, &secret_key, padding_char).expect("Decryption failed");

    println!("   Secret Key: {} - ({})", secret_key, secret_key.len());
    println!("Encrypted Msg: {} - ({})", encrypted_msg, encrypted_msg.len());
    println!("Decrypted Msg: {} - ({})", decrypted_msg, decrypted_msg.len());
}