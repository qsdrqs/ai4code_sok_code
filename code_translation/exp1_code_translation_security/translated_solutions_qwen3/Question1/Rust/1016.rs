pub fn encrypt(text: &str, key: &str) -> String {
    xor_strings(text, key)
}

pub fn decrypt(text: &str, key: &str) -> String {
    xor_strings(text, key)
}

fn xor_strings(text: &str, key: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();

    let mut result = String::new();

    for (i, c) in text.chars().enumerate() {
        let key_c = key_chars[i % key_len];
        let xor = (c as u32) ^ (key_c as u32);
        let ch = std::char::from_u32(xor).unwrap();
        result.push(ch);
    }

    result
}