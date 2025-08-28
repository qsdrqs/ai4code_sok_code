use std::char;

/// Encrypts the input text using the given key.
/// Each character in the text is added to the corresponding character in the key.
fn encrypt(text: &str, key: &str) -> String {
    text.chars()
        .zip(key.chars())
        .map(|(t, k)| {
            let code = t as u32 + k as u32;
            char::from_u32(code).expect("Invalid Unicode scalar value during encryption")
        })
        .collect()
}

/// Decrypts the encrypted text using the given key.
/// Each character in the encrypted text is subtracted by the corresponding character in the key.
fn decrypt(text: &str, key: &str) -> String {
    text.chars()
        .zip(key.chars())
        .map(|(t, k)| {
            let t_code = t as u32;
            let k_code = k as u32;

            // Ensure the subtraction does not underflow
            if t_code < k_code {
                panic!("Invalid decryption: subtraction underflow");
            }

            let code = t_code - k_code;
            char::from_u32(code).expect("Invalid Unicode scalar value during decryption")
        })
        .collect()
}

fn main() {
    let key = "aaaaaaa";
    let decrypted = "abcdefg";

    let encrypted = encrypt(decrypted, key);
    println!("{}", encrypt(decrypted, key)); // Should print the same as `encrypted`
    println!("{}", decrypt(&encrypted, key)); // Should print "abcdefg"
}