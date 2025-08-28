extern crate fernet;
use fernet::{Fernet, Key};

/// Encrypts the given data using the provided base64-encoded key.
pub fn encrypt(data: &[u8], key: &str) -> String {
    let key = key.parse::<Key>().expect("Invalid key");
    let cipher = Fernet::new(&key);
    cipher.encrypt(data)
}

/// Decrypts the given base64-encoded token using the provided base64-encoded key.
pub fn decrypt(data: &str, key: &str) -> Vec<u8> {
    let key = key.parse::<Key>().expect("Invalid key");
    let cipher = Fernet::new(&key);
    cipher.decrypt(data).expect("Decryption failed")
}

fn main() {
    // Generate a new Fernet key
    let key = Key::generate();
    let key_str = key.to_string();

    // Encrypt the string "/safedir/test.txt" as bytes
    let encrypted = encrypt(b"/safedir/test.txt", &key_str);

    // Optionally, decrypt and verify
    let decrypted = decrypt(&encrypted, &key_str);
    assert_eq!(decrypted, b"/safedir/test.txt");
}