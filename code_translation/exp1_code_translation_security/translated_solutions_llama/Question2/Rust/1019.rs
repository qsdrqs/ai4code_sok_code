use ecdsa::{SigningKey, VerifyingKey, signature::Signer};
use p384::P384;
use sha2::Sha256;

// Define a function to sign a string with a private key
fn sign_string(private_key: &SigningKey<P384>, data: &[u8]) -> Vec<u8> {
    let signature = private_key.sign(data);
    signature.to_bytes().to_vec()
}

fn main() {
    // Generate a new private key
    let private_key = SigningKey::random(&mut rand::thread_rng());

    // Data to be signed
    let data = b"hello world";

    // Sign the data
    let signature = sign_string(&private_key, data);

    // Print the signature
    println!("{:?}", signature);
}