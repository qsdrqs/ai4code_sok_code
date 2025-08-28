use ecdsa::{SigningKey, VerifyingKey, signature::Signer};
use sha2::Sha256;
use rand::Rng;

fn main() {
    // Generate a new ECDSA signing key
    let mut rng = rand::thread_rng();
    let sk = SigningKey::random(&mut rng, &ecdsa::SECP256k1);

    // Get the corresponding verifying key
    let vk = VerifyingKey::from(&sk);

    // Sign a message
    let message = b"helloworld";
    let signed_message = sign(message, &sk);

    // Verify the signed message
    println!("{}", vk.verify(message, &signed_message).is_ok());
}

fn sign(message: &[u8], key: &SigningKey) -> Vec<u8> {
    let signature = key.sign(message);
    signature.to_bytes().to_vec()
}