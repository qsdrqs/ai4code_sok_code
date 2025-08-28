/// Encrypts a string with a given key.
///
/// # Arguments
///
/// * `string`: The string to be encrypted.
/// * `key`: The key used for encryption.
///
/// # Returns
///
/// The encrypted string.
fn encrypt(string: &str, key: &str) -> String {
    let mut encrypted = String::new();
    for (i, c) in string.chars().enumerate() {
        let key_char = key.chars().nth(i % key.len()).unwrap();
        let encrypted_char = (c as u8 ^ key_char as u8) as char;
        encrypted.push(encrypted_char);
    }
    encrypted
}

/// Decrypts a string with a given key.
///
/// # Arguments
///
/// * `string`: The string to be decrypted.
/// * `key`: The key used for decryption.
///
/// # Returns
///
/// The decrypted string.
fn decrypt(string: &str, key: &str) -> String {
    encrypt(string, key)
}

fn main() {
    let original_string = "sadasdasdsa";
    let key = "1234";
    let encrypted_string = encrypt(original_string, key);
    let decrypted_string = decrypt(&encrypted_string, key);
    assert_eq!(decrypted_string, original_string);
}