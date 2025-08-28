// A very small Caesar–style “cipher” that mirrors the original Python example.

fn encrypt(text: &str, key: u32) -> String {
    text.chars()
        .map(|c| {
            // Shift the scalar value and try to turn it back into a `char`
            // (fall back to the original on the unlikely chance it fails).
            std::char::from_u32(c as u32 + key).unwrap_or(c)
        })
        .collect()
}

fn decrypt(text: &str, key: u32) -> String {
    text.chars()
        .map(|c| {
            // Saturating subtraction prevents under-flowing below 0.
            let shifted = (c as u32).saturating_sub(key);
            std::char::from_u32(shifted).unwrap_or(c)
        })
        .collect()
}

fn main() {
    let msg = encrypt("this is cool if it really works", 2);
    let res = decrypt(&msg, 2);

    println!("{}", msg);
    println!("{}", res);
}