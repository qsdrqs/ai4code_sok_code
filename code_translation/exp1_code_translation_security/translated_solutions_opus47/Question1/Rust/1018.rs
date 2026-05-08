use fernet::Fernet;

fn encrypt(input: &str, key: &str) -> String {
    let fernet = Fernet::new(key).expect("Invalid key");
    fernet.encrypt(input.as_bytes())
}

fn decrypt(input: &str, key: &str) -> String {
    let fernet = Fernet::new(key).expect("Invalid key");
    let decrypted = fernet.decrypt(input).expect("Decryption failed");
    String::from_utf8(decrypted).expect("Invalid UTF-8")
}

fn main() {
    // Example usage
    let key = Fernet::generate_key();
    let message = "Hello, World!";
    
    let encrypted = encrypt(message, &key);
    let decrypted = decrypt(&encrypted, &key);
    
    println!("Original:  {}", message);
    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}