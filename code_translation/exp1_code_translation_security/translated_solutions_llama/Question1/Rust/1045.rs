/// Function to encrypt a given string
fn encrypt(text: &str, key: u8) -> String {
    text.chars().map(|c| {
        let shifted_char = ((c as u8 + key) as u8);
        shifted_char as u8 as char
    }).collect()
}

/// Function to decrypt a given string
fn decrypt(text: &str, key: u8) -> String {
    text.chars().map(|c| {
        let shifted_char = ((c as u8 - key) as u8);
        shifted_char as u8 as char
    }).collect()
}

fn main() {
    println!("{}", encrypt("name", 1234));
    println!("{}", decrypt("ՀԳԿԷ", 1234));
}