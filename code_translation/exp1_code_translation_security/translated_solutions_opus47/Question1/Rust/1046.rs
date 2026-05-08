fn encrypt(msg: &str, key: &str) -> String {
    // print(msg)
    let mut temp = String::new();
    for c in msg.chars() {
        // print(c)
        println!("{}", temp);
        temp.push(c);
        temp.push_str(key);
    }
    temp
}

fn decrypt(msg: &str, _key: &str) -> String {
    let mut control = 0;
    let mut temp = String::new();
    for c in msg.chars() {
        if control % 2 == 0 {
            // In Python, c[0] on a single-char string is the char itself
            temp.push(c);
        }
        control += 1;
    }
    // println!("{}", temp);
    temp
}

fn main() {
    println!("{}", encrypt("a", "b"));
    println!("{}", decrypt("ab", "b"));
}