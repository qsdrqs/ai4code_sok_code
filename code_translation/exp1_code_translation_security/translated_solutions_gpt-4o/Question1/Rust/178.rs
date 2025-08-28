fn encrypt(input: &str, key: usize) -> String {
    let alpha: Vec<char> = "abcdefghijklmnopqrstuvwxyz".chars().collect();
    let mut new_string = String::new();

    for c in input.chars() {
        if c == ' ' {
            new_string.push(' ');
            continue;
        }

        if let Some(alpha_index) = alpha.iter().position(|&x| x == c) {
            let new_index = (alpha_index + key) % alpha.len();
            new_string.push(alpha[new_index]);
        }
    }

    new_string
}

fn decrypt(input: &str, key: usize) -> String {
    let alpha: Vec<char> = "abcdefghijklmnopqrstuvwxyz".chars().collect();
    let mut new_string = String::new();

    for c in input.chars() {
        if c == ' ' {
            new_string.push(' ');
            continue;
        }

        if let Some(alpha_index) = alpha.iter().position(|&x| x == c) {
            let new_index = (alpha_index + alpha.len() - key) % alpha.len();
            new_string.push(alpha[new_index]);
        }
    }

    new_string
}

fn main() {
    let encrypted = encrypt("hello worlz", 3);
    println!("Encrypted: {}", encrypted);

    let decrypted = decrypt(&encrypted, 3);
    println!("Decrypted: {}", decrypted);
}