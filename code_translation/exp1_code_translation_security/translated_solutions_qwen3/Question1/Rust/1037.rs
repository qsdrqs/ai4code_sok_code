use fernet::{Fernet, Key};

// Encrypts the given data using the provided key
fn encrypt(data: &[u8], key: &Key) -> Vec<u8> {
    let fernet = Fernet::new(key);
    fernet.encrypt(data)
}

// Decrypts the given data using the provided key
fn decrypt(data: &[u8], key: &Key) -> Result<Vec<u8>, fernet::Error> {
    let fernet = Fernet::new(key);
    fernet.decrypt(data)
}

fn main() {
    // Generate a new Fernet key
    let key = Key::generate();

    // Original input data
    let input = b"super secret";

    // Encrypt the data
    let encrypted_input = encrypt(input, &key);

    // Decrypt the data
    let decrypted_input = decrypt(&encrypted_input, &key).expect("Decryption failed");

    // Print the original and decrypted data
    println!("{:?}", input);
    println!("{:?}", decrypted_input);
}