/// Encrypts a message by appending the key after each character.
fn encrypt(msg: &str, key: &str) -> String {
    let mut result = String::new();
    for c in msg.chars() {
        result.push(c);         // Add the character
        result.push_str(key);   // Add the key after it
    }
    result
}

/// Decrypts a message by taking every other character from the encrypted string.
fn decrypt(msg: &str, _key: &str) -> String {
    let mut result = String::new();
    let mut control = 0;

    for c in msg.chars() {
        if control % 2 == 0 {
            result.push(c);  // Take every other character
        }
        control += 1;
    }

    result
}

fn main() {
    // Example usage
    let encrypted = encrypt("a", "b");
    let decrypted = decrypt(&encrypted, "b");

    println!("Encrypted: {}", encrypted);  // Output: ab
    println!("Decrypted: {}", decrypted);  // Output: a
}