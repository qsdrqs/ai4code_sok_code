use p384::ecdsa::{SigningKey, VerifyingKey, Signature};
use rand_core::OsRng;

fn sign(sk: &SigningKey, message: &[u8]) -> Signature {
    sk.sign(message)
}

fn main() {
    let message = b"hehehe";

    // Generate a random signing key using the system's RNG
    let mut rng = OsRng;
    let sk = SigningKey::random(&mut rng);

    // Sign the message
    let sig = sign(&sk, message);

    // Get the verifying key and verify the signature
    let vk = sk.verifying_key();
    println!("{}", vk.verify(message, &sig).is_ok());
}