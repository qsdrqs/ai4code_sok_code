use rand_core::OsRng;                                       // RNG for key-generation
use p192::ecdsa::{
    signature::{Signer, Verifier},                         // Traits
    Signature, SigningKey, VerifyingKey,                   // Types we need
};

/// Sign `msg` with the supplied signing key
fn sign(msg: &[u8], sk: &SigningKey) -> Signature {
    sk.sign(msg)                                           // returns an `ecdsa::Signature`
}

fn main() {
    // Generate a fresh NIST-P192 (secp192r1) key-pair
    let sk = SigningKey::random(&mut OsRng);               // private key
    let vk = VerifyingKey::from(&sk);                      // public  key

    let msg = b"message";

    // Create a signature and immediately verify it
    let sig = sign(msg, &sk);
    assert!(vk.verify(msg, &sig).is_ok());
}