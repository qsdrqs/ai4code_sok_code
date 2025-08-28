/// Function to encrypt a given string
fn encrypt(text: &str, key: i32) -> String {
    text.chars()
        .map(|c| {
            let code = c as u32;
            char::from_u32(code + key as u32).unwrap_or(c)
        })
        .collect()
}

/// Function to decrypt a given string
fn decrypt(text: &str, key: i32) -> String {
    text.chars()
        .map(|c| {
            let code = c as u32;
            char::from_u32(code - key as u32).unwrap_or(c)
        })
        .collect()
}

fn main() {
    println!("{}", encrypt("name", 1234));
    println!("{}", decrypt("ՀԳԿԷ", 1234));
}