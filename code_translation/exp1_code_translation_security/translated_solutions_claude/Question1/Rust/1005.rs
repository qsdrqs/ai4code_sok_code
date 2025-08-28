use fernet::Fernet;

fn encrypt(msg: &[u8], key: &str) -> Result<String, fernet::DecryptionError> {
    let fernet = Fernet::new(key)?;
    Ok(fernet.encrypt(msg))
}

fn decrypt(msg: &str, key: &str) -> Result<Vec<u8>, fernet::DecryptionError> {
    let fernet = Fernet::new(key)?;
    fernet.decrypt(msg)
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    let key = Fernet::generate_key();
    let msg = b"a much longer message with punctuation!";
    
    let e = encrypt(msg, &key)?;
    let d = decrypt(&e, &key)?;
    
    println!("{}", e);
    println!("{}", String::from_utf8_lossy(&d));
    
    Ok(())
}