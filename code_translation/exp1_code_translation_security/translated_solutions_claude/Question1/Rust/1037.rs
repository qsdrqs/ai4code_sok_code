use fernet::Fernet;

fn encrypt(input: &[u8], key: &str) -> Result<String, fernet::FernetError> {
    let fernet = Fernet::new(key)?;
    fernet.encrypt(input)
}

fn decrypt(input: &str, key: &str) -> Result<Vec<u8>, fernet::FernetError> {
    let fernet = Fernet::new(key)?;
    fernet.decrypt(input)
}

fn main() -> Result<(), fernet::FernetError> {
    let key = Fernet::generate_key();
    let input = b"super secret";
    let encrypted_input = encrypt(input, &key)?;
    let decrypted_input = decrypt(&encrypted_input, &key)?;
    
    println!("{:?}", std::str::from_utf8(input).unwrap());
    println!("{:?}", std::str::from_utf8(&decrypted_input).unwrap());
    
    Ok(())
}