pub fn encrypt(key: &str, string: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();
    string
        .chars()
        .enumerate()
        .map(|(i, c)| {
            let key_c = key_chars[i % key_len];
            char::from_u32((c as u32 + key_c as u32) % 256).unwrap()
        })
        .collect()
}

pub fn decrypt(key: &str, string: &str) -> String {
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();
    string
        .chars()
        .enumerate()
        .map(|(i, c)| {
            let key_c = key_chars[i % key_len];
            char::from_u32((c as i32 - key_c as i32).rem_euclid(256) as u32).unwrap()
        })
        .collect()
}

fn main() {
    let key = "mykey";
    let message = "Hello, World!";

    let encrypted = encrypt(key, message);
    println!("Encrypted: {:?}", encrypted);

    let decrypted = decrypt(key, &encrypted);
    println!("Decrypted: {:?}", decrypted);
}