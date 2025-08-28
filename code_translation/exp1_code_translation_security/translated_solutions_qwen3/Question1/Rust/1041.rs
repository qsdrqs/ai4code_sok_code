use std::char;

/// Encrypts a string by shifting each character's Unicode scalar value by `key`.
fn encrypt(s: &str, key: u32) -> String {
    s.chars()
        .map(|c| char::from_u32(c as u32 + key).expect("Invalid Unicode scalar"))
        .collect()
}

/// Decrypts a string by shifting each character's Unicode scalar value back by `key`.
fn decrypt(s: &str, key: u32) -> String {
    s.chars()
        .map(|c| char::from_u32(c as u32 - key).expect("Invalid Unicode scalar"))
        .collect()
}

fn main() {
    let msg = encrypt("this is cool if it really works", 2);
    let res = decrypt(&msg, 2);
    println!("Encrypted: {}", msg);
    println!("Decrypted: {}", res);
}