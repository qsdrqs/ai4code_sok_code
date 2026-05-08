use openssl::ec::{EcGroup, EcKey};
use openssl::hash::MessageDigest;
use openssl::nid::Nid;
use openssl::pkey::{PKey, Private};
use openssl::sign::Signer;

fn sign(key: &PKey<Private>, data: &[u8]) -> Vec<u8> {
    let mut signer = Signer::new(MessageDigest::sha256(), key).unwrap();
    signer.update(data).unwrap();
    signer.sign_to_vec().unwrap()
}

fn test() {
    let group = EcGroup::from_curve_name(Nid::SECP384R1).unwrap();
    let ec_key = EcKey::generate(&group).unwrap();
    let private_key = PKey::from_ec_key(ec_key).unwrap();
    let data = b"this is some data I'd like to sign";
    println!("{:?}", sign(&private_key, data));
}

fn main() {
    test();
}