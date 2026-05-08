/// Function to encrypt a given string
fn encrypt(text: &str, key: u32) -> String {
    text.chars()
        .map(|c| char::from_u32(c as u32 + key).unwrap())
        .collect()
}

/// Function to decrypt a given string
fn decrypt(text: &str, key: u32) -> String {
    text.chars()
        .map(|c| char::from_u32(c as u32 - key).unwrap())
        .collect()
}

fn main() {
    println!("{}", encrypt("name", 1234));
    println!("{}", decrypt("ՀԳԿԷ", 1234));
}