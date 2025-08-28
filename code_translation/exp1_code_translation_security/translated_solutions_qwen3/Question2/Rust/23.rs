use ecdsa::{SigningKey, Signer};
use p192::NistP192;
use rand::rngs::OsRng;

// Function to sign a message using a given signing key
fn sign(message: &[u8], key: &SigningKey<NistP192>) -> Vec<u8> {
    let signature = key.sign(message);
    signature.to_der().as_bytes().to_vec()
}

fn main() {
    // Generate a new random signing key
    let key = SigningKey::<NistP192>::random(&mut OsRng);

    // Message to be signed
    let message = b"i am a message";

    // Sign the message
    let signature = sign(message, &key);

    // Print the DER-encoded signature
    println!("{:?}", signature);
}