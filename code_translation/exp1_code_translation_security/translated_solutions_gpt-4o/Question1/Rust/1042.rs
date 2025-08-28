fn encrypt(key: i32, string: &str) -> Option<String> {
    if key < 0 || string.is_empty() {
        return None;
    }

    let mut key = key;
    if key > 26 {
        key %= 26;
    }

    let mut new_string = String::new();
    for c in string.chars() {
        if c.is_alphabetic() {
            if c.is_lowercase() {
                new_string.push(((c as u8 - b'a' + key as u8) % 26 + b'a') as char);
            } else {
                new_string.push(((c as u8 - b'A' + key as u8) % 26 + b'A') as char);
            }
        } else {
            new_string.push(c);
        }
    }

    Some(new_string)
}

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
    let key_ascii: Vec<u8> = key.chars().map(|c| c as u8).collect();
    let ciphertext_ascii: Vec<u8> = ciphertext.chars().map(|c| c as u8).collect();
    let ciphertext_len = ciphertext_ascii.len();

    let mut plaintext = String::new();
    for i in 0..ciphertext_len {
        let value = (ciphertext_ascii[i] as i32 - key_ascii[i % key_len] as i32).rem_euclid(26);
        plaintext.push((value as u8 + b'A') as char);
    }

    Some(plaintext)
}

fn main() {
    // Example usage
    let encrypted = encrypt(3, "Hello, World!").unwrap();
    println!("Encrypted: {}", encrypted);

    let decrypted = decrypt("KHOOR", "KEY").unwrap();
    println!("Decrypted: {}", decrypted);
}