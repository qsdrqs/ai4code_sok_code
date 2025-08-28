/// encrypt with key
fn encrypt(key: &str, plaintext: &str) -> String {
    let mut ciphertext = Vec::new();
    let key_bytes: Vec<char> = key.chars().collect();
    let plaintext_chars: Vec<char> = plaintext.chars().collect();
    
    for i in 0..plaintext_chars.len() {
        let key_c = key_bytes[i % key_bytes.len()];
        let cipher_c = char::from(((plaintext_chars[i] as u8 as u16 + key_c as u8 as u16) % 256) as u8);
        ciphertext.push(cipher_c);
    }
    
    ciphertext.into_iter().collect()
}

/// decrypt with key
fn decrypt(key: &str, ciphertext: &str) -> String {
    let mut plaintext = Vec::new();
    let key_bytes: Vec<char> = key.chars().collect();
    let ciphertext_chars: Vec<char> = ciphertext.chars().collect();
    
    for i in 0..ciphertext_chars.len() {
        let key_c = key_bytes[i % key_bytes.len()];
        let plain_c = char::from(((256 + ciphertext_chars[i] as u8 as u16 - key_c as u8 as u16) % 256) as u8);
        plaintext.push(plain_c);
    }
    
    plaintext.into_iter().collect()
}

fn main() {
    let encrypted = encrypt("123", "helloworld");
    let decrypted = decrypt("123", &encrypted);
    println!("{}", decrypted);
}