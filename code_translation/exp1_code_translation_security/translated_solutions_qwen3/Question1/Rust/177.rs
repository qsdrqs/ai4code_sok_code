extern crate aes_eax;
extern crate generic_array;
extern crate rand_core;
extern crate typenum;

use aes_eax::{
    Aes128Eax, Aes192Eax, Aes256Eax
};
use aes_eax::generic_array::{GenericArray, typenum::{U15, U16, U24, U32}};
use rand_core::{RngCore, OsRng};

/// Encrypts a message using AES in EAX mode.
///
/// # Arguments
/// - `m`: The plaintext message to encrypt.
/// - `sk`: The secret key (must be 16, 24, or 32 bytes long).
///
/// # Returns
/// A tuple `(ciphertext, nonce)` where:
/// - `ciphertext` is the encrypted message (Vec<u8>)
/// - `nonce` is the randomly generated nonce used for encryption (Vec<u8>)
pub fn encrypt(m: &[u8], sk: &[u8]) -> (Vec<u8>, Vec<u8>) {
    // Generate a 15-byte nonce
    let mut nonce = [0u8; 15];
    OsRng.fill_bytes(&mut nonce);

    match sk.len() {
        16 => {
            let key = GenericArray::<u8, U16>::from_slice(sk);
            let cipher = Aes128Eax::new(key);
            let nonce_arr = GenericArray::<u8, U15>::from_slice(&nonce);
            let c = cipher.encrypt(nonce_arr, m).expect("Encryption failed");
            (c, nonce.to_vec())
        },
        24 => {
            let key = GenericArray::<u8, U24>::from_slice(sk);
            let cipher = Aes192Eax::new(key);
            let nonce_arr = GenericArray::<u8, U15>::from_slice(&nonce);
            let c = cipher.encrypt(nonce_arr, m).expect("Encryption failed");
            (c, nonce.to_vec())
        },
        32 => {
            let key = GenericArray::<u8, U32>::from_slice(sk);
            let cipher = Aes256Eax::new(key);
            let nonce_arr = GenericArray::<u8, U15>::from_slice(&nonce);
            let c = cipher.encrypt(nonce_arr, m).expect("Encryption failed");
            (c, nonce.to_vec())
        },
        _ => panic!("Invalid key size: must be 16, 24, or 32 bytes"),
    }
}

/// Decrypts a message using AES in EAX mode.
///
/// # Arguments
/// - `cn`: A tuple `(ciphertext, nonce)` where:
///   - `ciphertext` is the encrypted message (Vec<u8>)
///   - `nonce` is the nonce used during encryption (Vec<u8>)
/// - `sk`: The secret key (must be 16, 24, or 32 bytes long).
///
/// # Returns
/// The decrypted plaintext message (Vec<u8>)
pub fn decrypt(cn: (Vec<u8>, Vec<u8>), sk: &[u8]) -> Vec<u8> {
    let (c, nonce) = cn;

    match sk.len() {
        16 => {
            let key = GenericArray::<u8, U16>::from_slice(sk);
            let cipher = Aes128Eax::new(key);
            let nonce_arr = GenericArray::<u8, U15>::from_slice(&nonce);
            cipher.decrypt(nonce_arr, c.as_ref()).expect("Decryption failed")
        },
        24 => {
            let key = GenericArray::<u8, U24>::from_slice(sk);
            let cipher = Aes192Eax::new(key);
            let nonce_arr = GenericArray::<u8, U15>::from_slice(&nonce);
            cipher.decrypt(nonce_arr, c.as_ref()).expect("Decryption failed")
        },
        32 => {
            let key = GenericArray::<u8, U32>::from_slice(sk);
            let cipher = Aes256Eax::new(key);
            let nonce_arr = GenericArray::<u8, U15>::from_slice(&nonce);
            cipher.decrypt(nonce_arr, c.as_ref()).expect("Decryption failed")
        },
        _ => panic!("Invalid key size: must be 16, 24, or 32 bytes"),
    }
}