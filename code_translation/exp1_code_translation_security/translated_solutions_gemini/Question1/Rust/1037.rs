// Import the Fernet struct from the fernet crate
use fernet::Fernet;

/// Encrypts a byte slice using a Fernet key.
/// The key must be a URL-safe base64-encoded 32-byte string.
fn encrypt(input: &[u8], key: &str) -> String {
    // Fernet::new can fail if the key is invalid, so it returns a Result.
    // We use unwrap() for simplicity, which panics on failure.
    let f = Fernet::new(key).unwrap();
    f.encrypt(input)
}

/// Decrypts a Fernet token using a key.
/// Returns the original plaintext as a vector of bytes.
fn decrypt(input: &str, key: &str) -> Vec<u8> {
    // Fernet::new can fail if the key is invalid.
    let f = Fernet::new(key).unwrap();
    // f.decrypt can fail if the token is invalid (e.g., wrong key, expired, corrupted).
    // We use unwrap() here as well.
    f.decrypt(input).unwrap()
}

fn main() {
    // Generate a new, random Fernet key.
    let key = Fernet::generate_key();

    // The input data to be encrypted. The b"" syntax creates a byte string literal.
    let input = b"super secret";

    // Encrypt the input data. We pass the key as a string slice (&key).
    let encrypted_input = encrypt(input, &key);

    // Decrypt the token to get the original data back.
    let decrypted_input = decrypt(&encrypted_input, &key);

    // Print the original and decrypted data.
    // We use the {:?} formatter to print byte slices/vectors.
    println!("Original:   {:?}", input);
    println!("Decrypted:  {:?}", decrypted_input);

    // You can also convert the decrypted bytes to a string to verify.
    assert_eq!(input, decrypted_input.as_slice());
    println!("\nSuccessfully encrypted and decrypted the message!");
}