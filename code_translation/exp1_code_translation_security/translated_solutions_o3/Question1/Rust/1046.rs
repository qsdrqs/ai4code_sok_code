fn encrypt(msg: &str, key: &str) -> String {
    let mut temp = String::new();

    for c in msg.chars() {
        // Replicates the “print(temp)” that appears before the concatenation.
        println!("{}", temp);
        temp.push(c);          // add current character
        temp.push_str(key);    // add the key after it
    }

    temp
}

fn decrypt(msg: &str, _key: &str) -> String {
    let mut control = 0usize;
    let mut temp = String::new();

    for c in msg.chars() {
        if control % 2 == 0 {
            temp.push(c);
        }
        control += 1;
    }

    temp
}

fn main() {
    println!("{}", encrypt("a", "b"));
    println!("{}", decrypt("ab", "b"));
}