// XOR–based “encryption” / “decryption” (symmetric)
pub fn encrypt(input: &str, key: &str) -> String {
    // Avoid a possible division-by-zero if someone passes an empty key.
    assert!(
        !key.is_empty(),
        "Key must contain at least one character."
    );

    // Collect the key once so we can index it quickly.
    let key_chars: Vec<char> = key.chars().collect();
    let key_len = key_chars.len();

    let mut out = String::with_capacity(input.chars().count());

    for (i, ch) in input.chars().enumerate() {
        let kch = key_chars[i % key_len];
        let xored = (ch as u32) ^ (kch as u32);

        // Safe because XOR of two values that are both ≤ 0x10_FFFF
        // can never exceed 0x10_FFFF.
        out.push(std::char::from_u32(xored).unwrap());
    }

    out
}

pub fn decrypt(input: &str, key: &str) -> String {
    // XORing twice with the same key restores the original text,
    // so decryption is identical to encryption.
    encrypt(input, key)
}

/* ------------------------------------------------------------------ */
/* Simple demo                                                         */
/* ------------------------------------------------------------------ */
/*
fn main() {
    let plaintext = "Hello, Rustaceans!";
    let key        = "secret";

    let cipher = encrypt(plaintext, key);
    let clear  = decrypt(&cipher, key);

    println!("Plain : {}", plaintext);
    println!("Cipher: {}", cipher);
    println!("Decr. : {}", clear);
}
*/