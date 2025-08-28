//  XOR cipher – Rust translation of the provided Python example.
//
//  The behaviour is the same as the original Python code:
//
//  * `encrypt` takes a plaintext & a key and returns the XOR-encrypted text.
//  * `decrypt` simply calls `encrypt` again (XOR is its own inverse).
//
//  No external crates are needed; everything is done with Rust’s standard
//  library.

fn encrypt(message: &str, key: &str) -> String {
    // For convenience we work on raw bytes.
    let msg_bytes = message.as_bytes();
    let key_bytes = key.as_bytes();

    // Edge-case: empty key ‑> leave the message unchanged.
    if key_bytes.is_empty() {
        return message.to_owned();
    }

    // Collect XOR’ed bytes.
    let mut out = Vec::with_capacity(msg_bytes.len());
    for (i, &m) in msg_bytes.iter().enumerate() {
        let k = key_bytes[i % key_bytes.len()];
        out.push(m ^ k);
    }

    // The original Python code blindly converts the result back to
    // Unicode characters (`chr`).  As long as the input is ASCII this
    // is fine, so we do the same.  If you expect arbitrary bytes in
    // real usage, return `Vec<u8>` instead.
    String::from_utf8(out).expect("Encrypted data is not valid UTF-8")
}

fn decrypt(ciphertext: &str, key: &str) -> String {
    // XORing twice with the same key restores the original message.
    encrypt(ciphertext, key)
}

fn main() {
    let cipher = encrypt("Hello world", "key");
    let plain  = decrypt(&cipher,      "key");

    println!("{}", plain); // prints: Hello world
}