use k256::ecdsa::{SigningKey, Signature};
use k256::elliptic_curve::rand_core::OsRng;

fn my_function(message: &[u8]) -> (SigningKey, Signature) {
    // Generate a new signing key using the SECP256k1 curve
    let signing_key = SigningKey::random(&mut OsRng);

    // Sign the message
    let signature = signing_key.sign(message);

    (signing_key, signature)
}