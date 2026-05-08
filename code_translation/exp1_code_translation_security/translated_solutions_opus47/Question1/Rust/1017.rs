use aes::Aes256;
use eax::Eax;
use eax::aead::{Aead, Error, KeyInit, generic_array::GenericArray};
use rand::{RngCore, rngs::OsRng};

// Type alias for AES-256 with EAX mode
type Aes256Eax = Eax<Aes256>;

// Function in Rust where one encrypts a given string using a given symmetric key.
fn encrypt(key: &[u8], plaintext: &[u8]) -> Result<(Vec<u8>, Vec<u8>, Vec<u8>), Error> {
    let cipher = Aes256Eax::new_from_slice(key).expect("Invalid key length");

    // Generate a random 16-byte nonce (matching pycryptodome's default for EAX)
    let mut nonce_bytes = [0u8; 16];
    OsRng.fill_bytes(&mut nonce_bytes);
    let nonce = GenericArray::from_slice(&nonce_bytes);

    // Encrypt the plaintext - the tag (16 bytes) is appended to the ciphertext
    let result = cipher.encrypt(nonce, plaintext)?;

    // Split the result into ciphertext and tag
    let (ct, tag) = result.split_at(result.len() - 16);

    Ok((nonce_bytes.to_vec(), tag.to_vec(), ct.to_vec()))
}

// Function in Rust where one decrypts a given string using a given symmetric key.
fn decrypt(key: &[u8], ciphertext: (Vec<u8>, Vec<u8>, Vec<u8>)) -> Result<Vec<u8>, Error> {
    let (nonce, tag, ct) = ciphertext;
    let cipher = Aes256Eax::new_from_slice(key).expect("Invalid key length");
    let nonce_ga = GenericArray::from_slice(&nonce);

    // Recombine ciphertext and tag for the decrypt operation (expected format)
    let mut combined = Vec::with_capacity(ct.len() + tag.len());
    combined.extend_from_slice(&ct);
    combined.extend_from_slice(&tag);

    cipher.decrypt(nonce_ga, combined.as_slice())
}

fn main() {
    // AES-256 requires a 32-byte key
    let key = b"thisis32byteskeyforaes256encryp!";
    let plaintext = b"Hello, World!";

    let encrypted = encrypt(key, plaintext).expect("Encryption failed");
    println!("Nonce: {:?}", encrypted.0);
    println!("Tag: {:?}", encrypted.1);
    println!("Ciphertext: {:?}", encrypted.2);

    let decrypted = decrypt(key, encrypted).expect("Decryption failed");
    println!("Decrypted: {}", String::from_utf8(decrypted).unwrap());
}