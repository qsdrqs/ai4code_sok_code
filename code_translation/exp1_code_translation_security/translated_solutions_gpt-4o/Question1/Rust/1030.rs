extern crate aes;
extern crate base64;
extern crate rand;

use aes::cipher::{BlockEncrypt, BlockDecrypt, KeyInit, generic_array::GenericArray};
use aes::Aes128;
use base64::{encode, decode};
use rand::Rng;
use std::str;

fn generate_secret_key_for_aes_cipher() -> String {
    // AES key length must be either 16, 24, or 32 bytes long
    let aes_key_length = 16; // use larger value in production
    // generate a random secret key with the decided key length
    let secret_key: Vec<u8> = rand::thread_rng().gen_iter().take(aes_key_length).collect();
    // encode this secret key for storing safely in database
    encode(&secret_key)
}

fn encrypt_message(private_msg: &str, encoded_secret_key: &str, padding_character: char) -> String {
    // decode the encoded secret key
    let secret_key = decode(encoded_secret_key).expect("Decoding failed");
    let key = GenericArray::from_slice(&secret_key);
    let cipher = Aes128::new(&key);

    // pad the private_msg
    let padding_length = (16 - private_msg.len() % 16) % 16;
    let padded_private_msg = format!("{}{}", private_msg, padding_character.to_string().repeat(padding_length));

    // use the cipher to encrypt the padded message
    let mut buffer = GenericArray::clone_from_slice(padded_private_msg.as_bytes());
    cipher.encrypt_block(&mut buffer);

    // encode the encrypted msg for storing safely in the database
    encode(&buffer)
}

fn decrypt_message(encoded_encrypted_msg: &str, encoded_secret_key: &str, padding_character: char) -> String {
    // decode the encoded encrypted message and encoded secret key
    let secret_key = decode(encoded_secret_key).expect("Decoding failed");
    let encrypted_msg = decode(encoded_encrypted_msg).expect("Decoding failed");
    let key = GenericArray::from_slice(&secret_key);
    let cipher = Aes128::new(&key);

    // use the cipher to decrypt the encrypted message
    let mut buffer = GenericArray::clone_from_slice(&encrypted_msg);
    cipher.decrypt_block(&mut buffer);

    // convert decrypted message into a string
    let decrypted_msg = str::from_utf8(&buffer).expect("Invalid UTF-8");

    // unpad the encrypted message
    decrypted_msg.trim_end_matches(padding_character).to_string()
}

fn main() {
    let private_msg = " My test string\n";
    let padding_character = '{';

    let secret_key = generate_secret_key_for_aes_cipher();
    let encrypted_msg = encrypt_message(private_msg, &secret_key, padding_character);
    let decrypted_msg = decrypt_message(&encrypted_msg, &secret_key, padding_character);

    println!("   Secret Key: {} - ({})", secret_key, secret_key.len());
    println!("Encrypted Msg: {} - ({})", encrypted_msg, encrypted_msg.len());
    println!("Decrypted Msg: {} - ({})", decrypted_msg, decrypted_msg.len());
}