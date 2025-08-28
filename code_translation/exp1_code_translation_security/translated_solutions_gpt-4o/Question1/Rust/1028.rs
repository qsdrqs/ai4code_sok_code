fn encrypt(input: &str, key: &str) -> String {
    let mut encrypted_string = String::new();
    for (i, c) in input.chars().enumerate() {
        let key_char = key.chars().nth(i % key.len()).unwrap();
        let encrypted_char = (c as u8) ^ (key_char as u8);
        encrypted_string.push(encrypted_char as char);
    }
    encrypted_string
}

fn decrypt(input: &str, key: &str) -> String {
    let mut decrypted_string = String::new();
    for (i, c) in input.chars().enumerate() {
        let key_char = key.chars().nth(i % key.len()).unwrap();
        let decrypted_char = (c as u8) ^ (key_char as u8);
        decrypted_string.push(decrypted_char as char);
    }
    decrypted_string
}

fn main() {
    let original_text = "Hello, World!";
    let key = "key";

    let encrypted_text = encrypt(original_text, key);
    println!("Encrypted: {}", encrypted_text);

    let decrypted_text = decrypt(&encrypted_text, key);
    println!("Decrypted: {}", decrypted_text);
}