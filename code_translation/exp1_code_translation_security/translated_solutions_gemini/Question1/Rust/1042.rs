//! This Rust code is a direct translation of the provided Python functions.
//! It includes all necessary logic and can be run directly.

/// A Caesar cipher implementation that shifts alphabetic characters by a given key.
///
/// # Arguments
/// * `key` - A non-negative integer representing the shift value.
/// * `string` - The string to encrypt.
///
/// # Returns
/// * `Some(String)` containing the encrypted text if inputs are valid.
/// * `None` if the key is negative or the input string is empty.
pub fn encrypt(key: i32, string: &str) -> Option<String> {
    // The Python checks `type(key) is not int` and `type(string) is not str`
    // are handled by Rust's static type system via the function signature.

    if key < 0 {
        return None;
    }
    if string.is_empty() {
        return None;
    }

    // The Python `key = key % 26` is handled here.
    // We cast to u8 as we are only dealing with ASCII character shifts.
    let key_shift = (key % 26) as u8;

    // Pre-allocating the string capacity is a small optimization.
    let mut new_string = String::with_capacity(string.len());

    for c in string.chars() {
        if c.is_ascii_alphabetic() {
            // Determine the base character ('a' for lowercase, 'A' for uppercase).
            let base = if c.is_ascii_lowercase() { b'a' } else { b'A' };
            
            // Perform the shift:
            // 1. Convert char to u8 and normalize to 0-25 range.
            // 2. Add the key shift and apply modulo 26 to wrap around the alphabet.
            // 3. Add the base back to get the new ASCII value.
            // 4. Convert the u8 value back to a char.
            let shifted_char = ((c as u8 - base + key_shift) % 26 + base) as char;
            new_string.push(shifted_char);
        } else {
            // Non-alphabetic characters are appended unchanged.
            new_string.push(c);
        }
    }

    Some(new_string)
}

/// Decrypts a given string using a Vigenère-like cipher.
/// Note: This function replicates the specific, and somewhat quirky, behavior
/// of the original Python code.
///
/// # Arguments
/// * `ciphertext` - The string to be decrypted.
/// * `key` - The symmetric key used for decryption.
///
/// # Returns
/// * `Some(String)` with the decrypted plaintext if inputs are valid.
/// * `None` if either string is empty or if the key is longer than the ciphertext.
pub fn decrypt(ciphertext: &str, key: &str) -> Option<String> {
    // The Python `isinstance` checks are handled by Rust's type system.
    if ciphertext.is_empty() || key.is_empty() {
        return None;
    }
    
    // Replicating the specific edge case from the Python code.
    if key.len() == 1 {
        return Some(ciphertext.to_string());
    }
    
    if key.len() > ciphertext.len() {
        return None;
    }

    let key_bytes = key.as_bytes();
    let ciphertext_bytes = ciphertext.as_bytes();
    let mut plaintext = String::with_capacity(ciphertext.len());

    for (i, &c_byte) in ciphertext_bytes.iter().enumerate() {
        let key_byte = key_bytes[i % key_bytes.len()];

        // The subtraction can result in a negative number.
        // Python's `%` on negative numbers behaves like a true mathematical modulo,
        // e.g., `-5 % 26 == 21`.
        // Rust's `%` is a remainder operator, e.g., `-5 % 26 == -5`.
        // To replicate Python's behavior, we use `rem_euclid`.
        // We cast to i16 to prevent underflow during subtraction.
        let value = (c_byte as i16 - key_byte as i16).rem_euclid(26) as u8;
        
        // The original Python code always adds 65 (ASCII for 'A'),
        // assuming the output should always be an uppercase letter. We replicate that.
        plaintext.push((value + b'A') as char);
    }

    Some(plaintext)
}

// --- Main function for demonstration and testing ---
fn main() {
    // --- Testing encrypt function ---
    println!("--- Testing encrypt ---");
    // Valid encryption
    let encrypted = encrypt(3, "Hello, World!");
    println!("encrypt(3, \"Hello, World!\") -> {:?}", encrypted); // Expected: Some("Khoor, Zruog!")

    // Key wrapping
    let encrypted_wrap = encrypt(29, "Hello, World!");
    println!("encrypt(29, \"Hello, World!\") -> {:?}", encrypted_wrap); // Expected: Some("Khoor, Zruog!")

    // Invalid key
    let invalid_key = encrypt(-1, "abc");
    println!("encrypt(-1, \"abc\") -> {:?}", invalid_key); // Expected: None

    // Empty string
    let empty_string = encrypt(5, "");
    println!("encrypt(5, \"\") -> {:?}", empty_string); // Expected: None

    // --- Testing decrypt function ---
    println!("\n--- Testing decrypt ---");
    // Valid decryption (example from Vigenère cipher)
    // Plaintext: HELLO, Key: KEY -> Ciphertext: RIJVS
    let decrypted = decrypt("RIJVS", "KEY");
    println!("decrypt(\"RIJVS\", \"KEY\") -> {:?}", decrypted); // Expected: Some("HELLO")

    // Key length is 1 (special case)
    let key_len_one = decrypt("RIJVS", "K");
    println!("decrypt(\"RIJVS\", \"K\") -> {:?}", key_len_one); // Expected: Some("RIJVS")

    // Key is longer than ciphertext
    let long_key = decrypt("HI", "KEY");
    println!("decrypt(\"HI\", \"KEY\") -> {:?}", long_key); // Expected: None

    // Empty ciphertext
    let empty_cipher = decrypt("", "KEY");
    println!("decrypt(\"\", \"KEY\") -> {:?}", empty_cipher); // Expected: None
}