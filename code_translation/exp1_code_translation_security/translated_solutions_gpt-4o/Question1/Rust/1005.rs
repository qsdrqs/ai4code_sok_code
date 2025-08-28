use fernet::{Fernet, FernetError};

fn encrypt(msg: &[u8], key: &str) -> Result<String, FernetError> {
    let fernet = Fernet::new(key)?;
    Ok(fernet.encrypt(msg))
}

fn decrypt(msg: &str, key: &str) -> Result<Vec<u8>, FernetError> {
    let fernet = Fernet::new(key)?;
    fernet.decrypt(msg)
}

fn main() {
    // Generate a new Fernet key
    let key = Fernet::generate_key();
    let key_str = String::from_utf8(key).expect("Failed to convert key to string");

    let msg = b"a much longer message with punctuation!";
    
    // Encrypt the message
    let encrypted = encrypt(msg, &key_str).expect("Encryption failed");
    
    // Decrypt the message
    let decrypted = decrypt(&encrypted, &key_str).expect("Decryption failed");
    
    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", String::from_utf8(decrypted).expect("Failed to convert decrypted message to string"));
}