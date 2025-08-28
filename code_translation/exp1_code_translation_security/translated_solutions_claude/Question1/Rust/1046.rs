fn encrypt(msg: &str, key: &str) -> String {
    let mut temp = String::new();
    for c in msg.chars() {
        println!("{}", temp);
        temp.push(c);
        temp.push_str(key);
    }
    temp
}

fn decrypt(msg: &str, key: &str) -> String {
    let mut control = 0;
    let mut temp = String::new();
    let chars: Vec<char> = msg.chars().collect();
    
    for i in 0..chars.len() {
        if control % 2 == 0 {
            temp.push(chars[i]);
        }
        control += 1;
    }
    temp
}

fn main() {
    println!("{}", encrypt("a", "b"));
    println!("{}", decrypt("ab", "b"));
}