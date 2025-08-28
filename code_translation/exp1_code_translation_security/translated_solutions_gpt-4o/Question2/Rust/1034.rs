use k256::ecdsa::{SigningKey, VerifyingKey, signature::{Signer, Verifier}};
use k256::ecdsa::Signature;
use sha2::{Sha256, Digest};

fn main() {
    // Generate a new private key
    let signing_key = SigningKey::random(&mut rand::thread_rng());
    
    // Derive the public key from the private key
    let verifying_key = VerifyingKey::from(&signing_key);

    // Message to be signed
    let message = "Test message";

    // Hash the message (ECDSA requires hashing the message before signing)
    let mut hasher = Sha256::new();
    hasher.update(message);
    let message_hash = hasher.finalize();

    // Sign the message hash
    let signature: Signature = signing_key.sign(message_hash.as_slice());

    // Verify the signature
    let is_valid = verifying_key.verify(message_hash.as_slice(), &signature).is_ok();

    // Print the verification result
    println!("{}", is_valid);
}