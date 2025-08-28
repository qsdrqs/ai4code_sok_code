use magic_crypt::{new_magic_crypt, MagicCryptTrait};

fn encode_str(plain_text: &str, key: &str) -> String {
    let mc = new_magic_crypt!(key, 256);
    mc.encrypt_str_to_base64(plain_text)
}

fn decode_str(str_encoded: &str, key: &str) -> Result<String, Box<dyn std::error::Error>> {
    let mc = new_magic_crypt!(key, 256);
    match mc.decrypt_base64_to_string(str_encoded) {
        Ok(decrypted) => Ok(decrypted),
        Err(e) => Err(Box::new(e)),
    }
}

fn main() {
    let str_encoded1 = encode_str("I am okay", "wow");
    match decode_str(&str_encoded1, "wow") {
        Ok(decoded) => println!("{}", decoded),
        Err(e) => println!("Decryption failed: {}", e),
    }
}