use k256::ecdsa::{signature::Signer, Signature, SigningKey};
use sha2::{Digest, Sha256};
use std::error::Error;

/// Signs a message using a private key with ECDSA (SECP256k1).
///
/// # Arguments
///
/// * `message` - The string message to sign.
/// * `private_key_bytes` - A 32-byte slice representing the private key.
///
/// # Returns
///
/// A hex-encoded DER signature string, or an error if signing fails.
fn sign_message(message: &str, private_key_bytes: &[u8]) -> Result<String, Box<dyn Error>> {
    // 1. Hash the message using SHA-256.
    // The equivalent of `hashlib.sha256(message.encode('utf-8')).digest()`
    let mut hasher = Sha256::new();
    hasher.update(message.as_bytes());
    let message_hash = hasher.finalize();

    // 2. Create a signing key from the private key bytes.
    // The equivalent of `ecdsa.SigningKey.from_string(..., curve=ecdsa.SECP256k1)`
    let signing_key = SigningKey::from_bytes(private_key_bytes)?;

    // 3. Sign the message hash.
    // The `sign` method produces a signature that needs to be encoded.
    // The equivalent of `signing_key.sign_digest(..., sigencode=ecdsa.util.sigencode_der)`
    let signature: Signature = signing_key.sign(&message_hash);

    // 4. Encode the signature to DER format and then to a hex string.
    // The equivalent of `binascii.hexlify(signature).decode('utf-8')`
    let hex_signature = hex::encode(signature.to_der().as_bytes());

    Ok(hex_signature)
}

fn main() {
    // Test the function
    // The equivalent of `binascii.unhexlify(...)`
    let private_key_hex = "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
    let private_key = hex::decode(private_key_hex).expect("Failed to decode private key from hex");

    let message = "Hello";

    // Call the signing function and handle potential errors
    match sign_message(message, &private_key) {
        Ok(signature) => {
            println!("{}", signature);
        }
        Err(e) => {
            eprintln!("Error signing message: {}", e);
        }
    }
}