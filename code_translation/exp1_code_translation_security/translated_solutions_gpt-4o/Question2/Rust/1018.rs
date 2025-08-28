use ecdsa::signature::{Signer, Signature};
use ecdsa::elliptic_curve::sec1::ToEncodedPoint;
use k256::ecdsa::{SigningKey, Signature as EcdsaSignature};

fn sign(msg: &[u8], key: &SigningKey) -> EcdsaSignature {
    key.sign(msg)
}

fn main() {
    // Example usage
    let signing_key = SigningKey::random(&mut rand::thread_rng()); // Generate a random signing key
    let message = b"message";
    let signature = sign(message, &signing_key);

    println!("Signature: {:?}", signature);
}