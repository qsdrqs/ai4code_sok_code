use ecdsa::{SigningKey, Signature, signature::Signer, signature::Verifier};
use p256::NistP256;
use rand::rngs::OsRng;

fn sign(msg: &[u8], key: &SigningKey<NistP256>) -> Signature<NistP256> {
    let vk = key.verifying_key();
    let signature: Signature<NistP256> = key.sign(msg);
    
    // Verify the signature (equivalent to assert)
    vk.verify(msg, &signature).expect("Signature verification failed");
    
    signature
}

fn test() {
    let sk = SigningKey::random(&mut OsRng);
    println!("{:?}", sign(b"message", &sk));
}

fn main() {
    test();
}