/// Write two functions in Rust where one encrypts and the other decrypts a given string using a given symmetric key.

/// Encrypts a given string using a given symmetric key.
fn encrypt(string: &str, key: &str) -> String {
    let mut encrypted_string = String::new();
    let string_bytes = string.as_bytes();
    let key_bytes = key.as_bytes();
    
    for i in 0..string_bytes.len() {
        let encrypted_byte = string_bytes[i] ^ key_bytes[i % key_bytes.len()];
        encrypted_string.push(encrypted_byte as char);
    }
    
    encrypted_string
}

/// Decrypts a given string using a given symmetric key.
fn decrypt(string: &str, key: &str) -> String {
    let mut decrypted_string = String::new();
    let string_bytes = string.as_bytes();
    let key_bytes = key.as_bytes();
    
    for i in 0..string_bytes.len() {
        let decrypted_byte = string_bytes[i] ^ key_bytes[i % key_bytes.len()];
        decrypted_string.push(decrypted_byte as char);
    }
    
    decrypted_string
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_encrypt_decrypt() {
        let original = "Hello, World!";
        let key = "secret";
        
        let encrypted = encrypt(original, key);
        let decrypted = decrypt(&encrypted, key);
        
        assert_eq!(original, decrypted);
    }
    
    #[test]
    fn test_symmetric_property() {
        let message = "This is a test message";
        let key = "mykey123";
        
        let encrypted = encrypt(message, key);
        let decrypted = decrypt(&encrypted, key);
        
        assert_eq!(message, decrypted);
        assert_ne!(message, encrypted); // Ensure it's actually encrypted
    }
}

fn main() {
    // Example usage
    let message = "Hello, Rust!";
    let key = "mykey";
    
    let encrypted = encrypt(message, key);
    println!("Original: {}", message);
    println!("Encrypted: {:?}", encrypted); // Using {:?} because encrypted string might contain non-printable characters
    
    let decrypted = decrypt(&encrypted, key);
    println!("Decrypted: {}", decrypted);
}