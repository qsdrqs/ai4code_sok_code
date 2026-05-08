use fernet::Fernet;
use once_cell::sync::Lazy;

// Module-level key, generated once (equivalent to Python's `key = Fernet.generate_key()`)
static KEY: Lazy<String> = Lazy::new(Fernet::generate_key);

fn encrypt_text(plain_text: &str) -> String {
    let f = Fernet::new(&KEY).expect("Invalid key");
    // `encrypt` already returns a String (the base64-encoded token)
    f.encrypt(plain_text.as_bytes())
}

fn decrypt_text(encrypted_text: &str) -> String {
    let f = Fernet::new(&KEY).expect("Invalid key");
    let decrypted_bytes = f
        .decrypt(encrypted_text)
        .expect("Failed to decrypt");
    String::from_utf8(decrypted_bytes).expect("Invalid UTF-8")
}

fn main() {
    let plain_text = "Hello, World!";

    let encrypted = encrypt_text(plain_text);
    println!("Encrypted: {}", encrypted);

    let decrypted = decrypt_text(&encrypted);
    println!("Decrypted: {}", decrypted);
}