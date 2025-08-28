fn encrypt(string: &str, key: i32) -> String {
    let mut encrypted = String::new();
    for c in string.chars() {
        encrypted.push(((c as u8) as i32 + key) as u8 as char);
    }
    encrypted
}

fn decrypt(string: &str, key: i32) -> String {
    let mut decrypted = String::new();
    for c in string.chars() {
        decrypted.push(((c as u8) as i32 - key) as u8 as char);
    }
    decrypted
}

fn main() {
    let string = "Hello World!";
    let key = 5;
    let encrypted = encrypt(string, key);
    let decrypted = decrypt(&encrypted, key);
    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}