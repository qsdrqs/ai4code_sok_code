use rand_core::OsRng;
use p256::ecdsa::{signature::Signer, Signature, SigningKey};

fn sign(inp: &str, key: &SigningKey) -> Signature {
    key.sign(inp.as_bytes())
}

fn main() {
    // generate a new ECDSA signing key
    let sk = SigningKey::random(&mut OsRng);

    // sign the message
    let signature = sign("Hello world", &sk);

    // print the raw signature bytes
    println!("{:?}", signature.as_ref());
}