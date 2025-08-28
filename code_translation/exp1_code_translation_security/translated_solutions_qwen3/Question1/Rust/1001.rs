pub fn encrypt(key: &str, string: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let len_key = key_chars.len();
    if len_key == 0 {
        return String::new();
    }
    let mut encrypted = String::new();
    for (i, c) in string.chars().enumerate() {
        let key_c = key_chars[i % len_key];
        let encrypted_code = (c as u32 + key_c as u32) % 256;
        encrypted.push(std::char::from_u32(encrypted_code).unwrap());
    }
    encrypted
}

pub fn decrypt(key: &str, string: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let len_key = key_chars.len();
    if len_key == 0 {
        return String::new();
    }
    let mut decrypted = String::new();
    for (i, c) in string.chars().enumerate() {
        let key_c = key_chars[i % len_key];
        let decrypted_code = (c as u32 - key_c as u32) % 256;
        decrypted.push(std::char::from_u32(decrypted_code).unwrap());
    }
    decrypted
}