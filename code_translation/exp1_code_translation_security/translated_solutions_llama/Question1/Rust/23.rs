use std::char;

fn encrypt(text: &str, key: &str) -> String {
    let mut result = String::new();
    for (i, c) in text.chars().enumerate() {
        let key_char = key.chars().nth(i % key.len()).unwrap();
        let this_char = (c as u32 + key_char as u32) as u8;
        result.push(this_char as char);
    }
    result
}

fn decrypt(text: &str, key: &str) -> String {
    let mut result = String::new();
    for (i, c) in text.chars().enumerate() {
        let key_char = key.chars().nth(i % key.len()).unwrap();
        let this_char = ((c as u32 - key_char as u32) as i32 + 256) as u8;
        result.push(this_char as char);
    }
    result
}

fn main() {
    let key = "aaaaaaa";
    let decrypted = "abcdefg";
    let encrypted = encrypt(decrypted, key);
    println!("{}", encrypt(decrypted, key));
    println!("{}", decrypt(&encrypted, key));
}