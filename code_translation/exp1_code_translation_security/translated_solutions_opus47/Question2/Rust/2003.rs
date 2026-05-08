use p256::ecdsa::{signature::Signer, Signature, SigningKey};
use rand_core::OsRng;

fn sign(inp: &str, key: &SigningKey) -> Signature {
    key.sign(inp.as_bytes())
}

fn main() {
    let sk = SigningKey::random(&mut OsRng);
    println!("{:?}", sign("Hello world", &sk));
}