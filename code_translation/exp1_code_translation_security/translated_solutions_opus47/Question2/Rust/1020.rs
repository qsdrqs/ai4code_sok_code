use k256::ecdsa::{
    signature::{Signer, Verifier},
    Signature, SigningKey, VerifyingKey,
};
use rand_core::OsRng;

fn main() {
    let sk = SigningKey::random(&mut OsRng);
    let vk = VerifyingKey::from(&sk);

    // Closure captures `vk` from the outer scope, mirroring the Python version
    let sign_message = |sk: &SigningKey, message: &[u8]| -> bool {
        let sig: Signature = sk.sign(message);
        vk.verify(message, &sig).is_ok()
    };

    println!("{}", sign_message(&sk, b".."));
}