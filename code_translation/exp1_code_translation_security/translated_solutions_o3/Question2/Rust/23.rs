use p192::ecdsa::{
    signature::Signer,   // trait that provides the `.sign()` method
    Signature,           // concrete signature type we will return
    SigningKey,          // secret (private) key type
};
use rand_core::OsRng;     // secure RNG for key generation

// Equivalent to the Python `def sign(message, key):`
fn sign(message: &[u8], key: &SigningKey) -> Signature {
    key.sign(message)     // performs ECDSA signing, returns a `Signature`
}

fn main() {
    // `SigningKey::random` is the Rust equivalent of `SigningKey.generate()` in Python
    let signing_key = SigningKey::random(&mut OsRng);

    // Sign exactly the same message as in the Python demo
    let signature = sign(b"i am a message", &signing_key);

    // Show the result (DER-encoded, hex-formatted for readability)
    println!("Signature: {}", hex::encode(signature.to_der()));
}