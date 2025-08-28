// main.rs

// All dependencies are brought into scope with the `use` keyword.
// This is similar to Python's `import`.
use fernet::{Fernet, Error};

/// Encrypts a slice of bytes using a provided key.
///
/// # Arguments
/// * `data` - The data to be encrypted, as a byte slice (`&[u8]`).
/// * `key` - The encryption key, as a byte slice (`&[u8]`).
///
/// # Returns
/// A `Result` which is:
/// * `Ok(String)` containing the base64-encoded encrypted token on success.
/// * `Err(fernet::Error)` if the key is invalid.
fn encrypt(data: &[u8], key: &[u8]) -> Result<String, Error> {
    // In Rust, operations that can fail (like parsing a key)
    // typically return a `Result` enum.
    // The `?` operator is used for concise error handling. If `Fernet::new`
    // returns an `Err`, the function will immediately return that error.
    let fernet = Fernet::new(key)?;
    
    // The encrypt method returns the encrypted string. We wrap it in `Ok`
    // to match the function's return signature.
    Ok(fernet.encrypt(data))
}

/// Decrypts a token using a provided key.
///
/// # Arguments
/// * `token` - The encrypted token to be decrypted, as a string slice (`&str`).
/// * `key` - The decryption key, as a byte slice (`&[u8]`).
///
/// # Returns
/// A `Result` which is:
/// * `Ok(Vec<u8>)` containing the original decrypted bytes on success.
/// * `Err(fernet::Error)` if the key is invalid or the token is corrupt/tampered.
fn decrypt(token: &str, key: &[u8]) -> Result<Vec<u8>, Error> {
    // Note: The original Python code had a bug where it returned the wrong variable.
    // This Rust version is corrected.
    let fernet = Fernet::new(key)?;
    
    // The decrypt method itself returns a Result, so we can just return it directly.
    fernet.decrypt(token)
}

/// The main entry point of the program.
fn main() {
    // In the original Python code, `main` called `encrypt` on a file path string.
    // Here, we'll do the same but also add decryption to show a full cycle.
    
    // 1. Generate a new encryption key.
    // `Fernet::generate_key()` returns a base64-encoded String.
    let key_string = Fernet::generate_key();
    let key = key_string.as_bytes(); // Convert the String to a byte slice for our functions.

    // 2. Define the data to be encrypted.
    // The Python code encrypted the *string* "/safedir/test.txt", not the file's contents.
    // We do the same here by converting the string literal to bytes.
    let original_data = b"/safedir/test.txt";

    println!("Original data: {:?}", std::str::from_utf8(original_data).unwrap());
    println!("Encryption Key: {}", key_string);

    // 3. Encrypt the data.
    // We use `expect` to handle the Result. If encryption fails, the program will panic
    // with the provided message. This is common in examples and tests.
    let encrypted_token = encrypt(original_data, key)
        .expect("Failed to encrypt data.");
    
    println!("Encrypted token: {}", encrypted_token);

    // 4. Decrypt the data.
    let decrypted_bytes = decrypt(&encrypted_token, key)
        .expect("Failed to decrypt token.");

    // Convert the resulting byte vector back to a string to print it.
    let decrypted_data = String::from_utf8(decrypted_bytes)
        .expect("Decrypted data is not valid UTF-8.");

    println!("Decrypted data: {}", decrypted_data);

    // 5. Verify that the decrypted data matches the original.
    assert_eq!(std::str::from_utf8(original_data).unwrap(), decrypted_data);
    println!("\nSuccessfully encrypted and decrypted the data!");
}