use p256::ecdsa::{SigningKey, Signature};
use rand_core::OsRng;
use sha2::{Digest, Sha256};

fn sign(inp: &str, key: &SigningKey) -> Vec<u8> {
    // Hash the input string using SHA-256
    let hash = Sha256::digest(inp.as_bytes());

    // Sign the hash using the ECDSA private key
    let signature: Signature = key.sign_digest::<Sha256>(&hash.into());

    // Convert the signature to a byte vector (DER-encoded)
    signature.to_vec()
}

fn main() {
    // Generate a new ECDSA private key using P-256 curve
    let sk = SigningKey::random(&mut OsRng);

    // Sign the string "Hello world"
    let signature = sign("Hello world", &sk);

    // Print the DER-encoded signature as a byte vector
    println!("{signature:?}");
}