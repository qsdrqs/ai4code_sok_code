use fernet::Fernet;

fn encrypt(message: &str, key: &str) -> String {
    let fernet = Fernet::new(key).unwrap();
    fernet.encrypt(message.as_bytes())
}

fn decrypt(message: &str, key: &str) -> Vec<u8> {
    let fernet = Fernet::new(key).unwrap();
    fernet.decrypt(message).unwrap()
}

fn main() {
    // Example usage
    let key = Fernet::generate_key();
    let original = "hello world";

    let encrypted = encrypt(original, &key);
    println!("Encrypted: {}", encrypted);

    let decrypted = decrypt(&encrypted, &key);
    println!("Decrypted: {}", String::from_utf8(decrypted).unwrap());
}