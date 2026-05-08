use fernet::Fernet;

// Function to encrypt msg with key using Fernet (AES-128-CBC with HMAC)
fn encrypt(msg: &[u8], key: &str) -> String {
    let f = Fernet::new(key).expect("Invalid key");
    f.encrypt(msg)
}

// Function to decrypt msg with key using Fernet (AES-128-CBC with HMAC)
fn decrypt(ciphertext: &str, key: &str) -> Vec<u8> {
    let f = Fernet::new(key).expect("Invalid key");
    f.decrypt(ciphertext).expect("Decryption failed")
}

fn main() {
    // Example usage
    let key = Fernet::generate_key();
    let message = b"Hello, World!";

    let ciphertext = encrypt(message, &key);
    println!("Encrypted: {}", ciphertext);

    let plaintext = decrypt(&ciphertext, &key);
    println!("Decrypted: {}", String::from_utf8_lossy(&plaintext));
}