use fernet::Fernet;

/// Encrypt `msg` with the supplied Fernet `key`.
fn encrypt(msg: &[u8], key: &str) -> String {
    let f = Fernet::new(key);
    f.encrypt(msg)
}

/// Decrypt `token` with the supplied Fernet `key`.
fn decrypt(token: &str, key: &str) -> Vec<u8> {
    let f = Fernet::new(key);
    // `decrypt` returns a `Result`; unwrap (or handle) for simplicity here
    f.decrypt(token).expect("decryption failed")
}

fn main() {
    // Generate a fresh, url-safe base64 key (compatible with Python’s cryptography)
    let key = Fernet::generate_key();

    let msg = b"a much longer message with punctuation!";
    let encrypted = encrypt(msg, &key);
    let decrypted = decrypt(&encrypted, &key);

    println!("{}", encrypted);                            // ciphertext token
    println!("{}", String::from_utf8(decrypted).unwrap()); // original message
}