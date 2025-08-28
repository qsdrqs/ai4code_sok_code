fn encrypt(plaintext: &str, key: &str) -> String {
    let key_bytes = key.as_bytes();
    let key_len = key_bytes.len();
    let mut ciphertext = String::new();

    for (i, &p_byte) in plaintext.as_bytes().iter().enumerate() {
        // Determine the corresponding key character using modulo
        let k_index = i % key_len;
        let k_byte = key_bytes[k_index];

        // Calculate the offset from 'a' for both plaintext and key characters
        let p_offset = p_byte - b'a';
        let k_offset = k_byte - b'a';

        // Compute the encrypted character using modular arithmetic
        let sum = p_offset + k_offset;
        let c_byte = (sum % 26) + b'a';

        // Convert the byte back to a character and append to result
        ciphertext.push(c_byte as char);
    }

    ciphertext
}

fn decrypt(ciphertext: &str, key: &str) -> String {
    let key_bytes = key.as_bytes();
    let key_len = key_bytes.len();
    let mut plaintext = String::new();

    for (i, &c_byte) in ciphertext.as_bytes().iter().enumerate() {
        // Determine the corresponding key character using modulo
        let k_index = i % key_len;
        let k_byte = key_bytes[k_index];

        // Calculate the offset from 'a' for both ciphertext and key characters
        let c_offset = c_byte - b'a';
        let k_offset = k_byte - b'a';

        // Compute the difference and adjust for negative values using modular arithmetic
        let diff = (c_offset as i16) - (k_offset as i16);
        let mod_diff = (diff + 26) % 26;

        // Convert the result back to a character and append to result
        let p_byte = (mod_diff as u8) + b'a';
        plaintext.push(p_byte as char);
    }

    plaintext
}

fn main() {
    let plaintext = "helloworld";
    let key = "python";

    let ciphertext = encrypt(plaintext, key);
    println!("Ciphertext: {}", ciphertext);

    let decrypted = decrypt(&ciphertext, key);
    println!("Decrypted: {}", decrypted);

    // Ensure the decrypted text matches the original plaintext
    assert_eq!(plaintext, decrypted);
}