// 1:1 translation of the original Python snippet.
// Crates you’ll need in `Cargo.toml` (they will be supplied for you):
//
// aead     = "0.5"        # common AEAD traits
// aes-eax  = "0.8"        # AES-EAX construction (RustCrypto)
// rand     = "0.8"        # only used for generating random key / nonce
//
// ---------------------------------------------------------------------
//
// NOTE:  PyCryptodome’s `cipher.encrypt_and_digest()` returns the
//        authentication-tag separate from the ciphertext.  
//        `aes-eax` (AEAD API) returns them concatenated
//        (ciphertext || tag).  
//        To stay fully compatible with the original Python code we split
//        the two when we encrypt and join them again when we decrypt.
//

use aes_eax::Aes128Eax;                           // AES-128 in EAX mode
use aead::{Aead, KeyInit, generic_array::GenericArray};
use rand::RngCore;                                // for random bytes

// ---------------------------------------------------------------------
// Encode
// ---------------------------------------------------------------------
fn encode_string(
    message: &str,
    key:   &[u8; 16],
    nonce: &[u8; 16],
) -> (Vec<u8>, [u8; 16]) {
    // --- initialise cipher --------------------------------------------------
    let cipher      = Aes128Eax::new(GenericArray::from_slice(key));
    let nonce_ga    = GenericArray::from_slice(nonce);

    // --- encrypt ------------------------------------------------------------
    // `encrypt()` returns  cipher || tag  (tag is 16-bytes long for EAX)
    let mut ct_and_tag = cipher
        .encrypt(nonce_ga, message.as_bytes())
        .expect("encryption failure!");

    // --- split into (ciphertext, tag) ---------------------------------------
    let tag_start = ct_and_tag.len() - 16;          // last 16 bytes
    let tag: [u8; 16] = ct_and_tag[tag_start..].try_into().unwrap();
    let ciphertext     = ct_and_tag[..tag_start].to_vec();

    (ciphertext, tag)
}

// ---------------------------------------------------------------------
// Decode / verify
// ---------------------------------------------------------------------
fn decode_string(
    pair:  &(Vec<u8>, [u8; 16]),                   // (ciphertext, tag)
    key:   &[u8; 16],
    nonce: &[u8; 16],
) -> Result<String, aead::Error> {
    let cipher      = Aes128Eax::new(GenericArray::from_slice(key));
    let nonce_ga    = GenericArray::from_slice(nonce);

    // Re-attach tag to the ciphertext, then decrypt & verify.
    let mut buffer = pair.0.clone();
    buffer.extend_from_slice(&pair.1);

    let plaintext = cipher.decrypt(nonce_ga, buffer.as_ref())?;
    Ok(String::from_utf8(plaintext).expect("invalid UTF-8"))
}

// ---------------------------------------------------------------------
// Demo (behaves exactly like the original Python file)
// ---------------------------------------------------------------------
fn main() {
    // create random 128-bit key and 128-bit nonce (same sizes as in Python)
    let mut key   = [0u8; 16];
    let mut nonce = [0u8; 16];
    rand::thread_rng().fill_bytes(&mut key);
    rand::thread_rng().fill_bytes(&mut nonce);

    let original_message = "secret data";
    println!("{original_message}");

    let (ciphertext, tag) = encode_string(original_message, &key, &nonce);
    let decoded = decode_string(&(ciphertext, tag), &key, &nonce)
        .expect("decryption failure!");

    println!("{decoded}");
}