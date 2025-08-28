/// Encrypts a given string using a given symmetric key.
fn encrypt(string: &str, key: &str) -> String {
    let mut encrypted_string = String::new();
    let string_bytes = string.as_bytes();
    let key_bytes = key.as_bytes();
    
    for i in 0..string_bytes.len() {
        let encrypted_char = string_bytes[i] ^ key_bytes[i % key_bytes.len()];
        encrypted_string.push(encrypted_char as char);
    }
    
    encrypted_string
}

/// Decrypts a given string using a given symmetric key.
fn decrypt(string: &str, key: &str) -> String {
    let mut decrypted_string = String::new();
    let string_bytes = string.as_bytes();
    let key_bytes = key.as_bytes();
    
    for i in 0..string_bytes.len() {
        let decrypted_char = string_bytes[i] ^ key_bytes[i % key_bytes.len()];
        decrypted_string.push(decrypted_char as char);
    }
    
    decrypted_string
}