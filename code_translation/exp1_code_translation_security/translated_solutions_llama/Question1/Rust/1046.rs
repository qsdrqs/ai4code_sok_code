fn encrypt(msg: &str, key: &str) -> String {
    let mut temp = String::new();
    for c in msg.chars() {
        temp.push(c);
        temp.push_str(key);
    }
    temp
}

fn decrypt(msg: &str, _key: &str) -> String {
    let mut temp = String::new();
    let mut control = 0;
    for c in msg.chars() {
        if control % 2 == 0 {
            temp.push(c);
        }
        control += 1;
    }
    temp
}

fn main() {
    println!("{}", encrypt('a', 'b'));
    println!("{}", decrypt("ab", "b"));
}