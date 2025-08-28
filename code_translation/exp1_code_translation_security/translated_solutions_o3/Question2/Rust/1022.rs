// ── main.rs ──────────────────────────────────────────────────────────────
//
// Cargo dependencies you will need:
//
// [dependencies]
// rand_core = "0.6"              # OsRng for secure randomness
// k256      = { version = "0.13", features = ["ecdsa"] }
//
// ─────────────────────────────────────────────────────────────────────────

use k256::ecdsa::{
    signature::{Signer, Verifier},   // traits
    Signature,                       // concrete signature type
    SigningKey, VerifyingKey,
};
use rand_core::OsRng;                // secure random number generator

fn main() {
    // 1. Generate a new private key (SigningKey) + its public key
    let private_key = SigningKey::random(&mut OsRng);   // same as `PrivateKey()`
    let public_key: VerifyingKey = private_key.verifying_key();

    // 2. The message we want to sign
    let message = b"My test message";

    // 3. Sign the message
    let signature: Signature = private_key.sign(message);

    // 4. Verify the signature
    let is_valid = public_key.verify(message, &signature).is_ok();

    println!("{}", is_valid);        // true
}