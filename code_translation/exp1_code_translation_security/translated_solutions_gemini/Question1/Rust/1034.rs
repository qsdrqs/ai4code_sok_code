// All dependencies would be listed in your Cargo.toml file.
// For this example, we'll list them in comments.
//
// [dependencies]
// aes = "0.8.3"
// base64 = "0.21.5"
// cbc = { version = "0.1.2", features = ["alloc"] }
// pbkdf2 = { version = "0.12.2", features = ["simple"] }
// rand = "0.8.5"
// sha1 = "0.10.6"

use aes::Aes256;
use cbc::cipher::{BlockDecryptMut, BlockEncryptMut, KeyIvInit};
use cbc::cipher::block_padding::Pkcs7;
use pbkdf2::pbkdf2_hmac;
use rand::RngCore;
use sha1::Sha1;

// Define type aliases for the encryptor and decryptor to simplify the code.
type Aes256CbcEnc = cbc::Encryptor<Aes256>;
type Aes256CbcDec = cbc::Decryptor<Aes256>;

/// Derives a 32-byte (256-bit) key from a password string using PBKDF2.
/// This mimics how cryptocode creates a key from a simple password.
fn derive_key(key: &str) -> [u8; 32] {
    let mut derived_key = [0u8; 32];
    // The python `cryptocode` library uses a fixed salt and 1000 iterations with SHA1.
    // We replicate that here for compatibility.
    let salt = b"cryptocode";
    pbkdf2_hmac::<Sha1>(key.as_bytes(), salt, 1000, &mut derived_key);
    derived_key
}

/// Encrypts a plaintext string using a key.
/// The output is a Base64 encoded string containing the IV and the ciphertext.
pub fn encode_str(plain_text: &str, key: &str) -> Result<String, Box<dyn std::error::Error>> {
    // 1. Derive a strong cryptographic key from the user-provided key string.
    let derived_key = derive_key(key);

    // 2. Generate a random 16-byte Initialization Vector (IV).
    // The IV must be unique for each encryption with the same key.
    let mut iv = [0u8; 16];
    rand::thread_rng().fill_bytes(&mut iv);

    // 3. Create the AES-CBC encryptor.
    let cipher = Aes256CbcEnc::new(&derived_key.into(), &iv.into());

    // 4. Encrypt the plaintext. The `encrypt_padded_vec_mut` handles PKCS7 padding.
    let ciphertext = cipher.encrypt_padded_vec_mut::<Pkcs7>(plain_text.as_bytes());

    // 5. Prepend the IV to the ciphertext. The decoder will need it.
    let mut result = Vec::new();
    result.extend_from_slice(&iv);
    result.extend_from_slice(&ciphertext);

    // 6. Encode the combined IV and ciphertext into a Base64 string for easy transport.
    Ok(base64::encode(result))
}

/// Decrypts a Base64 encoded string using a key.
pub fn decode_str(encoded_str: &str, key: &str) -> Result<String, Box<dyn std::error::Error>> {
    // 1. Derive the same key used for encryption.
    let derived_key = derive_key(key);

    // 2. Decode the Base64 string back into bytes.
    let decoded_data = base64::decode(encoded_str)?;

    // 3. Separate the IV (first 16 bytes) from the actual ciphertext.
    if decoded_data.len() < 16 {
        return Err("Invalid ciphertext: too short".into());
    }
    let (iv, ciphertext) = decoded_data.split_at(16);

    // 4. Create the AES-CBC decryptor.
    let cipher = Aes256CbcDec::new(&derived_key.into(), iv.into());

    // 5. Decrypt the ciphertext. The `decrypt_padded_vec_mut` will also remove the PKCS7 padding.
    let decrypted_bytes = cipher.decrypt_padded_vec_mut::<Pkcs7>(ciphertext)?;

    // 6. Convert the decrypted bytes back into a UTF-8 string.
    let decrypted_str = String::from_utf8(decrypted_bytes)?;

    Ok(decrypted_str)
}

fn main() {
    let plain_text = "I am okay";
    let key = "wow";

    println!("Original: {}", plain_text);

    // Encrypt the string. We use unwrap() for simplicity in this example.
    // In production code, you should handle the Result properly (e.g., with match or if let).
    let str_encoded1 = encode_str(plain_text, key).expect("Encryption failed");
    println!("Encoded: {}", str_encoded1);

    // Decrypt the string.
    let str_decoded1 = decode_str(&str_encoded1, key).expect("Decryption failed");
    println!("Decoded: {}", str_decoded1);

    // Verify the result
    assert_eq!(plain_text, str_decoded1);
    println!("\nSuccessfully encrypted and decrypted the message!");
}