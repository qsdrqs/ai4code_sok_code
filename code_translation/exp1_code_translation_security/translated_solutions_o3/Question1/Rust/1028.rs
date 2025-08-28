// XOR-based symmetric “encryption”/“decryption” identical to the Python code.
//
// NOTE:
// • Both functions work on `char`s exactly like the original Python version.
// • The result may contain non-printable or non-UTF-8 scalar values; whenever the
//   produced value is not a valid Rust `char`, the replacement character U+FFFD
//   is stored instead (this keeps the function infallible, mirroring Python’s
//   permissive behaviour).

pub fn encrypt(text: &str, key: &str) -> String {
    // Empty key?  Nothing to XOR with – return the input unchanged.
    if key.is_empty() {
        return text.to_owned();
    }

    // Collect the key once so we can index it by position quickly.
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();

    let mut encrypted = String::with_capacity(text.len());

    for (i, ch) in text.chars().enumerate() {
        let k = key_chars[i % key_len];

        // XOR the Unicode code points (as u32), then convert back to `char`.
        let code_point = (ch as u32) ^ (k as u32);
        let enc_ch = std::char::from_u32(code_point).unwrap_or('\u{FFFD}');
        encrypted.push(enc_ch);
    }

    encrypted
}

// Decryption is identical for an XOR cipher.
pub fn decrypt(text: &str, key: &str) -> String {
    encrypt(text, key)    // XORing twice with the same key re-creates the input
}

/* -------------------------------------------------------------------------- */
/* Example usage                                                              */
/* -------------------------------------------------------------------------- */

fn main() {
    let original = "Hello, world!";
    let key       = "rust";

    let cipher_text = encrypt(original, key);
    let plain_text  = decrypt(&cipher_text, key);

    println!("Original : {}", original);
    println!("Encrypted: {:?}", cipher_text); // `{:?}` shows even non-printables
    println!("Decrypted: {}", plain_text);
}