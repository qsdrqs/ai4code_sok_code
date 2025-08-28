// encrypt a string with a given key
fn encrypt(text: &str, key: &str) -> String {
    assert!(!key.is_empty(), "key must not be empty");

    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();

    text.chars()
        .enumerate()
        .map(|(i, ch)| {
            let p = ch as u32;
            let k = key_chars[i % key_len] as u32;
            std::char::from_u32(p ^ k).expect("generated an invalid Unicode scalar value")
        })
        .collect()
}

// decrypt a string with a given key
fn decrypt(text: &str, key: &str) -> String {
    encrypt(text, key)
}

fn main() {
    let original = "sadasdasdsa";
    let key = "1234";

    let encrypted = encrypt(original, key);
    let decrypted = decrypt(&encrypted, key);

    assert_eq!(decrypted, original);
    println!("original : {}", original);
    println!("encrypted: {}", encrypted);
    println!("decrypted: {}", decrypted);
}