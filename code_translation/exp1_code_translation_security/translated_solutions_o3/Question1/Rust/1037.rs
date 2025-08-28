use fernet::{Fernet, DecryptionError};

/// Encrypt `input` with the given base-64 Fernet `key`.
fn encrypt(input: &[u8], key: &str) -> String {
    let f = Fernet::new(key).expect("invalid key");
    f.encrypt(input)
}

/// Decrypt `token` with the given base-64 Fernet `key`.
fn decrypt(token: &str, key: &str) -> Result<Vec<u8>, DecryptionError> {
    let f = Fernet::new(key).expect("invalid key");
    f.decrypt(token)
}

/// Helper that prints a byte slice exactly like Python’s `print(b"...")`.
fn print_as_py_bytes(bytes: &[u8]) {
    let as_str = std::str::from_utf8(bytes).unwrap();
    println!("b\"{}\"", as_str);
}

fn main() {
    // Generate a fresh key (base-64 encoded, just like Python’s Fernet)
    let key = Fernet::generate_key();

    let input = b"super secret";

    let encrypted_input = encrypt(input, &key);
    let decrypted_input = decrypt(&encrypted_input, &key).expect("decryption failed");

    // Mimic Python’s byte-string printing
    print_as_py_bytes(input);
    print_as_py_bytes(&decrypted_input);
}