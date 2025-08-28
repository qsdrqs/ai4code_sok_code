fn encrypt(text: &str, key: &str) -> String {
    let mut result = String::new();
    for (t, k) in text.chars().zip(key.chars()) {
        let this_char = (t as u8) + (k as u8);
        result.push(this_char as char);
    }
    result
}

fn decrypt(text: &str, key: &str) -> String {
    let mut result = String::new();
    for (t, k) in text.chars().zip(key.chars()) {
        let this_char = (t as u8) - (k as u8);
        result.push(this_char as char);
    }
    result
}

fn main() {
    let key = "aaaaaaa";
    let decrypted = "abcdefg";
    let encrypted = encrypt(decrypted, key);
    println!("{}", encrypted);
    println!("{}", decrypt(&encrypted, key));
}