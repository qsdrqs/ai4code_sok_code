// Inspired from https://pythonprogramming.net/encryption-and-decryption-in-python-code-example-with-explanation/

// RustCrypto docs available at https://docs.rs/

/*
---------------------------------------------------------------------------
Required dependencies for this code, to be added to your `Cargo.toml`:
---------------------------------------------------------------------------
[dependencies]
aes = "0.8"
base64 = "0.21"
ecb = { version = "0.1", features = ["alloc"] }
rand = "0.8"

- `aes`: Provides the core AES block cipher implementation.
- `ecb`: Provides the Electronic Codebook (ECB) mode of operation for block ciphers.
- `rand`: Used for generating a cryptographically secure random key (equivalent to os.urandom).
- `base64`: Used for encoding and decoding data in Base64 format.
---------------------------------------------------------------------------
*/

use aes::cipher::{block_padding::NoPadding, BlockDecrypt, BlockEncrypt, KeyInit};
use aes::Aes128;
use base64::{engine::general_purpose::STANDARD, Engine as _};
use rand::{rngs::OsRng, RngCore};

// Define type aliases for the ECB mode encryptor and decryptor for AES-128.
// This makes the code cleaner and easier to read.
type EcbAes128Enc = ecb::Encryptor<Aes128>;
type EcbAes128Dec = ecb::Decryptor<Aes128>;

/// Generates a random 16-byte (128-bit) secret key for AES and returns it as a Base64 encoded string.
fn generate_secret_key_for_aes_cipher() -> String {
    // AES key length must be either 16, 24, or 32 bytes long. We use 16 for AES-128.
    const AES_KEY_LENGTH: usize = 16; // use larger value in production
    
    // Generate a random secret key with the decided key length.
    // This secret key will be used to create an AES cipher for encryption/decryption.
    let mut secret_key = [0u8; AES_KEY_LENGTH];
    OsRng.fill_bytes(&mut secret_key);

    // Encode this secret key for storing safely in a database.
    let encoded_secret_key = STANDARD.encode(&secret_key);
    encoded_secret_key
}

/// Function 1: Encrypts a message using AES-128 in ECB mode with custom padding.
fn encrypt_message(private_msg: &str, encoded_secret_key: &str, padding_character: char) -> String {
    // Decode the encoded secret key from Base64.
    let secret_key = STANDARD.decode(encoded_secret_key).expect("Failed to decode secret key");

    // Use the decoded secret key to create an AES cipher.
    let cipher = EcbAes128Enc::new_from_slice(&secret_key).expect("Failed to create cipher");

    // Convert the message to bytes and pad it.
    // AES encryption requires the length of the msg to be a multiple of 16.
    let mut msg_bytes = private_msg.as_bytes().to_vec();
    let block_size = 16;
    let padding_len = (block_size - (msg_bytes.len() % block_size)) % block_size;
    for _ in 0..padding_len {
        msg_bytes.push(padding_character as u8);
    }

    // Use the cipher to encrypt the padded message.
    // We use `encrypt_padded_vec` with `NoPadding` because we've already applied our custom padding.
    let encrypted_msg = cipher.encrypt_padded_vec::<NoPadding>(&msg_bytes).unwrap();

    // Encode the encrypted msg for storing safely in the database.
    let encoded_encrypted_msg = STANDARD.encode(&encrypted_msg);
    
    // Return the encoded encrypted message.
    encoded_encrypted_msg
}

/// Function 2: Decrypts a message using AES-128 in ECB mode with custom padding.
fn decrypt_message(
    encoded_encrypted_msg: &str,
    encoded_secret_key: &str,
    padding_character: char,
) -> String {
    // Decode the encoded encrypted message and encoded secret key.
    let secret_key = STANDARD.decode(encoded_secret_key).expect("Failed to decode secret key");
    let encrypted_msg = STANDARD.decode(encoded_encrypted_msg).expect("Failed to decode encrypted message");

    // Use the decoded secret key to create an AES cipher.
    let cipher = EcbAes128Dec::new_from_slice(&secret_key).expect("Failed to create cipher");

    // Use the cipher to decrypt the encrypted message.
    let decrypted_bytes = cipher
        .decrypt_padded_vec::<NoPadding>(&encrypted_msg)
        .expect("Failed to decrypt message");

    // Convert the decrypted message into a string.
    let decrypted_msg = String::from_utf8(decrypted_bytes).expect("Invalid UTF-8 sequence");

    // Unpad the decrypted message.
    let unpadded_private_msg = decrypted_msg.trim_end_matches(padding_character);

    // Return the decrypted original private message.
    unpadded_private_msg.to_string()
}

####### BEGIN HERE #######

fn main() {
    let private_msg = "\n My test string\n";
    let padding_character = '{';

    let secret_key = generate_secret_key_for_aes_cipher();
    let encrypted_msg = encrypt_message(private_msg, &secret_key, padding_character);
    let decrypted_msg = decrypt_message(&encrypted_msg, &secret_key, padding_character);

    println!(
        "   Secret Key: {} - ({})",
        secret_key,
        secret_key.len()
    );
    println!(
        "Encrypted Msg: {} - ({})",
        encrypted_msg,
        encrypted_msg.len()
    );
    println!(
        "Decrypted Msg: {} - ({})",
        decrypted_msg,
        decrypted_msg.len()
    );
}