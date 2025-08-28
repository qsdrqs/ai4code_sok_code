use openssl::error::ErrorStack;
use openssl::hash::MessageDigest;
use openssl::nid::Nid;
use openssl::pkey::{PKey, Private};
use openssl::ec::{EcGroup, EcKey};
use openssl::sign::Signer;

/// Sign the given data with the provided *private* key (ECDSA-P384/SHA-256).
fn sign(key: &PKey<Private>, data: &[u8]) -> Result<Vec<u8>, ErrorStack> {
    // Create a signer configured for SHA-256.
    let mut signer = Signer::new(MessageDigest::sha256(), key)?;
    // Feed the data we want to sign.
    signer.update(data)?;
    // Return the resulting DER-encoded ECDSA signature.
    signer.sign_to_vec()
}

/// Mirrors the Python `test()` function.
fn test() -> Result<(), Box<dyn std::error::Error>> {
    // 1. Generate a new P-384 (aka secp384r1) key pair.
    let group = EcGroup::from_curve_name(Nid::SECP384R1)?;
    let ec_key = EcKey::generate(&group)?;
    let private_key = PKey::from_ec_key(ec_key)?;

    // 2. Data we want to sign.
    let data = b"this is some data I'd like to sign";

    // 3. Sign and print the signature.
    let sig = sign(&private_key, data)?;
    println!("{:?}", sig); // prints the raw DER bytes

    Ok(())
}

fn main() {
    // Just run `test()` and surface any errors.
    if let Err(e) = test() {
        eprintln!("error: {e}");
    }
}