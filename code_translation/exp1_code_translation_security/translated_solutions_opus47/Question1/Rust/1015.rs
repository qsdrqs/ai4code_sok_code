use fernet::Fernet;

fn encrypt(data: &[u8], key: &str) -> String {
    let fernet = Fernet::new(key).expect("Invalid Fernet key");
    fernet.encrypt(data)
}

fn decrypt(data: &str, key: &str) -> Vec<u8> {
    let fernet = Fernet::new(key).expect("Invalid Fernet key");
    // Fixed the bug from the original Python (which referenced an undefined `decrypted`)
    fernet.decrypt(data).expect("Failed to decrypt")
}

fn main() {
    let key = Fernet::generate_key();
    // Fernet operates on bytes, so we pass a byte string literal
    let _ = encrypt(b"/safedir/test.txt", &key);
}