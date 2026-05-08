// well there's an ecdsa rust crate but that would be cheating
use p256::ecdsa::{signature::Signer, Signature, SigningKey};
// what
use rand_core::OsRng;


// sample code from p256 crate docs
// let signing_key = SigningKey::random(&mut OsRng);
// let verifying_key = signing_key.verifying_key();
// let signature: Signature = signing_key.sign(b"message");
// assert!(verifying_key.verify(b"message", &signature).is_ok());

fn sign(message: &[u8], key: &SigningKey) -> Signature {
    let _vk = key.verifying_key();
    let signature: Signature = key.sign(message);
    signature
}

fn main() {
    let key = SigningKey::random(&mut OsRng);
    println!("{:?}", sign(b"i am a message", &key));
}