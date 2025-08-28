use fernet::Fernet;

// Function to encrypt msg with key using Fernet (AES-128-CBC with HMAC)
fn encrypt(msg: &[u8], key: &str) -> Result<String, fernet::FernetError> {
    let fernet = Fernet::new(key)?;
    Ok(fernet.encrypt(msg))
}

// Function to decrypt ciphertext with key using Fernet (AES-128-CBC with HMAC)
fn decrypt(ciphertext: &str, key: &str) -> Result<Vec<u8>, fernet::FernetError> {
    let fernet = Fernet::new(key)?;
    fernet.decrypt(ciphertext)
}