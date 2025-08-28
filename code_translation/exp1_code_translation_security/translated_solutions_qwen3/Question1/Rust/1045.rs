use std::char;

fn encrypt(text: &str, key: i32) -> String {
    text.chars()
        .map(|c| {
            let new_code = c as i32 + key;
            char::from_u32(new_code as u32).unwrap()
        })
        .collect()
}

fn decrypt(text: &str, key: i32) -> String {
    text.chars()
        .map(|c| {
            let new_code = c as i32 - key;
            char::from_u32(new_code as u32).unwrap()
        })
        .collect()
}

fn main() {
    println!("{}", encrypt("name", 1234));
    println!("{}", decrypt("ՀԳԿԷ", 1234));
}