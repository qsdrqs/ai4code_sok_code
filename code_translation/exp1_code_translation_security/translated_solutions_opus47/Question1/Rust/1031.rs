use fernet::Fernet;

/// encrypt using a symmetric key
fn encrypt(fernet: &Fernet, message: &str) -> String {
    fernet.encrypt(message.as_bytes())
}

/// decrypt using a symmetric key
fn decrypt(fernet: &Fernet, enc_message: &str) -> String {
    let dec_bytes = fernet
        .decrypt(enc_message)
        .expect("decryption failed");
    String::from_utf8(dec_bytes).expect("invalid UTF-8")
}

fn main() {
    let message = "the solutions are here somewhere";
    println!("Secret Message: {}", message);

    let key = Fernet::generate_key();
    let fernet = Fernet::new(&key).expect("failed to create Fernet instance");

    let enc_message = encrypt(&fernet, message);
    println!("{}", enc_message);

    let dec_message = decrypt(&fernet, &enc_message);
    println!("decrypted Message: {}", dec_message);
}