use sha2::{Sha256, Digest};
use k256::ecdsa::{SigningKey, Signature, signature::hazmat::PrehashSigner};

fn sign_message(message: &str, private_key: &[u8]) -> String {
    // Hash the message
    let mut hasher = Sha256::new();
    hasher.update(message.as_bytes());
    let message_hash = hasher.finalize();

    // Sign the message
    let signing_key = SigningKey::from_slice(private_key)
        .expect("Invalid private key");
    let signature: Signature = signing_key
        .sign_prehash(&message_hash)
        .expect("Signing failed");

    // Return the signature in DER format, hex-encoded
    hex::encode(signature.to_der().as_bytes())
}

fn main() {
    // Test the function
    let private_key = hex::decode(
        "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109"
    ).expect("Invalid hex");
    let message = "Hello";
    let signature = sign_message(message, &private_key);
    println!("{}", signature);
}