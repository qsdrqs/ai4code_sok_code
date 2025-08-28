use aes::Aes256;
use eax::Eax;
use eax::{Error as EaxError};
use generic_array::{GenericArray, typenum::U16};
use rand::RngCore;

type AesEax = Eax<Aes256>;

/// Encrypts a plaintext using AES in EAX mode with a 32-byte symmetric key.
///
/// # Arguments
/// - `key`: A 32-byte symmetric key.
/// - `plaintext`: The data to be encrypted.
///
/// # Returns
/// A tuple `(nonce, tag, ciphertext)` where:
/// - `nonce`: A 16-byte random nonce used for encryption.
/// - `tag`: A 16-byte authentication tag.
/// - `ciphertext`: The encrypted data.
///
/// # Errors
/// Returns an `EaxError` if the key is not 32 bytes long or if encryption fails.
pub fn encrypt(
    key: &[u8],
    plaintext: &[u8],
) -> Result<([u8; 16], Vec<u8>, Vec<u8>), EaxError> {
    if key.len() != 32 {
        return Err(EaxError::default());
    }

    let mut nonce = GenericArray::<u8, U16>::default();
    rand::thread_rng().fill_bytes(&mut nonce);

    let key_ga = GenericArray::from_slice(key);
    let mut cipher = AesEax::new(key_ga, &nonce);

    let mut ciphertext = plaintext.to_vec();
    let tag = cipher.encrypt_in_place_detached(&mut ciphertext)?;

    let mut nonce_out = [0u8; 16];
    nonce_out.copy_from_slice(&nonce);

    Ok((nonce_out, tag.to_vec(), ciphertext))
}

/// Decrypts a ciphertext using AES in EAX mode with a 32-byte symmetric key.
///
/// # Arguments
/// - `key`: A 32-byte symmetric key.
/// - `ciphertext`: A tuple `(nonce, tag, ciphertext)` returned by the `encrypt` function.
///
/// # Returns
/// The decrypted plaintext.
///
/// # Errors
/// Returns an `EaxError` if the key is not 32 bytes long, or if the tag is invalid (authentication fails).
pub fn decrypt(
    key: &[u8],
    ciphertext: ([u8; 16], Vec<u8>, Vec<u8>),
) -> Result<Vec<u8>, EaxError> {
    if key.len() != 32 {
        return Err(EaxError::default());
    }

    let (nonce, tag, ciphertext_data) = ciphertext;

    let nonce_ga = GenericArray::from_slice(&nonce);
    let key_ga = GenericArray::from_slice(key);
    let tag_ga = GenericArray::from_slice(&tag);

    let mut cipher = AesEax::new(key_ga, &nonce_ga);

    let mut data = ciphertext_data.to_vec();
    cipher.decrypt_in_place_detached(&mut data, &tag_ga)?;

    Ok(data)
}