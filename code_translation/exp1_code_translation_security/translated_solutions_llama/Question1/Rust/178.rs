use std::collections::HashMap;

// Function to encrypt a string using Caesar cipher
fn encrypt(str: &str, key: usize) -> Vec<char> {
    let alpha: Vec<char> = ('a'..='z').collect();
    let mut new_string = Vec::new();

    for c in str.chars() {
        match c {
            ' ' => {
                new_string.push(' ');
                continue;
            }
            _ => {
                let alpha_index = alpha.iter().position(|&x| x == c).unwrap();
                let new_index = (alpha_index as usize + key) % 26;
                new_string.push(alpha[new_index]);
            }
        }
    }

    new_string
}

// Function to decrypt a string using Caesar cipher
fn decrypt(str: Vec<char>, key: usize) -> Vec<char> {
    let alpha: Vec<char> = ('a'..='z').collect();
    let mut new_string = Vec::new();

    for c in str {
        match c {
            ' ' => {
                new_string.push(' ');
                continue;
            }
            _ => {
                let alpha_index = alpha.iter().position(|&x| x == c).unwrap();
                let new_index = (alpha_index as usize + 26 - key) % 26;
                new_string.push(alpha[new_index]);
            }
        }
    }

    new_string
}

fn main() {
    let encrypted: Vec<char> = encrypt("hello worlz", 3);
    println!("Encrypted: {:?}", encrypted);

    let decrypted: Vec<char> = decrypt(encrypted, 3);
    println!("Decrypted: {:?}", decrypted);
}