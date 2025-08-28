use std::char;

fn encrypt(key: &str, plaintext: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();
    
    plaintext.chars().enumerate().map(|(i, c)| {
        let key_c = key_chars[i % key_len];
        let cipher_val = (c as u32 + key_c as u32) % 256;
        char::from_u32(cipher_val).unwrap()
    }).collect()
}

fn decrypt(key: &str, ciphertext: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();
    
    ciphertext.chars().enumerate().map(|(i, c)| {
        let key_c = key_chars[i % key_len];
        let plain_val = (256u32 + c as u32 - key_c as u32) % 256;
        char::from_u32(plain_val).unwrap()
    }).collect()
}

fn main() {
    let encrypted = encrypt("123", "helloworld");
    let decrypted = decrypt("123", &encrypted);
    println!("{}", decrypted);
}