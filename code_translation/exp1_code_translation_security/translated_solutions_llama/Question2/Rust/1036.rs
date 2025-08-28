use ecdsa::{SigningKey, VerifyingKey};
use ecdsa::curve::secp256k1;
use hex;
use sha2::{Sha256, Digest};

/// Signs a message with a private key using the ECDSA algorithm on the secp256k1 curve.
///
/// # Arguments
///
/// * `message`: The message to be signed.
/// * `private_key`: The private key used for signing.
///
/// # Returns
///
/// The signature of the message as a hexadecimal string.
fn sign_message(message: &str, private_key: &[u8]) -> String {
    // Hash the message
    let message_hash = Sha256::digest(message.as_bytes());

    // Sign the message
    let signing_key = SigningKey::from_slice(private_key, &secp256k1::curve());
    let signature = signing_key.sign(&message_hash);

    // Return the signature as a hexadecimal string
    hex::encode(signature)
}

fn main() {
    // Test the function
    let private_key = hex::decode("e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109").unwrap();
    let message = "Hello";
    let signature = sign_message(message, &private_key);
    println!("{}", signature);
}