use std::string::ToString;

// Function to encrypt a string by shifting each character by a given key
fn encrypt(string: &str, key: i32) -> String {
    string.chars().map(|c| ((c as i32 + key) as u8) as char).collect()
}

// Function to decrypt a string by shifting each character back by a given key
fn decrypt(string: &str, key: i32) -> String {
    string.chars().map(|c| ((c as i32 - key) as u8) as char).collect()
}

fn main() {
    let msg = encrypt("this is cool if it really works", 2);
    let res = decrypt(&msg, 2);
    println!("{}", msg);
    println!("{}", res);
}