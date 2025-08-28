use k256::ecdsa::{SigningKey, signature::Signer};

/// Signs a message using an ECDSA private key on the SECP256k1 curve.
///
/// # Arguments
/// - `message`: A byte slice representing the message to be signed.
/// - `private_key`: A 32-byte slice representing the ECDSA private key.
///
/// # Returns
/// A `Vec<u8>` containing the DER-encoded ECDSA signature.
fn sign_message(message: &[u8], private_key: &[u8]) -> Vec<u8> {
    // Convert the private key bytes into a SigningKey
    let secret_key = SigningKey::from_bytes(private_key)
        .expect("Invalid private key length or value");

    // Sign the message
    let signature = secret_key.sign(message);

    // Return the DER-encoded signature as a byte vector
    signature.to_vec()
}

fn main() {
    // Example usage
    let message = "hello".as_bytes();
    let private_key = "qwertyuiopasdfghjklzxcvbnmqwerty".as_bytes();

    let sig = sign_message(message, private_key);
    println!("Signature: {:?}", sig);
}