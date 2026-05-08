fn encrypt(key: i32, string: &str) -> Option<String> {
    if key < 0 {
        return None;
    }
    if string.is_empty() {
        return None;
    }
    let key = if key > 26 { key % 26 } else { key };
    let key = key as u8;

    let mut new_string = String::new();
    for c in string.chars() {
        if c.is_ascii_alphabetic() {
            if c.is_ascii_lowercase() {
                let shifted = ((c as u8 - b'a' + key) % 26) + b'a';
                new_string.push(shifted as char);
            } else {
                let shifted = ((c as u8 - b'A' + key) % 26) + b'A';
                new_string.push(shifted as char);
            }
        } else {
            new_string.push(c);
        }
    }
    Some(new_string)
}

/// function that decrypts a given string using a symmetric key
fn decrypt(ciphertext: &str, key: &str) -> Option<String> {
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
        let c = ciphertext_ascii[i] as i32;
        let k = key_ascii[i % key_len] as i32;
        let value = (c - k).rem_euclid(26) as u8;
        plaintext.push((value + b'A') as char);
    }
    Some(plaintext)
}