use k256::ecdsa::{SigningKey, Signature};
use k256::elliptic_curve::sec1::ToEncodedPoint;
use k256::elliptic_curve::SecretKey;
use k256::ecdsa::signature::Signer;

/// Function to sign a message using a private key
///
/// Input: message (as a byte slice) and private key (as a byte slice)
/// Output: Signature (as a Vec<u8>)
fn sign_message(message: &[u8], private_key: &[u8]) -> Vec<u8> {
    // Create a new ECDSA signing key from the private key bytes
    let secret_key = SecretKey::from_slice(private_key).expect("Invalid private key");
    let signing_key = SigningKey::from(secret_key);

    // Sign the message
    let signature: Signature = signing_key.sign(message);

    // Return the signature as a Vec<u8>
    signature.as_ref().to_vec()
}

fn main() {
    // Example usage
    let message = b"hello";
    let private_key = b"qwertyuiopasdfghjklzxcvbnmqwerty";

    let signature = sign_message(message, private_key);
    println!("Signature: {:?}", signature);
}