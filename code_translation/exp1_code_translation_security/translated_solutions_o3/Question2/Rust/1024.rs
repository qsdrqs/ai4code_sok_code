// main.rs
use k256::ecdsa::{
    signature::Signer,  // trait that gives us the `.sign()` method
    Signature,          // concrete ECDSA signature type
    SigningKey,         // wrapper around a secp256k1 secret key
};
use rand_core::OsRng;    // secure RNG for key generation

/// Creates an ECDSA signature for `message` with the given `private_key`.
fn generate_ecdsa_signed_msg(message: &[u8], private_key: &SigningKey) -> Signature {
    // `SigningKey` implements the `Signer` trait, whose `sign()` method
    // returns a `Signature`.
    private_key.sign(message)
}

fn main() {
    // ----- Generate key pair -----
    let mut rng = OsRng;                         // system RNG
    let private_key = SigningKey::random(&mut rng);
    let _public_key = private_key.verifying_key(); // unused, but shows parity with Python

    // ----- Sign a message -----
    let message = "My test message";
    let signature = generate_ecdsa_signed_msg(message.as_bytes(), &private_key);

    // ----- Display result -----
    // `Signature` implements `Debug`, so we can print it directly.
    // You can also format it as DER or raw (r,s) if preferred.
    println!("{:?}", signature);
}