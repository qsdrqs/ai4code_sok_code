fn encrypt(s: &str, key: usize) -> Vec<char> {
    let alpha = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
    let mut new_string = Vec::new();
    
    for c in s.chars() {
        if c == ' ' {
            new_string.push(' ');
            continue;
        }
        
        let alpha_index = alpha.iter().position(|&x| x == c).unwrap();
        println!("{}", alpha_index);
        
        let new_index = if alpha_index + key > alpha.len() - 1 {
            key - (alpha.len() - 1 - alpha_index) - 1
        } else {
            alpha_index + key
        };
        
        new_string.push(alpha[new_index]);
    }
    
    new_string
}

fn decrypt(chars: &[char], key: usize) -> Vec<char> {
    let alpha = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
    let mut new_string = Vec::new();
    
    for &c in chars {
        if c == ' ' {
            continue;
        }
        
        let alpha_index = alpha.iter().position(|&x| x == c).unwrap();
        let new_index = if alpha_index < key {
            alpha.len() - (key - alpha_index)
        } else {
            alpha_index - key
        };
        
        new_string.push(alpha[new_index]);
    }
    
    new_string
}

fn main() {
    let encrypted = encrypt("hello worlz", 3);
    println!("{:?}", encrypted);
    
    let decrypted = decrypt(&encrypted, 3);
    println!("{:?}", decrypted);
}