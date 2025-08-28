// ┌────────────────────────────────────────────────────────────────────┐
// │ Cargo.toml                                                        │
// │------------------------------------------------------------------│
// │ [dependencies]                                                    │
// │ aes-eax = "0.7"          # AES-EAX implementation (AEAD API)      │
// │ rand    = "0.8"          # Used only to make a random nonce       │
// └────────────────────────────────────────────────────────────────────┘

use aes_eax::aead::{
    generic_array::GenericArray,          // fixed-size array type
    AeadInPlace,                          // encrypt/decrypt “in place”
    KeyInit,                              // .new(&key)
};
use aes_eax::Aes128Eax;                   // AES-128 in EAX mode
use rand::{rngs::OsRng, RngCore};         // random-byte generator

// Encrypt `msg`, return (ciphertext, tag, nonce).
fn encrypt(key: &[u8; 16], msg: &[u8]) -> (Vec<u8>, Vec<u8>, Vec<u8>) {
    let cipher = Aes128Eax::new(GenericArray::from_slice(key));

    // EAX for AES-128 uses a 16-byte nonce.
    let mut nonce_bytes = [0u8; 16];
    OsRng.fill_bytes(&mut nonce_bytes);

    // Copy plaintext into a growable buffer, then encrypt it in-place.
    let mut buffer = msg.to_vec();
    let tag = cipher
        .encrypt_in_place_detached(GenericArray::from_slice(&nonce_bytes), b"", &mut buffer)
        .expect("encryption failure");

    (buffer, tag.to_vec(), nonce_bytes.to_vec())
}

// Decrypt and (optionally) authenticate.
fn decrypt(key: &[u8; 16], ciphertext: &[u8], nonce: &[u8], tag: &[u8]) -> Vec<u8> {
    let cipher = Aes128Eax::new(GenericArray::from_slice(key));

    let mut buffer = ciphertext.to_vec();
    cipher
        .decrypt_in_place_detached(
            GenericArray::from_slice(nonce),
            b"",                                  // no associated data
            &mut buffer,
            GenericArray::from_slice(tag),
        )
        .expect("ciphertext / tag verification failed");

    buffer
}

fn main() {
    // Same 16-byte key as the Python snippet.
    let key: [u8; 16] = *b"Sixteen byte key";

    // Encrypt the word “message”, then immediately decrypt it.
    let (ciphertext, tag, nonce) = encrypt(&key, b"message");
    let plaintext = decrypt(&key, &ciphertext, &nonce, &tag);

    println!("{}", String::from_utf8_lossy(&plaintext));  // → message
}