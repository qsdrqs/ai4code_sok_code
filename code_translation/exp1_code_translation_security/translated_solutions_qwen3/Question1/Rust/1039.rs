use fernet::{Fernet, Key};

pub fn encrypt(message: &str, key_str: &str) -> Vec<u8> {
    let key = Key::parse(key_str).expect("Invalid Fernet key");
    let fernet = Fernet::new(&key);
    fernet.encrypt(message.as_bytes())
}

pub fn decrypt(message: &[u8], key_str: &str) -> Vec<u8> {
    let key = Key::parse(key_str).expect("Invalid Fernet key");
    let fernet = Fernet::new(&key);
    fernet.decrypt(message).expect("Decryption failed")
}