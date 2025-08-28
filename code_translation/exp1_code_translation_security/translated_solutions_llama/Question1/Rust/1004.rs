/// Function to encrypt a message using XOR encryption.
///
/// # Arguments
///
/// * `message`: The message to be encrypted.
/// * `key`: The encryption key.
///
/// # Returns
///
/// The encrypted message.
fn encrypt(message: &str, key: &str) -> String {
    let mut encrypted = String::new();
    for (i, c) in message.chars().enumerate() {
        let key_char = key.chars().nth(i % key.len()).unwrap();
        let encrypted_char = (c as u8 ^ key_char as u8) as char;
        encrypted.push(encrypted_char);
    }
    encrypted
}

/// Function to decrypt an encrypted message using XOR encryption.
///
/// # Arguments
///
/// * `encrypted`: The encrypted message.
/// * `key`: The decryption key.
///
/// # Returns
///
/// The decrypted message.
fn decrypt(encrypted: &str, key: &str) -> String {
    encrypt(encrypted, key)
}

fn main() {
    let message = "Hello world";
    let key = "key";
    let encrypted = encrypt(message, key);
    let decrypted = decrypt(&encrypted, key);
    println!("{}", decrypted);
}