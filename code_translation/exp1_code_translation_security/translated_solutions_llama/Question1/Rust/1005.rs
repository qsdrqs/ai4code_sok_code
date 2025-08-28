use fernet::Fernet;

fn encrypt(msg: &[u8], key: &[u8]) -> Vec<u8> {
    let f = Fernet::new(key).unwrap();
    f.encrypt(msg).unwrap()
}

fn decrypt(msg: &[u8], key: &[u8]) -> Vec<u8> {
    let f = Fernet::new(key).unwrap();
    f.decrypt(msg).unwrap()
}

fn main() {
    let key = Fernet::generate_key();
    let msg = b"a much longer message with punctuation!";
    let e = encrypt(msg, &key);
    let d = decrypt(&e, &key);

    println!("Encrypted message: {:?}", e);
    println!("Decrypted message: {:?}", d);
}