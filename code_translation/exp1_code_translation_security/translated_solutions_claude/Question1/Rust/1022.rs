use fernet::Fernet;

fn main() {
    let ex_key = Fernet::generate_key();
    
    let encrypted = encrypt("Hello World!", &ex_key);
    println!("{:?}", encrypted);
    
    let decrypted = decrypt(&encrypted, &ex_key);
    println!("{}", decrypted);
}

fn encrypt(inp: &str, key: &str) -> Vec<u8> {
    let fernet = Fernet::new(key).unwrap();
    let enc_message = fernet.encrypt(inp.as_bytes());
    enc_message
}

fn decrypt(inp: &[u8], key: &str) -> String {
    let fernet = Fernet::new(key).unwrap();
    let decrypted_bytes = fernet.decrypt(inp).unwrap();
    String::from_utf8(decrypted_bytes).unwrap()
}