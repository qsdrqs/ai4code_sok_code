use fernet::Fernet;
use std::sync::LazyStatic;

lazy_static::lazy_static! {
    static ref KEY: Fernet = Fernet::generate_key();
}

fn encrypt_text(plain_text: &str) -> Result<String, Box<dyn std::error::Error>> {
    let encrypted_bytes = KEY.encrypt(plain_text.as_bytes())?;
    Ok(base64::encode(encrypted_bytes))
}

fn decrypt_text(encrypted_text: &str) -> Result<String, Box<dyn std::error::Error>> {
    let encrypted_bytes = base64::decode(encrypted_text)?;
    let decrypted_bytes = KEY.decrypt(&encrypted_bytes)?;
    Ok(String::from_utf8(decrypted_bytes)?)
}

// Dependencies needed in Cargo.toml:
/*
[dependencies]
fernet = "0.2"
base64 = "0.21"
lazy_static = "1.4"
*/

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_encrypt_decrypt() {
        let original = "Hello, World!";
        let encrypted = encrypt_text(original).unwrap();
        let decrypted = decrypt_text(&encrypted).unwrap();
        assert_eq!(original, decrypted);
    }
}