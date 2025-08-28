use std::char;

fn encrypt(text: &str, key: i32) -> String {
    text.chars()
        .map(|c| {
            let shifted = (c as i32 + key) as u32;
            char::from_u32(shifted).unwrap()
        })
        .collect()
}

fn decrypt(text: &str, key: i32) -> String {
    text.chars()
        .map(|c| {
            let shifted = (c as i32 - key) as u32;
            char::from_u32(shifted).unwrap()
        })
        .collect()
}

fn main() {
    let string = "Hello World!";
    let key = 5;
    let encrypted = encrypt(string, key);
    let decrypted = decrypt(&encrypted, key);
    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}