use k256::ecdsa::{SigningKey, Signature};
use rand::rngs::OsRng;

fn sign(message: &[u8], key: &SigningKey) -> Signature {
    use k256::ecdsa::signature::Signer;
    key.sign(message)
}

fn main() {
    let signing_key = SigningKey::random(&mut OsRng);
    let message = b"i am a message";
    let signature = sign(message, &signing_key);
    
    println!("{:?}", signature);
}