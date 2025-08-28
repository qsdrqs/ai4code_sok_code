use ecdsa::{SigningKey, VerifyingKey, signature::Signer, signature::Verifier};
use rand::rngs::StdRng;
use rand::SeedableRng;

fn ecdsa_key(message: &[u8]) -> Vec<u8> {
    let mut rng = StdRng::seed_from_u64(0x1234567890abcdef);
    let sk = SigningKey::random(&mut rng);
    let vk = VerifyingKey::from(&sk);
    let signature = sk.sign(message);
    println!("{}", vk.verify(message, &signature).is_ok());
    signature.to_bytes().to_vec()
}

fn ecdsa_verify(signature: &[u8], message: &[u8], vk: &VerifyingKey) -> bool {
    vk.verify(message, signature).is_ok()
}

fn ecdsa_genkey() -> (SigningKey, VerifyingKey) {
    let mut rng = StdRng::seed_from_u64(0x1234567890abcdef);
    let sk = SigningKey::random(&mut rng);
    let vk = VerifyingKey::from(&sk);
    (sk, vk)
}

fn ecdsa_sign(sk: &SigningKey, message: &[u8]) -> Vec<u8> {
    sk.sign(message).to_bytes().to_vec()
}

fn ecdsa_verify_key(vk: &VerifyingKey, signature: &[u8], message: &[u8]) -> bool {
    vk.verify(message, signature).is_ok()
}

fn main() {
    let message = b"This is a test message";
    let (sk, vk) = ecdsa_genkey();
    println!("{:?}", sk.to_string());
    println!("{:?}", vk.to_string());
    let signature = ecdsa_sign(&sk, message);
    println!("{:?}", signature);
    println!("{}", ecdsa_verify_key(&vk, &signature, message));
}