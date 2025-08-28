extern crate ecdsa;
extern crate rand_core;
extern crate sha2;

use ecdsa::signature::{Signer, Verifier};
use ecdsa::elliptic_curve::rand_core::OsRng;
use ecdsa::elliptic_curve::sec1::ToEncodedPoint;
use ecdsa::SigningKey;
use ecdsa::signature::Signature;
use sha2::Sha256;

fn sign(sk: &SigningKey<Sha256>, message: &str) -> Signature {
    sk.sign(message.as_bytes())
}

fn test() {
    // Generate a new signing key
    let signing_key = SigningKey::<Sha256>::random(&mut OsRng);
    let verifying_key = signing_key.verifying_key();

    // Sign the message
    let signature = sign(&signing_key, "hello world");

    // Verify the signature
    let is_valid = verifying_key.verify("hello world".as_bytes(), &signature).is_ok();
    println!("{}", is_valid);
}

fn main() {
    test();
}