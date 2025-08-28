use fernet::Fernet;

/// Encrypts a message with a given key using Fernet (AES-128-CBC with HMAC).
///
/// # Arguments
///
/// * `msg`: The message to be encrypted.
/// * `key`: The Fernet key used for encryption.
///
/// # Returns
///
/// The encrypted message.
pub fn encrypt(msg: &[u8], key: &[u8]) -> Result<Vec<u8>, fernet::Error> {
    let f = Fernet::new(key)?;
    Ok(f.encrypt(msg))
}

/// Decrypts a ciphertext with a given key using Fernet (AES-128-CBC with HMAC).
///
/// # Arguments
///
/// * `ciphertext`: The ciphertext to be decrypted.
/// * `key`: The Fernet key used for decryption.
///
/// # Returns
///
/// The decrypted message.
pub fn decrypt(ciphertext: &[u8], key: &[u8]) -> Result<Vec<u8>, fernet::Error> {
    let f = Fernet::new(key)?;
    Ok(f.decrypt(ciphertext))
}

fn main() {
    // Example usage
    let key = Fernet::gen_key();
    let msg = b"Hello, World!";
    
    match encrypt(msg, &key) {
        Ok(ciphertext) => println!("Encrypted: {:?}", ciphertext),
        Err(e) => println!("Encryption error: {}", e),
    }

    match decrypt(&encrypt(msg, &key).unwrap(), &key) {
        Ok(plaintext) => println!("Decrypted: {:?}", String::from_utf8_lossy(&plaintext)),
        Err(e) => println!("Decryption error: {}", e),
    }
}