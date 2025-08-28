fn encrypt(key: &str, string: &str) -> String {
    let mut encrypted_text = Vec::new();
    let key_bytes: Vec<char> = key.chars().collect();
    let string_chars: Vec<char> = string.chars().collect();
    
    for i in 0..string_chars.len() {
        let key_c = key_bytes[i % key_bytes.len()];
        let encrypted_c = char::from(((string_chars[i] as u8 as u32 + key_c as u8 as u32) % 256) as u8);
        encrypted_text.push(encrypted_c);
    }
    
    encrypted_text.into_iter().collect()
}

fn decrypt(key: &str, string: &str) -> String {
    let mut decrypted_text = Vec::new();
    let key_bytes: Vec<char> = key.chars().collect();
    let string_chars: Vec<char> = string.chars().collect();
    
    for i in 0..string_chars.len() {
        let key_c = key_bytes[i % key_bytes.len()];
        let decrypted_c = char::from(((string_chars[i] as u8 as u32 + 256 - key_c as u8 as u32) % 256) as u8);
        decrypted_text.push(decrypted_c);
    }
    
    decrypted_text.into_iter().collect()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_encrypt_decrypt() {
        let key = "mykey";
        let original = "Hello World!";
        let encrypted = encrypt(key, original);
        let decrypted = decrypt(key, &encrypted);
        assert_eq!(original, decrypted);
    }
}