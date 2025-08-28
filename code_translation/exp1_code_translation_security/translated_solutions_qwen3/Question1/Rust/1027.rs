use aes::Aes128;
use block_modes::BlockMode;
use block_modes::Cbc as BlockModesCbc;
use block_padding::Pkcs7;
use generic_array::GenericArray;
use std::convert::TryInto;

// Type alias for AES-128 in CBC mode with PKCS7 padding
type Aes128Cbc = BlockModesCbc<Aes128, Pkcs7>;

/// Encrypts a message using AES-128 in CBC mode with PKCS7 padding.
///
/// # Arguments
///
/// * `message` - A string slice to be encrypted.
/// * `key` - A 16-byte key for AES-128 encryption.
///
/// # Returns
///
/// A `Vec<u8>` containing the encrypted ciphertext.
pub fn encrypt(message: &str, key: &[u8]) -> Vec<u8> {
    // Step 1: Convert message to bytes and apply PKCS7 padding
    let pt = message.as_bytes();
    let pt_padded = Pkcs7::pad(pt, 16);

    // Step 2: Ensure the key is exactly 16 bytes
    let key_arr = <&[u8; 16]>::try_into(key)
        .expect("Key must be exactly 16 bytes long");

    // Step 3: Initialize AES-128 cipher with the key
    let cipher = Aes128::new(key_arr);

    // Step 4: Use a zeroed IV (not secure for production)
    let iv = GenericArray::default(); // 16 zero bytes

    // Step 5: Create CBC mode instance
    let cbc = Aes128Cbc::new(cipher, &iv);

    // Step 6: Encrypt the padded message
    cbc.encrypt_vec(&pt_padded)
        .expect("Encryption failed")
}

/// Decrypts a message using AES-128 in CBC mode with PKCS7 padding.
///
/// # Arguments
///
/// * `ciphertext` - A slice of the encrypted data.
/// * `key` - A 16-byte key for AES-128 decryption.
///
/// # Returns
///
/// A `String` containing the decrypted message.
pub fn decrypt(ciphertext: &[u8], key: &[u8]) -> String {
    // Step 1: Ensure the key is exactly 16 bytes
    let key_arr = <&[u8; 16]>::try_into(key)
        .expect("Key must be exactly 16 bytes long");

    // Step 2: Initialize AES-128 cipher with the key
    let cipher = Aes128::new(key_arr);

    // Step 3: Use the same zeroed IV as in encryption
    let iv = GenericArray::default(); // 16 zero bytes

    // Step 4: Create CBC mode instance
    let cbc = Aes128Cbc::new(cipher, &iv);

    // Step 5: Decrypt the ciphertext
    let pt_padded = cbc.decrypt_vec(ciphertext)
        .expect("Decryption failed");

    // Step 6: Remove PKCS7 padding
    let pt = Pkcs7::unpad(&pt_padded, 16)
        .expect("Padding invalid");

    // Step 7: Convert decrypted bytes to UTF-8 string
    String::from_utf8(pt)
        .expect("Invalid UTF-8 data")
}