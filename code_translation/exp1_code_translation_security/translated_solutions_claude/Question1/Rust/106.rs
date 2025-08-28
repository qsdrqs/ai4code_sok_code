fn encrypt(plaintext: &str, key: &str) -> String {
    let mut ciphertext = String::new();
    let plaintext_chars: Vec<char> = plaintext.chars().collect();
    let key_chars: Vec<char> = key.chars().collect();
    
    for i in 0..plaintext_chars.len() {
        let plain_char = plaintext_chars[i] as u8;
        let key_char = key_chars[i % key_chars.len()] as u8;
        let encrypted_char = ((plain_char + key_char - 2 * b'a') % 26 + b'a') as char;
        ciphertext.push(encrypted_char);
    }
    
    ciphertext
}

fn decrypt(ciphertext: &str, key: &str) -> String {
    let mut plaintext = String::new();
    let ciphertext_chars: Vec<char> = ciphertext.chars().collect();
    let key_chars: Vec<char> = key.chars().collect();
    
    for i in 0..ciphertext_chars.len() {
        let cipher_char = ciphertext_chars[i] as u8;
        let key_char = key_chars[i % key_chars.len()] as u8;
        let decrypted_char = ((cipher_char - key_char + 26) % 26 + b'a') as char;
        plaintext.push(decrypted_char);
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