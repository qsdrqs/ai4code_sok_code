// ─── Imports ────────────────────────────────────────────────────────────────────
use aes::Aes128;                    // AES block-cipher implementation
use eax::Eax;                       // EAX mode over an arbitrary block-cipher
use aead::{Aead, KeyInit, generic_array::GenericArray};
use rand_core::{OsRng, RngCore};    // Random-number generator for the nonce

// Our concrete AEAD type: AES-128 in EAX mode
type AesEax = Eax<Aes128>;

/// Encrypt `m` with secret key `sk`.
///
/// Returns a tuple `(ciphertext, nonce)`.
pub fn encrypt(m: &[u8], sk: &[u8]) -> Result<(Vec<u8>, [u8; 16]), aead::Error> {
    // Python code used a 16-byte key; keep the same restriction here.
    assert_eq!(sk.len(), 16, "key must be 16 bytes (AES-128)");

    // Initialise the AEAD instance.
    let cipher = AesEax::new(GenericArray::from_slice(sk));

    // Generate a fresh 16-byte nonce (Python’s `cipher.nonce` is 16 bytes).
    let mut nonce = [0u8; 16];
    OsRng.fill_bytes(&mut nonce);

    // Encrypt; the authentication tag is automatically appended to the
    // returned ciphertext (just like PyCryptodome’s `encrypt_and_digest`).
    let ciphertext = cipher.encrypt(GenericArray::from_slice(&nonce), m)?;

    Ok((ciphertext, nonce))
}

/// Decrypt the pair `(ciphertext, nonce)` with secret key `sk`.
///
/// On success the original plaintext is returned.
pub fn decrypt(cn: (Vec<u8>, [u8; 16]), sk: &[u8]) -> Result<Vec<u8>, aead::Error> {
    let (c, nonce) = cn;
    assert_eq!(sk.len(), 16, "key must be 16 bytes (AES-128)");

    let cipher = AesEax::new(GenericArray::from_slice(sk));

    // Decrypt and authenticate.
    cipher.decrypt(GenericArray::from_slice(&nonce), c.as_ref())
}

/* ---------------------------------------------------------------------------
   Example usage
   ---------------------------------------------------------------------------
fn main() -> Result<(), Box<dyn std::error::Error>> {
    let key = *b"an_example_16byt";   // 16-byte key
    let msg = b"hello world";

    let (ct, nonce) = encrypt(msg, &key)?;
    let pt = decrypt((ct, nonce), &key)?;

    assert_eq!(pt, msg);
    println!("round-trip OK");
    Ok(())
}
*/