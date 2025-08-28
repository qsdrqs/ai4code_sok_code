// Encrypts a string using a given key.
fn encrypt(input: &str, key: u8) -> String {
    input
        .chars()
        .map(|c| {
            // Cast `char` → `u8`, shift, then cast back to `char`.
            // `.wrapping_add` prevents a panic on byte overflow.
            let shifted = (c as u8).wrapping_add(key);
            shifted as char
        })
        .collect()
}

// Decrypts a string using a given key.
fn decrypt(input: &str, key: u8) -> String {
    input
        .chars()
        .map(|c| {
            let shifted = (c as u8).wrapping_sub(key);
            shifted as char
        })
        .collect()
}

fn main() {
    let text = "Hello World!";
    let key: u8 = 5;

    let encrypted = encrypt(text, key);
    let decrypted = decrypt(&encrypted, key);

    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {}", decrypted);
}