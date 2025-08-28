// --------  AES/CFB example  --------
//
//  Note:  All crates (`aes`, `cfb-mode`, `hex_literal`, …) are assumed to be
//  present in Cargo.toml as announced in the task statement.
//
//  ────────────────────────────────────────────────────────────────────────────

use aes::Aes128;
use cfb_mode::{
    cipher::{KeyIvInit, StreamCipher},
    Cfb,
};

/// AES-128 working in (full-block) CFB mode.
type AesCfb = Cfb<Aes128>;

/// Encrypt the given `message` with the supplied cipher object.
///
/// The Python version called `obj.encrypt`, which *mutated* the object’s
/// internal state.  
/// The same is done here, hence `cipher` is taken mutably.
fn encrypt_string(message: &str, cipher: &mut AesCfb) -> Vec<u8> {
    let mut buffer = message.as_bytes().to_vec();
    cipher.apply_keystream(&mut buffer); // in CFB encryption == keystream XOR
    buffer
}

/// Decrypt `ciphertext` with the supplied cipher object.
///
/// In CFB mode encryption and decryption are symmetric; the same
/// `apply_keystream` call is used for both directions.
fn decrypt_string(ciphertext: &[u8], cipher: &mut AesCfb) -> Vec<u8> {
    let mut buffer = ciphertext.to_vec();
    cipher.apply_keystream(&mut buffer);
    buffer
}

// ---------------------------------------------------------------------------

fn main() {
    // 16-byte key and IV, identical to the Python sample
    let key = b"This is a key123";
    let iv  = b"This is an IV456";

    // Two independent cipher objects, one for encrypting, one for decrypting
    let mut obj1 = AesCfb::new_from_slices(key, iv).expect("key/iv length");
    let mut obj2 = AesCfb::new_from_slices(key, iv).expect("key/iv length");

    let message = "The answer is no";

    let enc = encrypt_string(message, &mut obj1);
    let dec = decrypt_string(&enc, &mut obj2);

    println!("ciphertext (hex): {:02X?}", enc);
    println!("decrypted text   : {}", String::from_utf8(dec).unwrap());
}