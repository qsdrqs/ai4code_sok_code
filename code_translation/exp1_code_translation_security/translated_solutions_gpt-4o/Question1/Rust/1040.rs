fn encrypt(string: &str, key: &str) -> String {
    let mut encrypted = String::new();
    for (i, c) in string.chars().enumerate() {
        let key_char = key.chars().nth(i % key.len()).unwrap();
        encrypted.push((c as u8 ^ key_char as u8) as char);
    }
    encrypted
}

fn decrypt(string: &str, key: &str) -> String {
    encrypt(string, key)
}

fn main() {
    let original = "sadasdasdsa";
    let key = "1234";
    let encrypted = encrypt(original, key);
    let decrypted = decrypt(&encrypted, key);
    assert_eq!(decrypted, original);
    println!("Encryption and decryption successful!");
}