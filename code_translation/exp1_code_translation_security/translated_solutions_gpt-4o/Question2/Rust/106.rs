use k256::ecdsa::{SigningKey, VerifyingKey, signature::{Signer, Verifier}};
use k256::elliptic_curve::rand_core::OsRng;

/// Signs a given message using a given ECDSA signing key
///
/// Args:
///     message: input message
///     key: ECDSA key used to sign the message
///
/// Returns:
///     signed message
fn sign(message: &[u8], key: &SigningKey) -> Vec<u8> {
    let signature = key.sign(message);
    signature.as_ref().to_vec()
}

fn main() {
    // Generate a new ECDSA signing key using the SECP256k1 curve
    let signing_key = SigningKey::random(&mut OsRng);
    let verifying_key = VerifyingKey::from(&signing_key);

    // Sign the message
    let message = b"helloworld";
    let signed_message = sign(message, &signing_key);

    // Verify the signature
    let signature = k256::ecdsa::Signature::from_bytes(&signed_message).expect("Invalid signature format");
    let is_valid = verifying_key.verify(message, &signature).is_ok();

    println!("{}", is_valid); // Should print "true" if the signature is valid
}