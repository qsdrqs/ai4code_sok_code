use ecdsa::{SigningKey, VerifyingKey};
use secp256k1::Secp256k1;

// Define a function to sign a message using a given ECDSA signing key
fn sign_message(message: &[u8], curve: &str) -> Vec<u8> {
    // Define the curve
    let secp = Secp256k1::new();
    let signing_key = match curve {
        "secp256k1" => SigningKey::new(&secp, &secp256k1::curve::Secp256k1),
        _ => panic!("Unsupported curve"),
    };

    // Sign the message
    let signature = signing_key.sign(&secp, message);

    // Return the signature as a vector of bytes
    signature.to_bytes().to_vec()
}

fn main() {
    // Example usage
    let message = b"Hello, World!";
    let curve = "secp256k1";
    let signature = sign_message(message, curve);
    println!("{:?}", signature);
}