use k256::ecdsa::{SigningKey, Signature};
use k256::elliptic_curve::sec1::ToEncodedPoint;
use k256::elliptic_curve::SecretKey;
use sha2::{Sha256, Digest};

fn sign(message: &[u8], private_key: &[u8]) -> Result<Signature, Box<dyn std::error::Error>> {
    // Create a new ECDSA signing key from the private key bytes
    let secret_key = SecretKey::from_bytes(private_key)?;
    let signing_key = SigningKey::from(secret_key);

    // Hash the message using SHA-256
    let mut hasher = Sha256::new();
    hasher.update(message);
    let hashed_message = hasher.finalize();

    // Sign the hashed message
    let signature = signing_key.sign(&hashed_message);

    // Return the signature
    Ok(signature)
}

fn main() {
    // Example usage
    let message = b"Hello, world!";
    let private_key = [/* 32-byte private key here */];

    match sign(message, &private_key) {
        Ok(signature) => println!("Signature: {:?}", signature),
        Err(e) => eprintln!("Error signing message: {}", e),
    }
}