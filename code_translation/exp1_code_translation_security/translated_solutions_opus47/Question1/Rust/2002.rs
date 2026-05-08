// Cargo.toml dependencies:
// [dependencies]
// aes = "0.8"
// ctr = "0.9"

use aes::cipher::{generic_array::GenericArray, KeyIvInit, StreamCipher};
use aes::Aes256;
use ctr::Ctr128BE;

type Aes256Ctr = Ctr128BE<Aes256>;

// pyaes's default counter starts at 1, encoded as a 128-bit big-endian integer,
// i.e. fifteen zero bytes followed by 0x01.
const INITIAL_IV: [u8; 16] = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1];

// Where k is 256 bit UTF-8 encoded.
fn encrypt(k: &[u8], m: &str) -> Vec<u8> {
    let mut cipher = Aes256Ctr::new(
        GenericArray::from_slice(k),
        GenericArray::from_slice(&INITIAL_IV),
    );
    let mut buf = m.as_bytes().to_vec();
    cipher.apply_keystream(&mut buf);
    buf
}

// Where k is UTF-8 encoded.
fn decrypt(k: &[u8], ct: &[u8]) -> String {
    let mut cipher = Aes256Ctr::new(
        GenericArray::from_slice(k),
        GenericArray::from_slice(&INITIAL_IV),
    );
    let mut buf = ct.to_vec();
    cipher.apply_keystream(&mut buf);
    String::from_utf8(buf).unwrap()
}

fn test() {
    fn round_trip_test() {
        let k = b"This_key_for_demo_purposes_only!";
        let ct = encrypt(
            k,
            "Text may be any length you wish, no padding is required",
        );
        println!("{}", decrypt(k, &ct));
    }

    round_trip_test();
}

fn main() {
    test();
}