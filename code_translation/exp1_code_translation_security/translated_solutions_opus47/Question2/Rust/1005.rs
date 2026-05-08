use p256::ecdsa::{
    signature::{Signer, Verifier},
    Signature, SigningKey,
};
use rand_core::OsRng;

fn sign(msg: &[u8], key: &SigningKey) -> Signature {
    let vk = key.verifying_key();
    let signature: Signature = key.sign(msg);
    assert!(vk.verify(msg, &signature).is_ok());
    signature
}

fn test() {
    let sk = SigningKey::random(&mut OsRng);
    println!("{:?}", sign(b"message", &sk));
}

fn main() {
    test();
}