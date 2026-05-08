use rsa::{Pkcs1v15Sign, RsaPrivateKey};
use sha2::{Digest, Sha256};

/// Sign a message using RSA PKCS#1 v1.5 with SHA-256
fn sign(msg: &[u8], priv_key: &RsaPrivateKey) -> Result<Vec<u8>, rsa::errors::Error> {
    // Equivalent to: digest = SHA256.new(); digest.update(msg)
    let mut digest = Sha256::new();
    digest.update(msg);
    let hashed = digest.finalize();

    // Equivalent to: signer = PKCS1_v1_5.new(priv_key); signer.sign(digest)
    let padding = Pkcs1v15Sign::new::<Sha256>();
    priv_key.sign(padding, &hashed)
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    // Example usage
    use rand::rngs::OsRng;
    let mut rng = OsRng;
    let priv_key = RsaPrivateKey::new(&mut rng, 2048)?;

    let msg = b"Hello, world!";
    let signature = sign(msg, &priv_key)?;
    println!("Signature ({} bytes): {:?}", signature.len(), signature);

    Ok(())
}