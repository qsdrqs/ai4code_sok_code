use ecdsa::SigningKey;
use k256::Secp256k1;
use rand_core::OsRng;
use signature::{Signer, Verifier};
use digest::Digest;
use sha1::Sha1;

/// Signs a message and verifies the signature using the same key.
fn sign_message(sk: &SigningKey<Secp256k1>, message: &str) -> bool {
    // Hash the message using SHA-1
    let digest = Sha1::digest(message.as_bytes());

    // Sign the digest
    let signature = sk.sign(&digest);

    // Get the verifying key from the signing key
    let vk = sk.verifying_key();

    // Verify the signature
    vk.verify(&digest, &signature).is_ok()
}

fn main() {
    // Generate a random signing key
    let sk = SigningKey::<Secp256k1>::random(&mut OsRng);

    // Test the function with the message ".."
    let result = sign_message(&sk, "..");

    // Print the result (should be true)
    println!("{}", result);
}