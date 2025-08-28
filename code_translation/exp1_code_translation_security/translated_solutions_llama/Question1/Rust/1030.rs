use aes_gcm::{Aes256Gcm, Key, Nonce};
use base64;
use rand::Rng;
use std::convert::TryInto;

// Function to generate a secret key for AES cipher
fn generate_secret_key_for_aes_cipher() -> String {
    // AES key length must be either 16, 24, or 32 bytes long
    let aes_key_length = 32; // use larger value in production
    // generate a random secret key with the decided key length
    // this secret key will be used to create AES cipher for encryption/decryption
    let mut rng = rand::thread_rng();
    let secret_key: [u8; 32] = rng.gen();
    let secret_key = &secret_key[..aes_key_length];
    // encode this secret key for storing safely in database
    base64::encode(secret_key)
}

// Function 1: Encrypt a message
fn encrypt_message(private_msg: &str, encoded_secret_key: &str, padding_character: char) -> String {
    // decode the encoded secret key
    let secret_key = base64::decode(encoded_secret_key).unwrap();
    let key = Key::from_slice(&secret_key);
    let cipher = Aes256Gcm::new(key);
    let nonce = [0u8; 12]; // Use a random nonce in production

    // pad the private_msg
    // because AES encryption requires the length of the msg to be a multiple of 16
    let padding_length = (16 - private_msg.len() % 16) % 16;
    let padded_private_msg = format!("{}{}", private_msg, padding_character.to_string().repeat(padding_length));

    // use the cipher to encrypt the padded message
    let encrypted_msg = cipher.encrypt(&nonce, padded_private_msg.as_bytes()).unwrap();

    // encode the encrypted msg for storing safely in the database
    base64::encode(&encrypted_msg)
}

// Function 2: Decrypt a message
fn decrypt_message(encoded_encrypted_msg: &str, encoded_secret_key: &str, padding_character: char) -> String {
    // decode the encoded encrypted message and encoded secret key
    let secret_key = base64::decode(encoded_secret_key).unwrap();
    let key = Key::from_slice(&secret_key);
    let encrypted_msg = base64::decode(encoded_encrypted_msg).unwrap();
    let cipher = Aes256Gcm::new(key);
    let nonce = [0u8; 12]; // Use a random nonce in production

    // use the cipher to decrypt the encrypted message
    let decrypted_msg = cipher.decrypt(&nonce, &encrypted_msg).unwrap();

    // convert decrypted message into a string
    let decrypted_msg = String::from_utf8(decrypted_msg).unwrap();

    // unpad the encrypted message
    let unpadded_private_msg = decrypted_msg.trim_end_matches(padding_character);

    // return a decrypted original private message
    unpadded_private_msg.to_string()
}

fn main() {
    let private_msg = "
 My test string
";
    let padding_character = '{';

    let secret_key = generate_secret_key_for_aes_cipher();
    let encrypted_msg = encrypt_message(private_msg, &secret_key, padding_character);
    let decrypted_msg = decrypt_message(&encrypted_msg, &secret_key, padding_character);

    println!("   Secret Key: {} - ({})", secret_key, secret_key.len());
    println!("Encrypted Msg: {} - ({})", encrypted_msg, encrypted_msg.len());
    println!("Decrypted Msg: {} - ({})", decrypted_msg, decrypted_msg.len());
}