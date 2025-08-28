// Cargo.toml (for reference only)
//
// [dependencies]
// p384      = { version = "0.13", features = ["ecdsa"] }
// rand_core = "0.6"

use p384::ecdsa::{
    signature::{Signer, Verifier},  // trait imports
    Signature, SigningKey, VerifyingKey,
};
use rand_core::OsRng;               // secure RNG

// Sign the passed-in message with the supplied secret key.
fn sign(sk: &SigningKey, message: &[u8]) -> Signature {
    sk.sign(message)
}

fn main() {
    let message = b"hehehe";

    // Generate a fresh signing (private) key.
    let sk = SigningKey::random(&mut OsRng);

    // Produce the signature.
    let sig = sign(&sk, message);

    // Obtain the corresponding verifying (public) key.
    let vk = VerifyingKey::from(&sk);

    // Verify the signature and print the result.
    // `verify` returns `Ok(())` on success, an error otherwise.
    let is_valid = vk.verify(message, &sig).is_ok();
    println!("{is_valid}");
}