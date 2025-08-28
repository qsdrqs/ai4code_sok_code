use fernet::{Fernet, Key};
use rand::RngCore;
use base64::engine::general_purpose;
use base64::Engine;

fn main() {
    // Generate a 32-byte key and encode it in URL-safe Base64
    let mut key_bytes = [0u8; 32];
    rand::thread_rng().fill_bytes(&mut key_bytes);
    let key_str = general_purpose::URL_SAFE.encode(&key_bytes);
    let ex_key = Key::parse(&key_str).expect("Failed to parse key");

    // Define encryption function
    fn encrypt(inp: &str, key: &Key) -> String {
        let fernet = Fernet::new(key);
        fernet.encrypt(inp.as_bytes())
    }

    // Define decryption function
    fn decrypt(inp: &str, key: &Key) -> String {
        let fernet = Fernet::new(key);
        let decrypted = fernet.decrypt(inp).expect("Failed to decrypt");
        String::from_utf8(decrypted).expect("Failed to convert decrypted bytes to string")
    }

    // Example usage
    let encrypted = encrypt("Hello World!", &ex_key);
    println!("Encrypted: {}", encrypted);

    let decrypted = decrypt(&encrypted, &ex_key);
    println!("Decrypted: {}", decrypted);
}