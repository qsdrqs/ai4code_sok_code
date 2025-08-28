// All dependencies are listed here, as you would find them in a Cargo.toml file.
// [dependencies]
// aes = "0.8"
// cfb-mode = "0.8"
// hex = "0.4"

use aes::Aes128;
use cfb_mode::cipher::{NewCipher, StreamCipher};
use cfb_mode::Cfb;

// In Rust, we can create a type alias for the specific cipher configuration we're using.
// This makes the function signatures cleaner and easier to read.
// This is an AES-128 cipher running in CFB mode.
type Aes128Cfb = Cfb<Aes128>;

/// Encrypts a message using a pre-initialized stream cipher.
/// Note: Stream ciphers are stateful, so the cipher object must be mutable.
fn encrypt_string(message: &[u8], cipher: &mut Aes128Cfb) -> Vec<u8> {
    // The `encrypt` method from the `StreamCipher` trait works in-place.
    // We clone the message into a new buffer to encrypt it.
    let mut buffer = message.to_vec();
    cipher.encrypt(&mut buffer);
    buffer
}

/// Decrypts a ciphertext using a pre-initialized stream cipher.
/// Note: Stream ciphers are stateful, so the cipher object must be mutable.
fn decrypt_string(ciphertext: &[u8], cipher: &mut Aes128Cfb) -> Vec<u8> {
    // The `decrypt` method also works in-place.
    // We clone the ciphertext into a new buffer to decrypt it.
    let mut buffer = ciphertext.to_vec();
    cipher.decrypt(&mut buffer);
    buffer
}

// Border line since we are sending an obj that is derived from a key rather a plaintext key
fn main() {
    // The key and IV must have specific lengths.
    // AES-128 uses a 16-byte (128-bit) key.
    // The IV for CFB mode must match the cipher's block size, which is 16 bytes for AES.
    let key = b"This is a key123"; // 16 bytes
    let iv = b"This is an IV456";  // 16 bytes

    // Create the first cipher object for encryption.
    // The `new_var` constructor is convenient for keys/IVs defined as slices.
    // It's marked `mut` because the encryption/decryption process modifies the cipher's internal state.
    let mut obj1 = Aes128Cfb::new_from_slices(key, iv)
        .expect("key and IV lengths must be valid");

    // Create a second, identical cipher object for decryption.
    let mut obj2 = Aes128Cfb::new_from_slices(key, iv)
        .expect("key and IV lengths must be valid");

    let message = b"The answer is no";

    println!("Original message: {}", std::str::from_utf8(message).unwrap());

    // Encrypt the message with the first cipher object
    let enc = encrypt_string(message, &mut obj1);
    println!("Encrypted (hex):  {}", hex::encode(&enc));

    // Decrypt the ciphertext with the second cipher object
    let dec_bytes = decrypt_string(&enc, &mut obj2);
    let dec_string = String::from_utf8(dec_bytes).expect("Decrypted data is not valid UTF-8");
    println!("Decrypted message: {}", dec_string);

    // Verify that the decrypted message matches the original
    assert_eq!(message, dec_string.as_bytes());
    println!("\nSuccessfully encrypted and decrypted the message!");
}