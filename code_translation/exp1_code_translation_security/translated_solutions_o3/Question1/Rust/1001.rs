// ----------------------------------------------------------
//  Simple Vigenère-style byte wise encrypt / decrypt helpers
// ----------------------------------------------------------

/// Encrypt `text` with `key`.
pub fn encrypt(key: &str, text: &str) -> String {
    // Pre-compute the key as integer code points once.
    let key_vals: Vec<u32> = key.chars().map(|c| c as u32).collect();
    let k_len              = key_vals.len();

    // Build the resulting string.
    let mut out = String::with_capacity(text.len());

    for (i, ch) in text.chars().enumerate() {
        let key_val = key_vals[i % k_len];
        let encrypted = ((ch as u32 + key_val) % 256) as u8;
        out.push(encrypted as char);
    }

    out
}

/// Decrypt `text` that was produced with the matching `key`.
pub fn decrypt(key: &str, text: &str) -> String {
    let key_vals: Vec<u32> = key.chars().map(|c| c as u32).collect();
    let k_len              = key_vals.len();

    let mut out = String::with_capacity(text.len());

    for (i, ch) in text.chars().enumerate() {
        let key_val   = key_vals[i % k_len] as i32;
        let decrypted = ((ch as i32 - key_val + 256) % 256) as u8;
        out.push(decrypted as char);
    }

    out
}

// -------------------------
// Tiny demonstration / test
// -------------------------
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn round_trip() {
        let key       = "secret";
        let message   = "Hello, world!";
        let encrypted = encrypt(key, message);
        let decrypted = decrypt(key, &encrypted);

        assert_eq!(decrypted, message);
    }
}

fn main() {
    let key       = "secret";
    let plaintext = "Hello, world!";

    let cipher = encrypt(key, plaintext);
    let back   = decrypt(key, &cipher);

    println!("plaintext : {}", plaintext);
    println!("ciphertext: {}", cipher);
    println!("decrypted : {}", back);
}