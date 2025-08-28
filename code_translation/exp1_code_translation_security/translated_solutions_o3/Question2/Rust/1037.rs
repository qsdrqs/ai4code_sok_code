use p384::ecdsa::{
    signature::{Signer, Verifier},
    Signature, SigningKey, VerifyingKey,
};
use rand_core::OsRng;

// Equivalent to `signMessage` in the original Python
fn sign_message(key: &SigningKey, message: &[u8]) -> Signature {
    key.sign(message)
}

fn main() {
    // 1. Generate a new ECDSA signing key on the NIST P-384 curve.
    let sk = SigningKey::random(&mut OsRng);

    // 2. Derive the corresponding verifying (public) key.
    let vk = VerifyingKey::from(&sk);

    // 3. Sign the message.
    let signature = sign_message(&sk, b"testing");

    // 4. Verify the signature and print the result.
    let verified = vk.verify(b"testing", &signature).is_ok();
    println!("{}", verified);
}