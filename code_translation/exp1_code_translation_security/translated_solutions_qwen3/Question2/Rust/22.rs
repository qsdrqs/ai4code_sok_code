use k256::ecdsa::SigningKey;
use rand_core::OsRng;

fn myfunction(message: &[u8]) {
    let sk = SigningKey::random(&mut OsRng);
    let _sig = sk.sign(message);
}