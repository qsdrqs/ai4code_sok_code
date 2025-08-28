use ecdsa::{SigningKey, VerifyingKey, signature::Signer};
use ecdsa::elliptic_curve::sec1::ToEncodedPoint;
use ecdsa::elliptic_curve::Curve;
use ecdsa::elliptic_curve::curve_types::nist384p;

fn main() {
    let message = b"hehehe";
    let sk = SigningKey::random(nist384p::NIST384P);

    let sig = sk.sign(message);
    let vk = sk.verifying_key();

    assert!(vk.verify(message, &sig).is_ok());
}