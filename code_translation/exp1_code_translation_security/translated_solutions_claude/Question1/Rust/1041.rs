fn encrypt(string: &str, key: i32) -> String {
    let mut new_string = String::new();
    for char in string.chars() {
        let new_char = char::from_u32((char as u32).wrapping_add(key as u32)).unwrap_or(char);
        new_string.push(new_char);
    }
    new_string
}

fn decrypt(string: &str, key: i32) -> String {
    let mut new_string = String::new();
    for char in string.chars() {
        let new_char = char::from_u32((char as u32).wrapping_sub(key as u32)).unwrap_or(char);
        new_string.push(new_char);
    }
    new_string
}

fn main() {
    let msg = encrypt("this is cool if it really works", 2);
    let res = decrypt(&msg, 2);
    println!("{}", msg);
    println!("{}", res);
}