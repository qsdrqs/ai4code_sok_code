// Cargo.toml dependencies:
// [dependencies]
// k256 = { version = "0.13", features = ["ecdsa"] }
// rand_core = { version = "0.6", features = ["getrandom"] }

use k256::ecdsa::{signature::Signer, Signature, SigningKey, VerifyingKey};
use rand_core::OsRng;

fn generate_ecdsa_signed_msg(message: &str, private_key: &SigningKey) -> Signature {
    private_key.sign(message.as_bytes())
}

fn main() {
    // Generate Keys
    let private_key = SigningKey::random(&mut OsRng);
    let _public_key = VerifyingKey::from(&private_key);

    let message = "My test message";
    println!("{:?}", generate_ecdsa_signed_msg(message, &private_key));
}