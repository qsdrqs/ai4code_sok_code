/// encrypt a string with a given key
fn encrypt(string: &str, key: &str) -> String {
    let mut encrypted = String::new();
    let string_bytes = string.as_bytes();
    let key_bytes = key.as_bytes();
    
    for i in 0..string_bytes.len() {
        let encrypted_byte = string_bytes[i] ^ key_bytes[i % key_bytes.len()];
        encrypted.push(encrypted_byte as char);
    }
    encrypted
}

/// decrypt a string with a given key
fn decrypt(string: &str, key: &str) -> String {
    encrypt(string, key)
}

fn main() {
    let original = "sadasdasdsa";
    let key = "1234";
    let encrypted = encrypt(original, key);
    let decrypted = decrypt(&encrypted, key);
    
    assert_eq!(decrypted, original);
    println!("Test passed: '{}' -> encrypted -> '{}'", original, decrypted);
}

#[cfg(test)]
mod tests {
    use super::*;
    
    #[test]
    fn test_encrypt_decrypt() {
        assert_eq!(decrypt(&encrypt("sadasdasdsa", "1234"), "1234"), "sadasdasdsa");
    }
}