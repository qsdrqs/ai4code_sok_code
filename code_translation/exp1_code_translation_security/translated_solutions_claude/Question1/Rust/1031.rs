use fernet::Fernet;

/// Encrypt using a symmetric key
fn encrypt(fernet: &Fernet, message: &str) -> Vec<u8> {
    fernet.encrypt(message.as_bytes())
}

/// Decrypt using a symmetric key
fn decrypt(fernet: &Fernet, enc_message: &[u8]) -> Result<String, Box<dyn std::error::Error>> {
    let decrypted = fernet.decrypt(enc_message)?;
    let dec_message = String::from_utf8(decrypted)?;
    Ok(dec_message)
}

fn main() {
    let message = "the solutions are here somewhere";
    println!("Secret Message: {}", message);
    
    let key = Fernet::generate_key();
    let fernet = Fernet::new(&key).expect("Failed to create Fernet instance");
    
    let enc_message = encrypt(&fernet, message);
    println!("{:?}", enc_message);
    
    match decrypt(&fernet, &enc_message) {
        Ok(dec_message) => println!("decrypted Message: {}", dec_message),
        Err(e) => println!("Decryption failed: {}", e),
    }
}