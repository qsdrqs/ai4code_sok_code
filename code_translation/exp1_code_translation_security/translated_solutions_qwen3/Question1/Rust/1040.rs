fn encrypt(string: &str, key: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();

    string
        .chars()
        .enumerate()
        .map(|(i, c)| {
            let key_c = key_chars[i % key_len];
            let encrypted = c as u32 ^ key_c as u32;
            std::char::from_u32(encrypted).unwrap()
        })
        .collect()
}

fn decrypt(string: &str, key: &str) -> String {
    encrypt(string, key)
}

fn main() {
    let original = "sadasdasdsa";
    let key = "1234";

    let encrypted = encrypt(original, key);
    let decrypted = decrypt(&encrypted, key);

    assert_eq!(decrypted, original);
}