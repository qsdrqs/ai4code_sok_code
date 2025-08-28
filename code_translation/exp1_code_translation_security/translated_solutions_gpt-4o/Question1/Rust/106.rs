fn encrypt(plaintext: &str, key: &str) -> String {
    let mut ciphertext = String::new();
    let key_len = key.len();
    for (i, ch) in plaintext.chars().enumerate() {
        let p = ch as u8 - b'a';
        let k = key.chars().nth(i % key_len).unwrap() as u8 - b'a';
        let c = (p + k) % 26 + b'a';
        ciphertext.push(c as char);
    }
    ciphertext
}

fn decrypt(ciphertext: &str, key: &str) -> String {
    let mut plaintext = String::new();
    let key_len = key.len();
    for (i, ch) in ciphertext.chars().enumerate() {
        let c = ch as u8 - b'a';
        let k = key.chars().nth(i % key_len).unwrap() as u8 - b'a';
        let p = (c + 26 - k) % 26 + b'a';
        plaintext.push(p as char);
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