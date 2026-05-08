// Cargo.toml dependencies:
// [dependencies]
// fernet = "0.2"

use fernet::Fernet;

fn encrypt(input: &[u8], key: &str) -> String {
    let f = Fernet::new(key).unwrap();
    f.encrypt(input)
}

fn decrypt(input: &str, key: &str) -> Vec<u8> {
    let f = Fernet::new(key).unwrap();
    f.decrypt(input).unwrap()
}

fn main() {
    let key = Fernet::generate_key();
    let input = b"super secret";
    let encrypted_input = encrypt(input, &key);
    let decrypted_input = decrypt(&encrypted_input, &key);
    println!("{:?}", input);
    println!("{:?}", decrypted_input);
}