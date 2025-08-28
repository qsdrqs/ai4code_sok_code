fn encrypt(plaintext: &str, key: &str) -> String {
    let mut ciphertext = String::new();
    for (i, c) in plaintext.chars().enumerate() {
        let key_char = key.chars().nth(i % key.len()).unwrap();
        let encrypted_char = (((c as u8 - b'a') + (key_char as u8 - b'a')) % 26 + b'a') as u8;
        ciphertext.push(encrypted_char as char);
    }
    ciphertext
}

fn decrypt(ciphertext: &str, key: &str) -> String {
    let mut plaintext = String::new();
    for (i, c) in ciphertext.chars().enumerate() {
        let key_char = key.chars().nth(i % key.len()).unwrap();
        let decrypted_char = (((c as u8 - b'a' - (key_char as u8 - b'a')) + 26) % 26 + b'a') as u8;
        plaintext.push(decrypted_char as char);
    }
    plaintext
}

fn main() {
    let plaintext = "helloworld";
    let key = "python";
    let ciphertext = encrypt(plaintext, key);
    println!("Ciphertext: {}", ciphertext);
    let decrypted = decrypt(&ciphertext, key);
    println!("{}", decrypted);
    assert_eq!(plaintext, decrypted);
}