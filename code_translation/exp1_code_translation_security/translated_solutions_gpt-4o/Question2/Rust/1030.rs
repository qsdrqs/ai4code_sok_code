use k256::{
    ecdsa::{SigningKey, Signature, VerifyingKey},
    elliptic_curve::sec1::ToEncodedPoint,
    Scalar,
};
use sha3::{Digest, Sha3_256};
use rand_core::OsRng;

fn sha3_256_hash(msg: &str) -> Scalar {
    let mut hasher = Sha3_256::new();
    hasher.update(msg.as_bytes());
    let hash_bytes = hasher.finalize();
    Scalar::from_bytes_reduced(hash_bytes.as_slice().into())
}

fn sign_ecdsa_secp256k1(msg: &str, priv_key: &SigningKey) -> Signature {
    let msg_hash = sha3_256_hash(msg);
    priv_key.sign(msg_hash.as_bytes())
}

fn verify_ecdsa_secp256k1(msg: &str, signature: &Signature, pub_key: &VerifyingKey) -> bool {
    let msg_hash = sha3_256_hash(msg);
    pub_key.verify(msg_hash.as_bytes(), signature).is_ok()
}

fn main() {
    // ECDSA sign message (using the curve secp256k1 + SHA3-256)
    let msg = "Message for ECDSA signing";

    // Generate a random private key
    let signing_key = SigningKey::random(&mut OsRng); // Signing key
    let verifying_key = VerifyingKey::from(&signing_key); // Corresponding public key

    // Sign the message
    let signature = sign_ecdsa_secp256k1(msg, &signing_key);

    // Print the results
    println!("Message: {}", msg);
    println!("Private key: {:?}", signing_key.to_bytes());
    println!("Public key: {:?}", verifying_key.to_encoded_point(false));
    println!(
        "Signature: r={:x}, s={:x}",
        signature.r(),
        signature.s()
    );

    // Verify the signature
    let is_valid = verify_ecdsa_secp256k1(msg, &signature, &verifying_key);
    println!("Signature valid: {}", is_valid);
}