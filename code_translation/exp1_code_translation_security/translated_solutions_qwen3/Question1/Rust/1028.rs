pub fn encrypt(string: &str, key: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();
    let mut encrypted = String::new();

    for (i, c) in string.chars().enumerate() {
        let key_c = key_chars[i % key_len];
        let xor = (c as u32) ^ (key_c as u32);
        encrypted.push(std::char::from_u32(xor).unwrap());
    }

    encrypted
}

pub fn decrypt(string: &str, key: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();
    let mut decrypted = String::new();

    for (i, c) in string.chars().enumerate() {
        let key_c = key_chars[i % key_len];
        let xor = (c as u32) ^ (key_c as u32);
        decrypted.push(std::char::from_u32(xor).unwrap());
    }

    decrypted
}