use base64::{engine::general_purpose::URL_SAFE, Engine as _};
use fernet::Fernet;
use pbkdf2::pbkdf2_hmac;
use rand::RngCore;
use sha2::Sha256;

/// Derive a secret key from a given password and salt
fn derive_key(password: &[u8], salt: &[u8], iterations: u32) -> String {
    let mut key = [0u8; 32];
    pbkdf2_hmac::<Sha256>(password, salt, iterations, &mut key);
    URL_SAFE.encode(&key)
}

fn encrypt(message: &str, password: &str, iterations: u32) -> Vec<u8> {
    let message_bytes = message.as_bytes();
    let mut salt = [0u8; 16];
    rand::thread_rng().fill_bytes(&mut salt);
    // Note: matches the original Python exactly, which hard-codes 100_000 here
    // regardless of the `iterations` parameter
    let key = derive_key(password.as_bytes(), &salt, 100_000);
    let fernet = Fernet::new(&key).unwrap();
    let encrypted = fernet.encrypt(message_bytes);
    let decoded_token = URL_SAFE.decode(&encrypted).unwrap();

    let mut result = Vec::with_capacity(16 + 4 + decoded_token.len());
    result.extend_from_slice(&salt);
    result.extend_from_slice(&iterations.to_be_bytes());
    result.extend_from_slice(&decoded_token);

    URL_SAFE.encode(&result).into_bytes()
}

fn decrypt(token: &[u8], password: &str) -> Vec<u8> {
    let decoded = URL_SAFE.decode(token).unwrap();
    let salt = &decoded[..16];
    let iter_bytes: [u8; 4] = decoded[16..20].try_into().unwrap();
    let token_raw = &decoded[20..];
    let token_b64 = URL_SAFE.encode(token_raw);

    let iterations = u32::from_be_bytes(iter_bytes);
    let key = derive_key(password.as_bytes(), salt, iterations);
    let fernet = Fernet::new(&key).unwrap();
    fernet.decrypt(&token_b64).unwrap()
}

fn main() {
    let token = encrypt("nice123", "password123", 100_000);
    println!("{}", String::from_utf8_lossy(&token));
    let decrypted = decrypt(&token, "password123");
    println!("{}", String::from_utf8_lossy(&decrypted));
}