use rand_core::OsRng;                       // RNG for key-generation
use p256::ecdsa::{signature::Signer,        // Signer trait
                 Signature, SigningKey};    // Key / signature types

// Sign a message with the supplied ECDSA private key.
fn sign(message: &str, key: &SigningKey) -> Signature {
    key.sign(message.as_bytes())
}

fn main() {
    // Generate a random private key (NIST P-256 curve by default).
    let signing_key = SigningKey::random(&mut OsRng);

    // Sign the string "something".
    let signature = sign("something", &signing_key);

    // Print the DER-encoded signature bytes.
    println!("{:?}", signature.as_ref());
}