fn encrypt(string: &str, key: u32) -> String {
    let mut new_string = String::new();
    for char in string.chars() {
        new_string.push(char::from_u32(char as u32 + key).unwrap());
    }
    new_string
}

fn decrypt(string: &str, key: u32) -> String {
    let mut new_string = String::new();
    for i in string.chars() {
        new_string.push(char::from_u32(i as u32 - key).unwrap());
    }
    new_string
}

fn main() {
    let msg = encrypt("this is cool if it really works", 2);
    let res = decrypt(&msg, 2);
    println!("{}", msg);
    println!("{}", res);
}