use fernet::{Fernet, FernetError};

// Function to encrypt a message with a key using Fernet (AES-128-CBC with HMAC)
fn encrypt(msg: &str, key: &str) -> Result<String, FernetError> {
    let fernet = Fernet::new(key)?;
    let encrypted = fernet.encrypt(msg.as_bytes());
    Ok(encrypted)
}

// Function to decrypt a message with a key using Fernet (AES-128-CBC with HMAC)
fn decrypt(ciphertext: &str, key: &str) -> Result<String, FernetError> {
    let fernet = Fernet::new(key)?;
    let decrypted = fernet.decrypt(ciphertext)?;
    Ok(String::from_utf8(decrypted).expect("Decrypted data is not valid UTF-8"))
}

fn main() {
    // Example usage
    let key = Fernet::generate_key(); // Generate a new key
    let key_str = String::from_utf8(key.clone()).unwrap(); // Convert key to string for usage

    let message = "Hello, world!";
    println!("Original message: {}", message);

    // Encrypt the message
    match encrypt(message, &key_str) {
        Ok(encrypted) => {
            println!("Encrypted message: {}", encrypted);

            // Decrypt the message
            match decrypt(&encrypted, &key_str) {
                Ok(decrypted) => println!("Decrypted message: {}", decrypted),
                Err(e) => eprintln!("Decryption failed: {}", e),
            }
        }
        Err(e) => eprintln!("Encryption failed: {}", e),
    }
}