use fernet::Fernet;

fn encrypt(msg: &[u8], key: &str) -> String {
    let f = Fernet::new(key).unwrap();
    f.encrypt(msg)
}

fn decrypt(msg: &str, key: &str) -> Vec<u8> {
    let f = Fernet::new(key).unwrap();
    f.decrypt(msg).unwrap()
}

fn main() {
    let key = Fernet::generate_key();
    let msg = b"a much longer message with punctuation!";
    let e = encrypt(msg, &key);
    let d = decrypt(&e, &key);

    println!("{}", e);
    println!("{}", String::from_utf8(d).unwrap());
}