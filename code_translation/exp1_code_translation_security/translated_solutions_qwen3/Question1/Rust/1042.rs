pub fn encrypt(key: i32, string: &str) -> Option<String> {
    if key < 0 {
        return None;
    }
    if string.is_empty() {
        return None;
    }
    let adjusted_key = if key > 26 { key % 26 } else { key };
    let mut new_string = String::new();
    for c in string.chars() {
        if c.is_ascii_alphabetic() {
            if c.is_lowercase() {
                let shifted = ((c as u8 - b'a') + adjusted_key as u8) % 26;
                new_string.push((shifted + b'a') as char);
            } else {
                let shifted = ((c as u8 - b'A') + adjusted_key as u8) % 26;
                new_string.push((shifted + b'A') as char);
            }
        } else {
            new_string.push(c);
        }
    }
    Some(new_string)
}

pub fn decrypt(ciphertext: &str, key: &str) -> Option<String> {
    if ciphertext.is_empty() || key.is_empty() {
        return None;
    }
    if key.len() == 1 {
        return Some(ciphertext.to_string());
    }
    if key.len() > ciphertext.len() {
        return None;
    }
    let key_len = key.len();
    let key_ascii: Vec<u8> = key.bytes().collect();
    let ciphertext_ascii: Vec<u8> = ciphertext.bytes().collect();
    let ciphertext_len = ciphertext_ascii.len();
    let mut plaintext = String::new();
    for i in 0..ciphertext_len {
        let key_byte = key_ascii[i % key_len];
        let ciphervalue = ciphertext_ascii[i] as i32;
        let keyvalue = key_byte as i32;
        let value = (ciphervalue - keyvalue + 26) % 26;
        let char_code = (value + 65) as u8;
        plaintext.push(char_code as char);
    }
    Some(plaintext)
}