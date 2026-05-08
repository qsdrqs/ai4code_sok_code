/// encrypt a string with a given key
fn encrypt(string: &str, key: &str) -> String {
    let key_bytes = key.as_bytes();
    let encrypted: Vec<u8> = string
        .as_bytes()
        .iter()
        .enumerate()
        .map(|(i, &b)| b ^ key_bytes[i % key_bytes.len()])
        .collect();
    String::from_utf8(encrypted).unwrap()
}

/// decrypt a string with a given key
fn decrypt(string: &str, key: &str) -> String {
    encrypt(string, key)
}

fn main() {
    assert_eq!(decrypt(&encrypt("sadasdasdsa", "1234"), "1234"), "sadasdasdsa");
}