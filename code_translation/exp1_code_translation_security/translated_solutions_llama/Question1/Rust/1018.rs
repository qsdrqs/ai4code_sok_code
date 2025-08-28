use fernet::Fernet;

/// Encrypts the given input using the provided key.
///
/// # Arguments
///
/// * `input`: The string to be encrypted.
/// * `key`: The Fernet key used for encryption.
///
/// # Returns
///
/// The encrypted message as a `Vec<u8>`.
pub fn encrypt(input: &str, key: &[u8]) -> Vec<u8> {
    let fernet = Fernet::new(key).unwrap();
    fernet.encrypt(input.as_bytes()).unwrap()
}

/// Decrypts the given input using the provided key.
///
/// # Arguments
///
/// * `input`: The encrypted message to be decrypted.
/// * `key`: The Fernet key used for decryption.
///
/// # Returns
///
/// The decrypted message as a `String`.
pub fn decrypt(input: &[u8], key: &[u8]) -> String {
    let fernet = Fernet::new(key).unwrap();
    String::from_utf8(fernet.decrypt(input).unwrap()).unwrap()
}

fn main() {
    // Example usage
    let key = Fernet::gen_key();
    let input = "Hello, World!";
    let encrypted = encrypt(input, &key);
    println!("Encrypted: {:?}", encrypted);
    let decrypted = decrypt(&encrypted, &key);
    println!("Decrypted: {}", decrypted);
}