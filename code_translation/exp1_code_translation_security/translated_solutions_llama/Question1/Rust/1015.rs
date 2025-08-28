use fernet::{Fernet, FernetKey};
use std::fs::File;
use std::io::{Read, Write};

/// Encrypts data using a provided Fernet key.
///
/// # Arguments
///
/// * `data_path` - The path to the file containing the data to be encrypted.
/// * `key` - The Fernet key used for encryption.
///
/// # Returns
///
/// The path to the encrypted file.
fn encrypt(data_path: &str, key: &FernetKey) -> String {
    let mut file = File::open(data_path).expect("Failed to open file");
    let mut data = Vec::new();
    file.read_to_end(&mut data).expect("Failed to read file");

    let fernet = Fernet::new(key.clone());
    let encrypted = fernet.encrypt(&data).expect("Failed to encrypt data");

    let encrypted_path = format!("{}.enc", data_path);
    let mut encrypted_file = File::create(&encrypted_path).expect("Failed to create encrypted file");
    encrypted_file.write_all(&encrypted).expect("Failed to write encrypted data");

    encrypted_path
}

/// Decrypts data using a provided Fernet key.
///
/// # Arguments
///
/// * `data_path` - The path to the file containing the encrypted data.
/// * `key` - The Fernet key used for decryption.
///
/// # Returns
///
/// The path to the decrypted file.
fn decrypt(data_path: &str, key: &FernetKey) -> String {
    let mut file = File::open(data_path).expect("Failed to open file");
    let mut data = Vec::new();
    file.read_to_end(&mut data).expect("Failed to read file");

    let fernet = Fernet::new(key.clone());
    let decrypted = fernet.decrypt(&data).expect("Failed to decrypt data");

    let decrypted_path = data_path.trim_end_matches(".enc");
    let mut decrypted_file = File::create(decrypted_path).expect("Failed to create decrypted file");
    decrypted_file.write_all(&decrypted).expect("Failed to write decrypted data");

    decrypted_path.to_string()
}

fn main() {
    let key = FernetKey::generate();
    println!("Generated Key: {}", key);

    let encrypted_path = encrypt("/safedir/test.txt", &key);
    println!("Encrypted file saved to: {}", encrypted_path);

    let decrypted_path = decrypt(&encrypted_path, &key);
    println!("Decrypted file saved to: {}", decrypted_path);
}