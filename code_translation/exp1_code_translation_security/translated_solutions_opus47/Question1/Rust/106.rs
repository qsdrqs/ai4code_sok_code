fn encrypt(plaintext: &str, key: &str) -> String {
    let mut ciphertext = String::new();
    let plaintext_bytes = plaintext.as_bytes();
    let key_bytes = key.as_bytes();
    for i in 0..plaintext_bytes.len() {
        let p = plaintext_bytes[i] as i32;
        let k = key_bytes[i % key_bytes.len()] as i32;
        let a = b'a' as i32;
        let c = ((p + k - 2 * a) % 26 + a) as u8;
        ciphertext.push(c as char);
    }
    ciphertext
}

fn decrypt(ciphertext: &str, key: &str) -> String {
    let mut plaintext = String::new();
    let ciphertext_bytes = ciphertext.as_bytes();
    let key_bytes = key.as_bytes();
    for i in 0..ciphertext_bytes.len() {
        let c = ciphertext_bytes[i] as i32;
        let k = key_bytes[i % key_bytes.len()] as i32;
        let a = b'a' as i32;
        let p = ((c - k + 26) % 26 + a) as u8;
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