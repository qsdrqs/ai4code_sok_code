/// encrypt with key
fn encrypt(key: &str, plaintext: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let plaintext_chars: Vec<char> = plaintext.chars().collect();
    let mut ciphertext = String::new();
    for i in 0..plaintext_chars.len() {
        let key_c = key_chars[i % key_chars.len()];
        let cipher_c = char::from_u32((plaintext_chars[i] as u32 + key_c as u32) % 256).unwrap();
        ciphertext.push(cipher_c);
    }
    ciphertext
}

/// decrypt with key
fn decrypt(key: &str, ciphertext: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let ciphertext_chars: Vec<char> = ciphertext.chars().collect();
    let mut plaintext = String::new();
    for i in 0..ciphertext_chars.len() {
        let key_c = key_chars[i % key_chars.len()];
        let plain_c = char::from_u32((256 + ciphertext_chars[i] as u32 - key_c as u32) % 256).unwrap();
        plaintext.push(plain_c);
    }
    plaintext
}

fn main() {
    println!("{}", decrypt("123", &encrypt("123", "helloworld")));
}