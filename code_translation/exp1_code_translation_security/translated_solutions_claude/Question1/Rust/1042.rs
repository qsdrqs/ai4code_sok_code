fn encrypt(key: i32, string: &str) -> Option<String> {
    // Check if key is negative
    if key < 0 {
        return None;
    }
    
    // Check if string is empty
    if string.is_empty() {
        return None;
    }
    
    // Normalize key to be within 0-25 range
    let normalized_key = if key > 26 { key % 26 } else { key };
    
    let mut new_string = String::new();
    
    for ch in string.chars() {
        if ch.is_alphabetic() {
            if ch.is_lowercase() {
                let shifted = ((ch as u8 - b'a') as i32 + normalized_key) % 26;
                new_string.push((shifted as u8 + b'a') as char);
            } else {
                let shifted = ((ch as u8 - b'A') as i32 + normalized_key) % 26;
                new_string.push((shifted as u8 + b'A') as char);
            }
        } else {
            new_string.push(ch);
        }
    }
    
    Some(new_string)
}

fn decrypt(ciphertext: &str, key: &str) -> Option<String> {
    // Check if either string is empty
    if ciphertext.is_empty() || key.is_empty() {
        return None;
    }
    
    // If key length is 1, return ciphertext as is
    if key.len() == 1 {
        return Some(ciphertext.to_string());
    }
    
    // If key is longer than ciphertext, return None
    if key.len() > ciphertext.len() {
        return None;
    }
    
    let key_len = key.len();
    let key_ascii: Vec<u8> = key.chars().map(|c| c as u8).collect();
    let ciphertext_ascii: Vec<u8> = ciphertext.chars().map(|c| c as u8).collect();
    let ciphertext_len = ciphertext_ascii.len();
    
    let mut plaintext = String::new();
    
    for i in 0..ciphertext_len {
        let value = ((ciphertext_ascii[i] as i32 - key_ascii[i % key_len] as i32) % 26 + 26) % 26;
        plaintext.push((value as u8 + b'A') as char);
    }
    
    Some(plaintext)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_encrypt() {
        assert_eq!(encrypt(1, "abc"), Some("bcd".to_string()));
        assert_eq!(encrypt(1, "ABC"), Some("BCD".to_string()));
        assert_eq!(encrypt(1, "a1b2c3"), Some("b1c2d3".to_string()));
        assert_eq!(encrypt(-1, "abc"), None);
        assert_eq!(encrypt(1, ""), None);
        assert_eq!(encrypt(27, "a"), Some("b".to_string())); // 27 % 26 = 1
    }

    #[test]
    fn test_decrypt() {
        assert_eq!(decrypt("BCD", "A"), Some("BCD".to_string()));
        assert_eq!(decrypt("", "key"), None);
        assert_eq!(decrypt("text", ""), None);
        assert_eq!(decrypt("AB", "ABC"), None); // key longer than ciphertext
    }
}