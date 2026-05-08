fn encrypt(s: &str, key: usize) -> Vec<char> {
    let alpha: Vec<char> = vec!['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                                 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                                 'u', 'v', 'w', 'x', 'y', 'z'];
    let mut new_string: Vec<char> = Vec::new();
    for c in s.chars() {
        if c == ' ' {
            new_string.push(' ');
            continue;
        }
        let mut alpha_index = alpha.iter().position(|&x| x == c).unwrap();
        println!("{}", alpha_index);
        if alpha_index + key > alpha.len() - 1 {
            alpha_index = key - (alpha.len() - 1 - alpha_index);
        }
        new_string.push(alpha[alpha_index + key]);
    }
    new_string
}

fn decrypt(s: &[char], key: usize) -> Vec<char> {
    let alpha: Vec<char> = vec!['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                                 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                                 'u', 'v', 'w', 'x', 'y', 'z'];
    let mut new_string: Vec<char> = Vec::new();
    for &c in s {
        if c == ' ' {
            continue;
        }
        let alpha_index = alpha.iter().position(|&x| x == c).unwrap();
        new_string.push(alpha[alpha_index - key]);
    }
    new_string
}

fn main() {
    let encrypted = encrypt("hello worlz", 3);
    println!("{:?}", encrypted);
    let decrypted = decrypt(&encrypted, 3);
    println!("{:?}", decrypted);
}