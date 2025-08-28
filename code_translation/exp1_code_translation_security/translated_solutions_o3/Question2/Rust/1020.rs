use rand_core::OsRng;
use k256::ecdsa::{
    signature::{Signer, Verifier},
    Signature, SigningKey, VerifyingKey,
};

fn sign_and_verify(sk: &SigningKey, message: &[u8]) -> bool {
    // Sign the message
    let sig: Signature = sk.sign(message);

    // Obtain the corresponding verifying key
    let vk: VerifyingKey = sk.verifying_key();

    // Verify the signature (returns `true` on success)
    vk.verify(message, &sig).is_ok()
}

fn main() {
    // Randomly generate a new signing (secret) key on the secp256k1 curve
    let sk = SigningKey::random(&mut OsRng);

    // Message to be signed
    let message = b"..";

    // Sign and verify
    println!("{}", sign_and_verify(&sk, message));
}