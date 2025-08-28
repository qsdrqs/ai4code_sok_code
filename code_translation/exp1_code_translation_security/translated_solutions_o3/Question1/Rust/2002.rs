// Cargo.toml dependencies (will be provided for you)
// [dependencies]
// aes   = "0.8"        # AES block-cipher implementation
// ctr   = "0.9"        # CTR (counter) mode for any block-cipher
// cipher = "0.4"       # Common traits used by `aes` + `ctr`

use aes::Aes256;
use ctr::cipher::{KeyIvInit, StreamCipher};

/// We’ll use AES-256 in big-endian CTR mode.  The counter/IV is the
/// same 128-bit zero value that `pyaes.AESModeOfOperationCTR()` starts
/// with when you don’t supply one.
type Aes256Ctr = ctr::Ctr128BE<Aes256>;
const ZERO_IV: [u8; 16] = [0u8; 16];

/// Encrypt `m` with a 256-bit key `k`, returning the raw ciphertext.
pub fn encrypt(k: &[u8], m: &str) -> Vec<u8> {
    let mut cipher = Aes256Ctr::new(k.into(), &ZERO_IV.into());
    let mut buf = m.as_bytes().to_vec();
    cipher.apply_keystream(&mut buf);
    buf
}

/// Decrypt ciphertext `ct` with key `k`, returning a UTF-8 `String`.
pub fn decrypt(k: &[u8], ct: &[u8]) -> String {
    let mut cipher = Aes256Ctr::new(k.into(), &ZERO_IV.into());
    let mut buf = ct.to_vec();
    cipher.apply_keystream(&mut buf);
    String::from_utf8(buf).expect("ciphertext was not valid UTF-8")
}

/// The same quick round-trip test as in the original Python snippet.
fn main() {
    // 32-byte (256-bit) key, UTF-8 encoded just like the Python code.
    let k = b"This_key_for_demo_purposes_only!";

    let ct = encrypt(
        k,
        "Text may be any length you wish, no padding is required",
    );

    println!("{}", decrypt(k, &ct));
}