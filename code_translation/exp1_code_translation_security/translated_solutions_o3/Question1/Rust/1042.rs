// Encrypt:  simple (ASCII-only) Caesar shift
pub fn encrypt(key: i32, text: &str) -> Option<String> {
    // negative key or empty input  →  None
    if key < 0 || text.is_empty() {
        return None;
    }

    // Reduce the shift to the 0-25 range
    let key = (key % 26) as u8;

    // Build the output
    let mut out = String::with_capacity(text.len());
    for ch in text.chars() {
        if ch.is_ascii_alphabetic() {
            let base = if ch.is_ascii_lowercase() { b'a' } else { b'A' };
            let shifted =
                ((ch as u8 - base + key) % 26) + base;
            out.push(shifted as char);
        } else {
            // Non-alphabetic characters are copied verbatim
            out.push(ch);
        }
    }

    Some(out)
}

// Decrypt:  Vigenère-style, returns `None` on exactly the same
//           conditions as the original Python routine.
pub fn decrypt(ciphertext: &str, key: &str) -> Option<String> {
    // Type checks are unnecessary in Rust (statically enforced),
    // but we keep the remaining Python validations.
    if ciphertext.is_empty() || key.is_empty() {
        return None;
    }
    if key.len() == 1 {
        return Some(ciphertext.to_string());
    }
    if key.len() > ciphertext.len() {
        return None;
    }

    let key_bytes = key.as_bytes();
    let key_len   = key_bytes.len();

    let mut plain = String::with_capacity(ciphertext.len());
    for (i, &c_byte) in ciphertext.as_bytes().iter().enumerate() {
        let k_byte  = key_bytes[i % key_len];
        // (cipher - key) mod 26, then force it into capital letters (ASCII 65-90)
        let value   = ((c_byte as i32 - k_byte as i32).rem_euclid(26) + 65) as u8;
        plain.push(value as char);
    }

    Some(plain)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_encrypt() {
        assert_eq!(encrypt(3, "Abc, xyz!"), Some("Def, abc!".to_string()));
        assert_eq!(encrypt(-1, "abc"), None);
        assert_eq!(encrypt(30, "xyz"), Some("bcd".to_string())); // 30 % 26 = 4
    }

    #[test]
    fn test_decrypt() {
        assert_eq!(decrypt("LXFOPVEFRNHR", "LEMON"), Some("ATTACKATDAWN".to_string()));
        assert_eq!(decrypt("", "a"), None);
        assert_eq!(decrypt("ABC", ""), None);
        assert_eq!(decrypt("ABC", "X"), Some("ABC".to_string())); // key of length 1
        assert_eq!(decrypt("ABC", "LONGKEY"), None);              // key > text
    }
}