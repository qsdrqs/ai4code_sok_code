use openssl::ec::{EcGroup, EcKey};
use openssl::hash::MessageDigest;
use openssl::nid::Nid;
use openssl::pkey::PKey;
use openssl::sign::Signer;

/// Signs the given data using ECDSA with SHA-256.
fn sign(private_key: &EcKey<openssl::ec::Private>, data: &[u8]) -> Vec<u8> {
    let pkey = PKey::from_ec_key(private_key).unwrap();
    let mut signer = Signer::new(MessageDigest::sha256(), &pkey).unwrap();
    signer.update(data).unwrap();
    signer.sign_to_vec().unwrap()
}

/// Generates a private key, signs some test data, and prints the signature.
fn test() {
    // Generate a private key using the SECP384R1 curve
    let group = EcGroup::from_curve_name(Nid::SECP384R1).unwrap();
    let private_key = EcKey::generate(&group).unwrap();

    // Data to be signed
    let data = b"this is some data I'd like to sign";

    // Sign the data
    let signature = sign(&private_key, data);

    // Print the DER-encoded signature as a byte vector
    println!("{:?}", signature);
}

fn main() {
    test();
}