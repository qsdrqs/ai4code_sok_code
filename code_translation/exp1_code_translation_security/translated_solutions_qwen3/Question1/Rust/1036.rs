fn encrypt(string: &str, key: i32) -> String {
    string
        .chars()
        .map(|c| {
            let code = c as i32 + key;
            char::from_u32(code as u32).unwrap()
        })
        .collect()
}

fn decrypt(string: &str, key: i32) -> String {
    string
        .chars()
        .map(|c| {
            let code = c as i32 - key;
            char::from_u32(code as u32).unwrap()
        })
        .collect()
}

fn main() {
    let string = "Hello World!";
    let key = 5;
    let encrypted = encrypt(string, key);
    let decrypted = decrypt(&encrypted, key);
    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}