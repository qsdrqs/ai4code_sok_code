// Port of the original Python example to Rust.
// The program performs a simple Vigenère-style cipher restricted
// to lowercase ASCII letters (‘a’–‘z’).

fn encrypt(plaintext: &str, key: &str) -> String {
    let key_bytes = key.as_bytes();
    let key_len   = key_bytes.len();

    let mut ciphertext = String::with_capacity(plaintext.len());

    for (i, &p_byte) in plaintext.as_bytes().iter().enumerate() {
        let p = p_byte - b'a';
        let k = key_bytes[i % key_len] - b'a';
        let c = (p + k) % 26;
        ciphertext.push((c + b'a') as char);
    }

    ciphertext
}

fn decrypt(ciphertext: &str, key: &str) -> String {
    let key_bytes = key.as_bytes();
    let key_len   = key_bytes.len();

    let mut plaintext = String::with_capacity(ciphertext.len());

    for (i, &c_byte) in ciphertext.as_bytes().iter().enumerate() {
        let c = c_byte - b'a';
        let k = key_bytes[i % key_len] - b'a';
        let p = (26 + c as i16 - k as i16) % 26; // keep result positive
        plaintext.push((p as u8 + b'a') as char);
    }

    plaintext
}

fn main() {
    let plaintext = "helloworld";
    let key       = "python";

    let ciphertext = encrypt(plaintext, key);
    println!("Ciphertext: {}", ciphertext);

    let decrypted = decrypt(&ciphertext, key);
    println!("{}", decrypted);

    assert_eq!(plaintext, decrypted);
}