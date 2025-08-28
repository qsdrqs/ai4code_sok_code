use fernet::Fernet;
use std::fs;

fn encrypt(file_path: &str, key: &str) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    let fernet = Fernet::new(key)?;
    let data = fs::read(file_path)?;
    let encrypted = fernet.encrypt(&data);
    Ok(encrypted)
}

fn decrypt(data: &[u8], key: &str) -> Result<Vec<u8>, Box<dyn std::error::Error>> {
    let fernet = Fernet::new(key)?;
    let decrypted = fernet.decrypt(data)?;
    Ok(decrypted)
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    let key = Fernet::generate_key();
    let key_str = std::str::from_utf8(&key)?;
    encrypt("/safedir/test.txt", key_str)?;
    Ok(())
}