use fernet::Fernet;

fn encrypt(input: &[u8], key: &[u8]) -> Vec<u8> {
    let f = Fernet::new(key).unwrap();
    f.encrypt(input).unwrap()
}

fn decrypt(input: &[u8], key: &[u8]) -> Vec<u8> {
    let f = Fernet::new(key).unwrap();
    f.decrypt(input).unwrap()
}

fn main() {
    let key = Fernet::generate_key();
    let input = b"super secret";
    let encrypted_input = encrypt(input, &key);
    let decrypted_input = decrypt(&encrypted_input, &key);

    println!("Original Input: {:?}", input);
    println!("Decrypted Input: {:?}", decrypted_input);
}