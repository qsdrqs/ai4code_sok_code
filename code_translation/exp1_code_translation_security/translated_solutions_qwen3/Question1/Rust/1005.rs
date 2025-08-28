use fernet::{Fernet, Key};

// Encrypts a message using the provided Fernet key
fn encrypt(msg: &[u8], key: &Key) -> String {
    let fernet = Fernet::new(key);
    fernet.encrypt(msg)
}

// Decrypts a Fernet token using the provided key
fn decrypt(token: &str, key: &Key) -> Result<Vec<u8>, fernet::Error> {
    let fernet = Fernet::new(key);
    fernet.decrypt(token)
}

fn main() {
    // Generate a new Fernet key
    let key = Key::generate();

    // Define the message to encrypt (as a byte slice)
    let msg = b"a much longer message with punctuation!";

    // Encrypt the message
    let encrypted = encrypt(msg, &key);

    // Decrypt the encrypted message
    let decrypted = decrypt(&encrypted, &key).expect("Decryption failed");

    // Print the results
    println!("Encrypted: {}", encrypted);
    println!("Decrypted: {:?}", decrypted);
}