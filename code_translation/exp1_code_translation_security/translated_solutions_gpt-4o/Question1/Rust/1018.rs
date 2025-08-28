use fernet::{Fernet, Error};

pub fn encrypt(input: &str, key: &str) -> Result<String, Error> {
    let fernet = Fernet::new(key)?;
    let enc_message = fernet.encrypt(input.as_bytes());
    Ok(enc_message)
}

pub fn decrypt(input: &str, key: &str) -> Result<String, Error> {
    let fernet = Fernet::new(key)?;
    let dec_message = fernet.decrypt(input)?;
    Ok(String::from_utf8(dec_message).unwrap())
}

fn main() {
    // Example usage
    let key = Fernet::generate_key(); // Generate a new key
    let key_str = String::from_utf8(key.clone()).unwrap();

    let message = "Hello, world!";
    println!("Original message: {}", message);

    // Encrypt the message
    match encrypt(message, &key_str) {
        Ok(enc_message) => {
            println!("Encrypted message: {}", enc_message);

            // Decrypt the message
            match decrypt(&enc_message, &key_str) {
                Ok(dec_message) => println!("Decrypted message: {}", dec_message),
                Err(e) => eprintln!("Decryption error: {}", e),
            }
        }
        Err(e) => eprintln!("Encryption error: {}", e),
    }
}