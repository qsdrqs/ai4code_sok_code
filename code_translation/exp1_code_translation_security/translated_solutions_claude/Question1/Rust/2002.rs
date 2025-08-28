use aes::Aes256;
use aes::cipher::{KeyIvInit, StreamCipher};
use ctr::Ctr64BE;

type Aes256Ctr = Ctr64BE<Aes256>;

// Where k is 256 bit (32 bytes) key
fn encrypt(k: &[u8], m: &str) -> Vec<u8> {
    // CTR mode requires an IV/nonce. Using zeros for simplicity (like pyaes default)
    let iv = [0u8; 16];
    let mut cipher = Aes256Ctr::new(k.into(), &iv.into());
    
    let mut data = m.as_bytes().to_vec();
    cipher.apply_keystream(&mut data);
    data
}

fn decrypt(k: &[u8], ct: &[u8]) -> String {
    // CTR mode requires an IV/nonce. Using zeros for simplicity (like pyaes default)
    let iv = [0u8; 16];
    let mut cipher = Aes256Ctr::new(k.into(), &iv.into());
    
    let mut data = ct.to_vec();
    cipher.apply_keystream(&mut data);
    String::from_utf8(data).expect("Invalid UTF-8")
}

fn test() {
    fn round_trip_test() {
        let k = "This_key_for_demo_purposes_only!".as_bytes();
        let ct = encrypt(
            k,
            "Text may be any length you wish, no padding is required"
        );
        println!("{}", decrypt(k, &ct));
    }
    
    round_trip_test();
}

fn main() {
    test();
}