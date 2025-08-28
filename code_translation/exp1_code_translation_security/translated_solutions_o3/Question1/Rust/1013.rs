use base64::{engine::general_purpose::URL_SAFE, Engine as _};
use fernet::{Fernet, DecryptionError};
use pbkdf2::pbkdf2_hmac;
use rand::rngs::OsRng;
use rand::RngCore;
use sha2::Sha256;

/// Derive a 32-byte key from a password and salt, then
/// return the URL-safe base-64 string that a Fernet key expects.
fn derive_key(password: &[u8], salt: &[u8], iterations: u32) -> String {
    let mut key = [0u8; 32];
    pbkdf2_hmac::<Sha256>(password, salt, iterations, &mut key);
    // Fernet wants the key itself base-64 encoded.
    URL_SAFE.encode(key)
}

/// Encrypt `message` with the supplied password.
/// Returned value is identical (bit-for-bit) to the Python code.
fn encrypt(message: &str, password: &str, iterations: u32) -> String {
    // 1. fresh 16-byte salt
    let mut salt = [0u8; 16];
    OsRng.fill_bytes(&mut salt);

    // 2. PBKDF2 → Fernet key
    let key_b64 = derive_key(password.as_bytes(), &salt, iterations);
    let fernet  = Fernet::new(&key_b64).expect("derived key must be valid");

    // 3. Regular Fernet encryption
    let inner_token        = fernet.encrypt(message.as_bytes());           // base-64 (URL-safe) string
    let inner_token_raw    = URL_SAFE.decode(inner_token).unwrap();        // raw bytes

    // 4. Build custom envelope: salt | iterations | raw_fernet
    let mut out = Vec::with_capacity(16 + 4 + inner_token_raw.len());
    out.extend_from_slice(&salt);
    out.extend_from_slice(&iterations.to_be_bytes());
    out.extend_from_slice(&inner_token_raw);

    // 5. One last URL-safe base-64
    URL_SAFE.encode(out)
}

/// Decrypt token produced by `encrypt`.
fn decrypt(token: &str, password: &str) -> Result<String, DecryptionError> {
    let decoded = URL_SAFE.decode(token).map_err(|_| DecryptionError::InvalidToken)?;

    if decoded.len() < 20 {
        return Err(DecryptionError::InvalidToken);
    }

    let (salt, rest)     = decoded.split_at(16);
    let (iter_bytes, ct) = rest.split_at(4);

    let iterations = u32::from_be_bytes(iter_bytes.try_into().unwrap());

    let key_b64   = derive_key(password.as_bytes(), salt, iterations);
    let fernet    = Fernet::new(&key_b64).unwrap();

    // raw → base-64 in order to please Fernet
    let inner_token_b64 = URL_SAFE.encode(ct);
    let plaintext       = fernet.decrypt(&inner_token_b64)?;   // Vec<u8>

    Ok(String::from_utf8(plaintext).expect("valid UTF-8"))
}

fn main() {
    let token = encrypt("nice123", "password123", 100_000);
    println!("{}", token);

    let decrypted = decrypt(&token, "password123").unwrap();
    println!("{}", decrypted);
}