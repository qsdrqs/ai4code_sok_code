extern crate fernet;
use fernet::{Fernet, Key};

/// Encrypts a message using a Fernet instance.
fn encrypt(fernet: &Fernet, message: &str) -> Vec<u8> {
    fernet.encrypt(message.as_bytes())
}

/// Decrypts an encrypted message using a Fernet instance.
fn decrypt(fernet: &Fernet, enc_message: &[u8]) -> String {
    let decrypted = fernet.decrypt(enc_message).expect("Decryption failed");
    String::from_utf8(decrypted).expect("Invalid UTF-8 in decrypted message")
}

fn main() {
    let message = "the solutions are here somewhere";
    println!("Secret Message: {}", message);

    let key = Key::generate();
    let fernet = Fernet::new(key);

    let enc_message = encrypt(&fernet, message);
    let enc_str = String::from_utf8_lossy(&enc_message);
    println!("{}", enc_str);

    let dec_message = decrypt(&fernet, &enc_message);
    println!("decrypted Message: {}", dec_message);
}