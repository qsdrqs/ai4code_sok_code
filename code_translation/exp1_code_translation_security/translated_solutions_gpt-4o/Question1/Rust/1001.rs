fn encrypt(key: &str, string: &str) -> String {
    let mut encrypted_text = String::new();
    for (i, c) in string.chars().enumerate() {
        let key_c = key.chars().nth(i % key.len()).unwrap();
        let encrypted_c = (((c as u8) + (key_c as u8)) % 256) as u8 as char;
        encrypted_text.push(encrypted_c);
    }
    encrypted_text
}

fn decrypt(key: &str, string: &str) -> String {
    let mut decrypted_text = String::new();
    for (i, c) in string.chars().enumerate() {
        let key_c = key.chars().nth(i % key.len()).unwrap();
        let decrypted_c = (((c as u8) + 256 - (key_c as u8)) % 256) as u8 as char;
        decrypted_text.push(decrypted_c);
    }
    decrypted_text
}

fn main() {
    let key = "mysecretkey";
    let text = "Hello, World!";
    
    let encrypted = encrypt(key, text);
    println!("Encrypted: {}", encrypted);
    
    let decrypted = decrypt(key, &encrypted);
    println!("Decrypted: {}", decrypted);
}