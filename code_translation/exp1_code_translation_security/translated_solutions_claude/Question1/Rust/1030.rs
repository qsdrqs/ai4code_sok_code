use aes::Aes128;
use aes::cipher::{BlockEncrypt, BlockDecrypt, KeyInit, generic_array::GenericArray};
use base64::{Engine as _, engine::general_purpose};
use rand::RngCore;

fn generate_secret_key_for_aes_cipher() -> String {
    // AES key length must be either 16, 24, or 32 bytes long
    let aes_key_length = 16; // use larger value in production
    
    // generate a random secret key with the decided key length
    let mut secret_key = vec![0u8; aes_key_length];
    rand::thread_rng().fill_bytes(&mut secret_key);
    
    // encode this secret key for storing safely in database
    let encoded_secret_key = general_purpose::STANDARD.encode(&secret_key);
    encoded_secret_key
}

// Function 1
fn encrypt_message(private_msg: &str, encoded_secret_key: &str, padding_character: char) -> Result<String, Box<dyn std::error::Error>> {
    // decode the encoded secret key
    let secret_key = general_purpose::STANDARD.decode(encoded_secret_key)?;
    
    // pad the private_msg
    // because AES encryption requires the length of the msg to be a multiple of 16
    let mut padded_private_msg = private_msg.to_string();
    let padding_needed = (16 - (private_msg.len() % 16)) % 16;
    for _ in 0..padding_needed {
        padded_private_msg.push(padding_character);
    }
    
    // create AES cipher
    let cipher = Aes128::new(GenericArray::from_slice(&secret_key));
    
    // encrypt the padded message block by block
    let mut encrypted_msg = Vec::new();
    let padded_bytes = padded_private_msg.as_bytes();
    
    for chunk in padded_bytes.chunks(16) {
        let mut block = GenericArray::clone_from_slice(chunk);
        cipher.encrypt_block(&mut block);
        encrypted_msg.extend_from_slice(&block);
    }
    
    // encode the encrypted msg for storing safely in the database
    let encoded_encrypted_msg = general_purpose::STANDARD.encode(&encrypted_msg);
    Ok(encoded_encrypted_msg)
}

// Function 2
fn decrypt_message(encoded_encrypted_msg: &str, encoded_secret_key: &str, padding_character: char) -> Result<String, Box<dyn std::error::Error>> {
    // decode the encoded encrypted message and encoded secret key
    let secret_key = general_purpose::STANDARD.decode(encoded_secret_key)?;
    let encrypted_msg = general_purpose::STANDARD.decode(encoded_encrypted_msg)?;
    
    // create AES cipher
    let cipher = Aes128::new(GenericArray::from_slice(&secret_key));
    
    // decrypt the encrypted message block by block
    let mut decrypted_msg = Vec::new();
    
    for chunk in encrypted_msg.chunks(16) {
        let mut block = GenericArray::clone_from_slice(chunk);
        cipher.decrypt_block(&mut block);
        decrypted_msg.extend_from_slice(&block);
    }
    
    // convert decrypted message into a string
    let decrypted_string = String::from_utf8(decrypted_msg)?;
    
    // unpad the decrypted message
    let unpadded_private_msg = decrypted_string.trim_end_matches(padding_character);
    
    Ok(unpadded_private_msg.to_string())
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    let private_msg = "\n My test string\n";
    let padding_character = '{';
    
    let secret_key = generate_secret_key_for_aes_cipher();
    let encrypted_msg = encrypt_message(private_msg, &secret_key, padding_character)?;
    let decrypted_msg = decrypt_message(&encrypted_msg, &secret_key, padding_character)?;
    
    println!("   Secret Key: {} - ({})", secret_key, secret_key.len());
    println!("Encrypted Msg: {} - ({})", encrypted_msg, encrypted_msg.len());
    println!("Decrypted Msg: {} - ({})", decrypted_msg, decrypted_msg.len());
    
    Ok(())
}