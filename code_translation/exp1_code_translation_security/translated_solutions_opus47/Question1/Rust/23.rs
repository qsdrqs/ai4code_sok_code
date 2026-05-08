// this is gonna be really scuffed but it might work
// right now text and key must be equal length, but that can be changed to loop to the front of the key again for very long text
// it's TECHNICALLY a psuedo-cypher

fn encrypt(text: &str, key: &str) -> String {
    let mut result = String::new();
    let text_chars: Vec<char> = text.chars().collect();
    let key_chars: Vec<char> = key.chars().collect();
    for i in 0..text_chars.len() {
        let this_char = text_chars[i] as u32 + key_chars[i] as u32;
        result.push(char::from_u32(this_char).unwrap());
    }
    result
}

fn decrypt(text: &str, key: &str) -> String {
    let mut result = String::new();
    let text_chars: Vec<char> = text.chars().collect();
    let key_chars: Vec<char> = key.chars().collect();
    for i in 0..text_chars.len() {
        let this_char = text_chars[i] as u32 - key_chars[i] as u32;
        result.push(char::from_u32(this_char).unwrap());
    }
    result
}

fn main() {
    let key = "aaaaaaa";
    let decrypted = "abcdefg";
    let encrypted = encrypt("abcdefg", key);
    println!("{}", encrypt(decrypted, key));
    println!("{}", decrypt(&encrypted, key));
}