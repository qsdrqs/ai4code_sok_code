use ecdsa::{SigningKey, Signature};
use k256::Secp256k1;
use rand::rngs::OsRng;

fn sign(inp: &str, key: &SigningKey<Secp256k1>) -> Signature<Secp256k1> {
    key.sign(inp.as_bytes())
}

fn main() {
    let sk = SigningKey::random(&mut OsRng);
    println!("{:?}", sign("Hello world", &sk));
}