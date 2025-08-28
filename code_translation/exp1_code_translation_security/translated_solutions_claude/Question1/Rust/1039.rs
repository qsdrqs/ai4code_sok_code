use fernet::Fernet;
use std::error::Error;

fn encrypt(message: &str, key: &str) -> Result<String, Box<dyn Error>> {
    let fernet = Fernet::new(key)?;
    let encrypted = fernet.encrypt(message.as_bytes());
    Ok(encrypted)
}

fn decrypt(message: &str, key: &str) -> Result<String, Box<dyn Error>> {
    let fernet = Fernet::new(key)?;
    let decrypted = fernet.decrypt(message)?;
    let decrypted_string = String::from_utf8(decrypted)?;
    Ok(decrypted_string)
}

// Example usage
fn main() -> Result<(), Box<dyn Error>> {
    // Generate a key (in practice, you'd store this securely)
    let key = Fernet::generate_key();
    
    let message = "Hello, World!";
    
    // Encrypt
    let encrypted = encrypt(message, &key)?;
    println!("Encrypted: {}", encrypted);
    
    // Decrypt
    let decrypted = decrypt(&encrypted, &key)?;
    println!("Decrypted: {}", decrypted);
    
    Ok(())
}