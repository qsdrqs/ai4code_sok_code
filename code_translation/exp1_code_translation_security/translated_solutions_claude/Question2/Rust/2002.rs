use ecdsa::{SigningKey, VerifyingKey, Signature};
use ecdsa::signature::{Signer, Verifier};
use p256::NistP256;
use rand::rngs::OsRng;

type EcdsaSigningKey = SigningKey<NistP256>;
type EcdsaVerifyingKey = VerifyingKey<NistP256>;
type EcdsaSignature = Signature<NistP256>;

fn sign(sk: &EcdsaSigningKey, m: &str) -> EcdsaSignature {
    sk.sign(m.as_bytes())
}

fn test() {
    let sk = EcdsaSigningKey::random(&mut OsRng);
    let vk: EcdsaVerifyingKey = sk.verifying_key();
    let signature = sign(&sk, "hello world");
    
    match vk.verify("hello worfld".as_bytes(), &signature) {
        Ok(()) => println!("true"),
        Err(_) => println!("false"),
    }
}

fn main() {
    test();
}