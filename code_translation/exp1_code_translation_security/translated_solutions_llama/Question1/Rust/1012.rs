use fernet::Fernet;

fn main() {
    // Generate a key
    let key = Fernet::generate_key();
    println!("Generated Key: {}", key);

    let plain_text = "Hello, World!";
    let encrypted_text = encrypt_text(&key, plain_text);
    println!("Encrypted Text: {}", encrypted_text);

    let decrypted_text = decrypt_text(&key, &encrypted_text);
    println!("Decrypted Text: {}", decrypted_text);
}

/// Encrypts the given plain text using the provided key.
///
/// # Arguments
///
/// * `key` - The encryption key.
/// * `plain_text` - The text to be encrypted.
///
/// # Returns
///
/// The encrypted text as a string.
fn encrypt_text(key: &str, plain_text: &str) -> String {
    let f = Fernet::new(key).unwrap();
    let encrypted_text = f.encrypt(plain_text.as_bytes()).unwrap();
    String::from_utf8_lossy(&encrypted_text).into_owned()
}

/// Decrypts the given encrypted text using the provided key.
///
/// # Arguments
///
/// * `key` - The decryption key.
/// * `encrypted_text` - The text to be decrypted.
///
/// # Returns
///
/// The decrypted text as a string.
fn decrypt_text(key: &str, encrypted_text: &str) -> String {
    let f = Fernet::new(key).unwrap();
    let decrypted_text = f.decrypt(encrypted_text.as_bytes()).unwrap();
    String::from_utf8_lossy(&decrypted_text).into_owned()
}