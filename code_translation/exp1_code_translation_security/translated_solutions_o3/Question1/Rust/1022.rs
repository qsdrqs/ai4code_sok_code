use anyhow::Result;
use fernet::Fernet;

/// Encrypt the given UTF-8 string with the supplied Fernet key.
fn encrypt(input: &str, key: &str) -> String {
    // The crate offers a simple API where encryption is a static method,
    // but we can also build a `Fernet` value if we want to match the
    // Python layout more closely.
    Fernet::encrypt(key, input)
}

/// Decrypt the Fernet token back into a UTF-8 string.
fn decrypt(token: &str, key: &str) -> Result<String> {
    Ok(Fernet::decrypt(key, token)?)
}

fn main() -> Result<()> {
    // Equivalent of `Fernet.generate_key()` in Python.
    let ex_key = Fernet::generate_key();

    let encrypted = encrypt("Hello World!", &ex_key);
    println!("{}", encrypted);   // Prints a base64-encoded Fernet token.

    let decrypted = decrypt(&encrypted, &ex_key)?;
    println!("{}", decrypted);   // Prints "Hello World!"

    Ok(())
}