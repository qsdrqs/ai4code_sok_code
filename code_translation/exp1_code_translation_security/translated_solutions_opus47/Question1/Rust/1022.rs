use fernet::Fernet;

fn encrypt(inp: &str, key: &str) -> String {
    let fernet = Fernet::new(key).expect("Invalid key");
    fernet.encrypt(inp.as_bytes())
}

fn decrypt(inp: &str, key: &str) -> String {
    let fernet = Fernet::new(key).expect("Invalid key");
    let decrypted = fernet.decrypt(inp).expect("Failed to decrypt");
    String::from_utf8(decrypted).expect("Invalid UTF-8")
}

fn main() {
    let ex_key = Fernet::generate_key();

    let encrypted = encrypt("Hello World!", &ex_key);
    println!("{}", encrypted);

    let decrypted = decrypt(&encrypted, &ex_key);
    println!("{}", decrypted);
}