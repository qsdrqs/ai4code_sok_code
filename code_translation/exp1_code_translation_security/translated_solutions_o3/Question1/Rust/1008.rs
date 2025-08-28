// Simple Vigenère-style cipher working on single–byte characters (0‥=255).

fn encrypt(key: &str, plaintext: &str) -> String {
    let key_bytes = key.as_bytes();
    let mut cipher = String::with_capacity(plaintext.len());

    for (i, &plain_byte) in plaintext.as_bytes().iter().enumerate() {
        let key_byte     = key_bytes[i % key_bytes.len()];
        let cipher_byte  = plain_byte.wrapping_add(key_byte); // (a + b) mod 256
        cipher.push(cipher_byte as char);                     // 0‥=255 ⇢ Unicode scalar value
    }

    cipher
}

fn decrypt(key: &str, ciphertext: &str) -> String {
    let key_bytes = key.as_bytes();
    let mut plain = String::with_capacity(ciphertext.chars().count());

    for (i, ch) in ciphertext.chars().enumerate() {
        let cipher_byte = ch as u32 as u8;                    // back to the stored byte (0‥=255)
        let key_byte    = key_bytes[i % key_bytes.len()];
        let plain_byte  = cipher_byte.wrapping_sub(key_byte); // (c − k) mod 256
        plain.push(plain_byte as char);
    }

    plain
}

fn main() {
    let cipher = encrypt("123", "helloworld");
    let plain  = decrypt("123", &cipher);

    println!("{}", plain);   // → helloworld
}