fn encrypt(string: &str, key: i32) -> String {
    let mut new_string = String::new();
    for char in string.chars() {
        new_string.push(((char as u8) as i32 + key) as u8 as char);
    }
    new_string
}

fn decrypt(string: &str, key: i32) -> String {
    let mut new_string = String::new();
    for char in string.chars() {
        new_string.push(((char as u8) as i32 - key) as u8 as char);
    }
    new_string
}

fn main() {
    let msg = encrypt("this is cool if it really works", 2);
    let res = decrypt(&msg, 2);
    println!("{}", msg);
    println!("{}", res);
}