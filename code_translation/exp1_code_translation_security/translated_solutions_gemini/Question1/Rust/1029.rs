// All necessary dependencies are brought into scope.
// `aes` provides the core AES algorithm implementations (Aes128, Aes192, Aes256).
// `ecb` provides the Electronic Codebook (ECB) mode of operation.
// `cipher` provides the traits needed for block ciphers and stream ciphers,
// such as `BlockEncrypt`, `BlockDecrypt`, `KeyInit`, and `InvalidLength`.
use aes::Aes128;
use ecb::Ecb;
use cipher::{
    block_padding::NoPadding, // We implement padding manually, so we tell the cipher not to.
    BlockDecrypt, BlockEncrypt, KeyInit,
};

// In Rust, a `class` is typically represented by a `struct` for data
// and an `impl` block for methods.
// The lifetime parameter `'a` indicates that the struct holds a reference
// to a key and cannot outlive that key.
pub struct AesCipher<'a> {
    key: &'a [u8],
}

impl<'a> AesCipher<'a> {
    /// Creates a new `AesCipher`. This is equivalent to the Python `__init__`.
    /// Note: The AES block size is always 16 bytes, so `blk_sz` from the Python
    /// example is redundant and replaced with the `aes::BLOCK_SIZE` constant.
    pub fn new(key: &'a [u8]) -> Self {
        Self { key }
    }

    /// Encrypts a message using AES in ECB mode with PKCS#7 padding.
    /// Returns a `Result` containing the ciphertext or an error string.
    pub fn encrypt(&self, msg: &[u8]) -> Result<Vec<u8>, String> {
        // --- Padding ---
        // Calculate the number of bytes needed to reach a multiple of the block size.
        let block_size = aes::BLOCK_SIZE; // This is a constant equal to 16.
        let pad_len = block_size - (msg.len() % block_size);
        let pad_byte = pad_len as u8;

        // Create a new vector with the original message and the padding.
        let mut padded_msg = Vec::with_capacity(msg.len() + pad_len);
        padded_msg.extend_from_slice(msg);
        padded_msg.extend(std::iter::repeat(pad_byte).take(pad_len));

        // --- Encryption ---
        // The RustCrypto crates are generic over the AES key size. We must
        // dispatch to the correct implementation based on the key length.
        match self.key.len() {
            16 => { // AES-128
                let cipher = Ecb::<Aes128, NoPadding>::new(self.key.into());
                Ok(cipher.encrypt_vec(&padded_msg))
            },
            24 => { // AES-192
                let cipher = Ecb::<aes::Aes192, NoPadding>::new(self.key.into());
                Ok(cipher.encrypt_vec(&padded_msg))
            },
            32 => { // AES-256
                let cipher = Ecb::<aes::Aes256, NoPadding>::new(self.key.into());
                Ok(cipher.encrypt_vec(&padded_msg))
            },
            _ => Err("Invalid key length. Must be 16, 24, or 32 bytes.".to_string()),
        }
    }

    /// Decrypts a ciphertext using AES in ECB mode and removes PKCS#7 padding.
    /// Returns a `Result` containing the plaintext or an error string.
    pub fn decrypt(&self, ciphertext: &[u8]) -> Result<Vec<u8>, String> {
        // --- Sanity Check ---
        if ciphertext.len() % aes::BLOCK_SIZE != 0 {
            return Err("Invalid ciphertext length: not a multiple of block size.".to_string());
        }

        // --- Decryption ---
        // We use `?` to propagate any errors from the decryption process.
        let padded_msg = match self.key.len() {
            16 => {
                let cipher = Ecb::<Aes128, NoPadding>::new(self.key.into());
                cipher.decrypt_vec(ciphertext).map_err(|e| e.to_string())
            },
            24 => {
                let cipher = Ecb::<aes::Aes192, NoPadding>::new(self.key.into());
                cipher.decrypt_vec(ciphertext).map_err(|e| e.to_string())
            },
            32 => {
                let cipher = Ecb::<aes::Aes256, NoPadding>::new(self.key.into());
                cipher.decrypt_vec(ciphertext).map_err(|e| e.to_string())
            },
            _ => Err("Invalid key length. Must be 16, 24, or 32 bytes.".to_string()),
        }?;

        // --- Remove Padding ---
        // Get the last byte, which indicates the padding length.
        let pad_len = match padded_msg.last() {
            Some(&p) if p > 0 => p as usize,
            _ => return Err("Invalid padding: cannot determine padding length.".to_string()),
        };

        // Check if the padding length is valid.
        if pad_len > padded_msg.len() || pad_len > aes::BLOCK_SIZE {
            return Err("Invalid padding: padding length is incorrect.".to_string());
        }

        // Check if all padding bytes are correct.
        let unpadded_len = padded_msg.len() - pad_len;
        let (msg_part, padding_part) = padded_msg.split_at(unpadded_len);
        
        if padding_part.iter().any(|&b| b != pad_len as u8) {
            return Err("Invalid padding: padding bytes are incorrect.".to_string());
        }

        Ok(msg_part.to_vec())
    }
}

// Example usage to demonstrate the functionality.
fn main() {
    // A 16-byte (128-bit) key for AES-128.
    let key = b"a 16-byte key!!";
    let message = b"This is a secret message.";

    // Create a cipher instance
    let cipher = AesCipher::new(key);

    println!("Original message: {:?}", std::str::from_utf8(message).unwrap());
    println!("Key: {:?}", std::str::from_utf8(key).unwrap());

    // Encrypt the message
    match cipher.encrypt(message) {
        Ok(ciphertext) => {
            println!("Encrypted (hex): {}", hex::encode(&ciphertext));

            // Decrypt the message
            match cipher.decrypt(&ciphertext) {
                Ok(decrypted_msg) => {
                    println!("Decrypted message: {:?}", std::str::from_utf8(&decrypted_msg).unwrap());
                    assert_eq!(message, &decrypted_msg[..]);
                    println!("\nSuccess: Decrypted message matches the original!");
                }
                Err(e) => {
                    eprintln!("Decryption failed: {}", e);
                }
            }
        }
        Err(e) => {
            eprintln!("Encryption failed: {}", e);
        }
    }
    
    // Add `hex` to Cargo.toml for this example: `hex = "0.4.3"`
}