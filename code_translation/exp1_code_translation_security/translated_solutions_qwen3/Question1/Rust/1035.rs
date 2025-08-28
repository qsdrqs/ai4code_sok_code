extern crate aes;
extern crate ctr;
extern crate eax;
extern crate rand;
extern crate generic_array;

use aes::Aes128;
use ctr::cipher::StreamCipher;
use ctr::Aes128Ctr;
use eax::{Aead, NewAead, generic_array::GenericArray};
use rand::RngCore;
use std::option::Option;

// Define the EAX-AES128 type
type EaxAes128 = eax::Eax<Aes128>;

/// Encrypts a message using AES in EAX mode.
///
/// # Arguments
/// - `key`: A 16-byte key.
/// - `msg`: The plaintext message to encrypt.
///
/// # Returns
/// A tuple containing:
/// - `ciphertext`: The encrypted message.
/// - `tag`: The authentication tag.
/// - `nonce`: The randomly generated nonce used for encryption.
fn encrypt(key: &[u8], msg: &[u8]) -> (Vec<u8>, Vec<u8>, Vec<u8>) {
    use eax::AeadCore;

    // Convert the key into a GenericArray
    let key_arr = GenericArray::from_slice(key);

    // Create a new EAX cipher instance
    let cipher = EaxAes128::new(&key_arr);

    // Generate a random 16-byte nonce
    let nonce = cipher.generate_nonce();

    // Encrypt the message and get the ciphertext and tag
    let (ciphertext, tag) = cipher.encrypt_detached(&nonce, msg).expect("Encryption failed");

    // Return ciphertext, tag, and nonce as Vec<u8>
    (ciphertext, tag.to_vec(), nonce.to_vec())
}

/// Decrypts a message using AES in EAX mode.
///
/// # Arguments
/// - `key`: A 16-byte key.
/// - `ciphertext`: The encrypted message.
/// - `nonce`: The nonce used during encryption.
/// - `tag`: Optional authentication tag. If provided, the tag is verified.
///
/// # Returns
/// The decrypted plaintext.
fn decrypt(key: &[u8], ciphertext: &[u8], nonce: &[u8], tag: Option<&[u8]>) -> Vec<u8> {
    match tag {
        Some(tag_val) => {
            // Decrypt and verify the tag using EAX
            let key_arr = GenericArray::from_slice(key);
            let cipher = EaxAes128::new(&key_arr);
            let nonce_arr = GenericArray::from_slice(nonce);
            let tag_arr = GenericArray::from_slice(tag_val);

            cipher.decrypt_detached(&nonce_arr, ciphertext, &tag_arr)
                .expect("Decryption or tag verification failed")
        }
        None => {
            // Decrypt without verifying the tag using CTR mode
            let key_arr = GenericArray::from_slice(key);
            let nonce_arr = GenericArray::from_slice(nonce);
            let mut ctr = Aes128Ctr::new(&key_arr, &nonce_arr);
            let mut plaintext = ciphertext.to_vec();

            // Apply the keystream to decrypt
            ctr.apply_keystream(&mut plaintext);

            plaintext
        }
    }
}

fn main() {
    // Define the key (must be 16 bytes)
    let key = b"Sixteen byte key";

    // Encrypt the message
    let (ctext, tag, nonce) = encrypt(key, b"message");

    // Decrypt with tag verification
    let pt1 = decrypt(key, &ctext, &nonce, Some(&tag));
    println!("Decrypted with tag: {:?}", String::from_utf8_lossy(&pt1));

    // Decrypt without tag verification
    let pt2 = decrypt(key, &ctext, &nonce, None);
    println!("Decrypted without tag: {:?}", String::from_utf8_lossy(&pt2));
}