// No external crates are required
fn encrypt(text: &str, key: usize) -> Vec<char> {
    // Equivalent to the Python list
    let alpha: Vec<char> = ('a'..='z').collect();
    let mut new_string: Vec<char> = Vec::new();

    for c in text.chars() {
        if c == ' ' {
            new_string.push(' ');
            continue;
        }

        // Position of the current character in the alphabet
        let alpha_index = alpha
            .iter()
            .position(|&ch| ch == c)
            .expect("character not found in alphabet");

        // Same wrap-around trick used in the Python code
        let mut idx = alpha_index;
        if idx + key > alpha.len() - 1 {
            idx = key - (alpha.len() - 1 - idx);
        }

        new_string.push(alpha[idx + key]);
    }

    new_string
}

fn decrypt(text: &[char], key: usize) -> Vec<char> {
    let alpha: Vec<char> = ('a'..='z').collect();
    let mut new_string: Vec<char> = Vec::new();

    for &c in text {
        if c == ' ' {
            // Python version skips spaces during decryption
            continue;
        }

        let alpha_index = alpha
            .iter()
            .position(|&ch| ch == c)
            .expect("character not found in alphabet");

        // Handle possible negative index the way Python would
        let mut idx = alpha_index as isize - key as isize;
        if idx < 0 {
            idx += alpha.len() as isize; // mimic Python’s negative indexing
        }

        new_string.push(alpha[idx as usize]);
    }

    new_string
}

fn main() {
    let encrypted = encrypt("hello worlz", 3);
    println!("Encrypted: {:?}", encrypted);

    let decrypted = decrypt(&encrypted, 3);
    println!("Decrypted: {:?}", decrypted);
}