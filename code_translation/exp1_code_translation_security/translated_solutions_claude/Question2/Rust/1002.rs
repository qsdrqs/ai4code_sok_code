use p256::ecdsa::{SigningKey, Signature};
use p256::elliptic_curve::rand_core::OsRng;
use sha2::{Sha256, Digest};

fn sign(key: &SigningKey, data: &[u8]) -> Signature {
    // Hash the data with SHA256
    let mut hasher = Sha256::new();
    hasher.update(data);
    let hash = hasher.finalize();
    
    // Sign the hash
    key.sign(&hash)
}

fn test() {
    // Generate a private key (Note: using SECP256R1 instead of SECP384R1)
    let private_key = SigningKey::random(&mut OsRng);
    let data = b"this is some data I'd like to sign";
    
    let signature = sign(&private_key, data);
    println!("{:?}", signature);
}

fn main() {
    test();
}