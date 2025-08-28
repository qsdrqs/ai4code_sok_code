// Function that encrypts a given string.
fn encrypt(text: &str, key: u32) -> String {
    text.chars()                               // iterate over all characters
        .map(|c| std::char::from_u32(c as u32 + key).unwrap())
        .collect()                             // build the resulting `String`
}

// Function that decrypts a given string.
fn decrypt(text: &str, key: u32) -> String {
    text.chars()
        .map(|c| std::char::from_u32(c as u32 - key).unwrap())
        .collect()
}

fn main() {
    println!("{}", encrypt("name", 1234));
    println!("{}", decrypt("ՀԳԿԷ", 1234));
}