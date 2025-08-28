use fernet::Fernet;

/// Encrypt a UTF-8 string with the supplied Fernet instance.
fn encrypt(fernet: &Fernet, message: &str) -> String {
    fernet.encrypt(message.as_bytes())
}

/// Decrypt a Fernet token back into a UTF-8 string.
fn decrypt(fernet: &Fernet, token: &str) -> Result<String, Box<dyn std::error::Error>> {
    let bytes = fernet.decrypt(token)?;            // Vec<u8>
    Ok(String::from_utf8(bytes)?)                  // Convert to String
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    let message = "the solutions are here somewhere";
    println!("Secret Message: {}", message);

    // Generate a fresh symmetric key and initialise the cipher.
    let key   = Fernet::generate_key();            // String (Base64)
    let cipher = Fernet::new(&key);                // same as python-cryptography’s Fernet(key)

    // --- encryption ---
    let enc_message = encrypt(&cipher, message);
    println!("{}", enc_message);

    // --- decryption ---
    let dec_message = decrypt(&cipher, &enc_message)?;
    println!("decrypted Message: {}", dec_message);

    Ok(())
}