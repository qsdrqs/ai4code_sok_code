use k256::ecdsa::{
    signature::{Signer, Verifier},
    Signature, SigningKey, VerifyingKey,
};
use rand_core::OsRng;

fn main() {
    // Library used: k256 crate (secp256k1 with ECDSA + SHA-256)
    // Generate Keys
    let private_key = SigningKey::random(&mut OsRng);
    let public_key = VerifyingKey::from(&private_key);

    let message = b"My test message";

    // Generate Signature
    let signature: Signature = private_key.sign(message);

    // Verify if signature is valid
    println!("{}", public_key.verify(message, &signature).is_ok());
}