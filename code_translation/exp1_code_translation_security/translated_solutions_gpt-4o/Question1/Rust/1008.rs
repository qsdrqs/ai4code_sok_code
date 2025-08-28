fn encrypt(key: &str, plaintext: &str) -> String {
    let mut ciphertext = String::new();
    for (i, plain_c) in plaintext.chars().enumerate() {
        let key_c = key.chars().nth(i % key.len()).unwrap();
        let cipher_c = ((plain_c as u8 + key_c as u8) % 256) as u8 as char;
        ciphertext.push(cipher_c);
    }
    ciphertext
}

fn decrypt(key: &str, ciphertext: &str) -> String {
    let mut plaintext = String::new();
    for (i, cipher_c) in ciphertext.chars().enumerate() {
        let key_c = key.chars().nth(i % key.len()).unwrap();
        let plain_c = ((256 + cipher_c as u8 - key_c as u8) % 256) as u8 as char;
        plaintext.push(plain_c);
    }
    plaintext
}

fn main() {
    let key = "123";
    let plaintext = "helloworld";
    let encrypted = encrypt(key, plaintext);
    let decrypted = decrypt(key, &encrypted);
    println!("{}", decrypted);
}