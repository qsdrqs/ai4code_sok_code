// --- Dependencies (Provided for a complete example) ---

/// A struct to represent a digital signature.
/// In a real-world scenario, this would be a fixed-size array or a
/// struct from a cryptographic library (e.g., ed25519_dalek::Signature).
/// We derive `Debug` to allow printing it for demonstration.
#[derive(Debug, PartialEq)]
pub struct Signature(pub Vec<u8>);

/// A struct to represent a secret key used for signing.
pub struct SecretKey {
    // In a real implementation, this would hold private key material.
    key_data: Vec<u8>,
}

impl SecretKey {
    /// A method to sign a message.
    ///
    /// NOTE: This is a mock implementation for demonstration purposes.
    /// A real implementation would use a secure cryptographic algorithm.
    /// **DO NOT USE THIS IN PRODUCTION.**
    pub fn sign(&self, message: &[u8]) -> Signature {
        // For this example, our "signature" is just the key data
        // concatenated with the message.
        let mut signature_bytes = self.key_data.clone();
        signature_bytes.extend_from_slice(message);
        Signature(signature_bytes)
    }
}

// --- Translated Function ---

/// Signs a message `m` using a secret key `sk`.
///
/// # Arguments
/// * `m` - A byte slice representing the message to be signed.
/// * `sk` - A reference to the secret key.
pub fn sign(m: &[u8], sk: &SecretKey) -> Signature {
    sk.sign(m)
}

// --- Example Usage ---

fn main() {
    // 1. Create a mock secret key.
    let secret_key = SecretKey {
        key_data: vec![1, 2, 3, 4],
    };

    // 2. Define the message to be signed.
    let message = b"hello, world";

    // 3. Call the sign function.
    let signature = sign(message, &secret_key);

    // 4. Print the result.
    println!("Message: {:?}", String::from_utf8_lossy(message));
    println!("Signature: {:?}", signature);

    // The output will be the concatenation of the key and message data.
    // Signature: Signature([1, 2, 3, 4, 104, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100])
}