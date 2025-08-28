use fernet::{Fernet, Key};

pub fn encrypt(input: &str, key: &str) -> Vec<u8> {
    let key = Key::from_base64(key).expect("Invalid key");
    let fernet = Fernet::new(&key);
    fernet.encrypt(input.as_bytes()).into_bytes()
}

pub fn decrypt(input: &[u8], key: &str) -> String {
    let key = Key::from_base64(key).expect("Invalid key");
    let fernet = Fernet::new(&key);
    let token = std::str::from_utf8(input).expect("Invalid token");
    let decrypted = fernet.decrypt(token).expect("Decryption failed");
    String::from_utf8(decrypted).expect("Invalid UTF-8 in decrypted data")
}