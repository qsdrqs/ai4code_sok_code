// Dependencies assumed to be supplied:
//
// k256       = { version = "0.13", features = ["ecdsa"] }
// rand_core  = "0.6"

use k256::ecdsa::{signature::Signer, Signature, SigningKey};

/// Signs the literal byte string b"message" with the provided ECDSA signing key,
/// mirroring the behavior of the original Python example.
fn sign_message(_message: &[u8], key: &SigningKey) -> Signature {
    key.sign(b"message")
}

fn main() {
    // Example usage:
    //
    // In practice, you'd obtain `signing_key` from some secure source
    // (e.g. be loaded from disk or derived), not generated every time.
    //
    // Here we create a random one solely to demonstrate functionality.
    let signing_key = SigningKey::random(&mut rand_core::OsRng);

    let msg = b"anything";      // not actually used by sign_message, per original code
    let sig = sign_message(msg, &signing_key);

    println!("Signature: {:?}", sig);
}