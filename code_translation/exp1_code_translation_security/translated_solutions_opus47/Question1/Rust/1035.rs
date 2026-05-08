// Cargo.toml dependencies:
// [dependencies]
// aes = "0.8"
// eax = "0.5"
// rand_core = { version = "0.6", features = ["getrandom"] }

use aes::Aes128;
use eax::Eax;
use eax::aead::{AeadInPlace, KeyInit};
use eax::aead::generic_array::GenericArray;
use rand_core::{OsRng, RngCore};

type Aes128Eax = Eax<Aes128>;

fn encrypt(key: &[u8], msg: &[u8]) -> (Vec<u8>, Vec<u8>, Vec<u8>) {
    let cipher = Aes128Eax::new(GenericArray::from_slice(key));

    // Generate a random 16-byte nonce (EAX default)
    let mut nonce_bytes = [0u8; 16];
    OsRng.fill_bytes(&mut nonce_bytes);
    let nonce = GenericArray::from(nonce_bytes);

    // Encrypt in-place, obtaining a detached authentication tag
    let mut ciphertext = msg.to_vec();
    let tag = cipher
        .encrypt_in_place_detached(&nonce, b"", &mut ciphertext)
        .unwrap();

    (ciphertext, tag.to_vec(), nonce.to_vec())
}

fn decrypt(key: &[u8], ciphertext: &[u8], nonce: &[u8], tag: Option<&[u8]>) -> Vec<u8> {
    let cipher = Aes128Eax::new(GenericArray::from_slice(key));
    let mut plaintext = ciphertext.to_vec();

    // In the eax crate, decryption and tag verification are combined.
    if let Some(tag_bytes) = tag {
        cipher
            .decrypt_in_place_detached(
                GenericArray::from_slice(nonce),
                b"",
                &mut plaintext,
                GenericArray::from_slice(tag_bytes),
            )
            .expect("decryption/verification failed");
    }

    plaintext
}

fn main() {
    let key = b"Sixteen byte key";
    let (ctext, tag, nonce) = encrypt(key, b"message");
    let plaintext = decrypt(key, &ctext, &nonce, Some(&tag));
    println!("{:?}", String::from_utf8(plaintext).unwrap());
}