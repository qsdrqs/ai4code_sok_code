use aes_ctr::AesCtr;
use block_modes::block_padding::NoPadding;
use block_modes::{BlockMode, Cbc};
use std::io::Write;

fn encrypt(k: &[u8], m: &str) -> Vec<u8> {
    let cipher = AesCtr::new_from_slice(k, &[0u8; 16]).unwrap();
    let mut encrypted = Vec::new();
    let mut writer = std::io::Cursor::new(&mut encrypted);
    writer.write_all(m.as_bytes()).unwrap();
    let encrypted = cipher.encrypt(&mut writer.into_inner()).unwrap();
    encrypted
}

fn decrypt(k: &[u8], ct: &[u8]) -> String {
    let cipher = AesCtr::new_from_slice(k, &[0u8; 16]).unwrap();
    let decrypted = cipher.decrypt(ct).unwrap();
    String::from_utf8(decrypted).unwrap()
}

fn test() {
    fn round_trip_test() {
        let k = "This_key_for_demo_purposes_only!".as_bytes();
        let m = "Text may be any length you wish, no padding is required";
        let ct = encrypt(k, m);
        println!("{}", decrypt(k, &ct));
    }
    round_trip_test();
}

fn main() {
    test();
}