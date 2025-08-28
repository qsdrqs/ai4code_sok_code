/// Encrypts the given plaintext with the provided key.
///
/// The encryption is done by adding the ASCII values of the plaintext character
/// and the key character (repeating the key as necessary), then taking the result
/// modulo 256.
///
/// # Arguments
///
/// * `key`: The encryption key.
/// * `plaintext`: The text to be encrypted.
///
/// # Returns
///
/// The encrypted ciphertext.
fn encrypt(key: &str, plaintext: &str) -> String {
    let mut ciphertext = String::new();
    for (i, c) in plaintext.chars().enumerate() {
        let key_c = key.chars().nth(i % key.len()).unwrap();
        let cipher_c = ((c as u8 + key_c as u8) % 256) as u8;
        ciphertext.push(cipher_c as char);
    }
    ciphertext
}

/// Decrypts the given ciphertext with the provided key.
///
/// The decryption is done by subtracting the ASCII values of the key character
/// from the ciphertext character (repeating the key as necessary), then taking
/// the result modulo 256.
///
/// # Arguments
///
/// * `key`: The decryption key.
/// * `ciphertext`: The text to be decrypted.
///
/// # Returns
///
/// The decrypted plaintext.
fn decrypt(key: &str, ciphertext: &str) -> String {
    let mut plaintext = String::new();
    for (i, c) in ciphertext.chars().enumerate() {
        let key_c = key.chars().nth(i % key.len()).unwrap();
        let plain_c = ((256 + c as u8 - key_c as u8) % 256) as u8;
        plaintext.push(plain_c as char);
    }
    plaintext
}

fn main() {
    let key = "123";
    let plaintext = "helloworld";
    let ciphertext = encrypt(key, plaintext);
    println!("{}", decrypt(key, &ciphertext));
}