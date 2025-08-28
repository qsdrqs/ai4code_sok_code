use fernet::Fernet;
use std::error::Error;

/// Encrypts a message using a symmetric key.
///
/// # Arguments
/// * `fernet` - A reference to the Fernet instance holding the key.
/// * `message` - The string slice to encrypt.
///
/// # Returns
/// An encrypted token as a String.
fn encrypt(fernet: &Fernet, message: &str) -> String {
    // The fernet `encrypt` method takes a byte slice `&[u8]`.
    // `message.as_bytes()` converts the string slice to a byte slice.
    fernet.encrypt(message.as_bytes())
}

/// Decrypts a token using a symmetric key.
///
/// # Arguments
/// * `fernet` - A reference to the Fernet instance holding the key.
/// * `token` - The encrypted token to decrypt.
///
/// # Returns
/// A `Result` which is:
/// - `Ok(String)` containing the decrypted message if successful.
/// - `Err` if decryption fails (e.g., bad key, tampered token).
fn decrypt(fernet: &Fernet, token: &str) -> Result<String, Box<dyn Error>> {
    // `decrypt` returns a Result containing a vector of bytes (`Vec<u8>`).
    let decrypted_bytes = fernet.decrypt(token)?;

    // We must convert the resulting bytes back into a String.
    // This can also fail if the bytes are not valid UTF-8, so it returns a Result.
    let dec_message = String::from_utf8(decrypted_bytes)?;

    Ok(dec_message)
}

fn main() {
    let message = "the solutions are here somewhere";
    println!("Secret Message: {}", message);

    // Fernet::generate_key() is equivalent to the Python version.
    let key = Fernet::generate_key();

    // In Rust, we create the Fernet instance from the key.
    // The `new` method returns a Result, so we use `expect` to unwrap it.
    // In a real application, you would handle this error more gracefully.
    let fernet = Fernet::new(&key).expect("Failed to create Fernet instance");

    // Encrypt the message
    let enc_message = encrypt(&fernet, message);
    println!("Encrypted token: {}", enc_message);

    // Decrypt the message
    // The `decrypt` function returns a Result, so we use `expect` again
    // to get the value or panic if an error occurred.
    let dec_message = decrypt(&fernet, &enc_message).expect("Decryption failed");
    println!("Decrypted Message: {}", dec_message);
}