// Function to encrypt a given string
fn encrypt(text: &str, key: i32) -> String {
    let mut encrypted_text = String::new();
    for c in text.chars() {
        let encrypted_char = ((c as u32) + (key as u32)) as u8 as char;
        encrypted_text.push(encrypted_char);
    }
    encrypted_text
}

// Function to decrypt a given string
fn decrypt(text: &str, key: i32) -> String {
    let mut decrypted_text = String::new();
    for c in text.chars() {
        let decrypted_char = ((c as u32) - (key as u32)) as u8 as char;
        decrypted_text.push(decrypted_char);
    }
    decrypted_text
}

fn main() {
    println!("{}", encrypt("name", 1234));
    println!("{}", decrypt("ՀԳԿԷ", 1234));
}