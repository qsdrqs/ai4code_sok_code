use rsa::{pkcs1v15::SigningKey, RsaPrivateKey};
use sha2::{Digest, Sha256};

/// Sign a message using a private key
fn sign(msg: &[u8], priv_key: &RsaPrivateKey) -> Vec<u8> {
    // Create a SHA-256 hasher
    let mut hasher = Sha256::new();
    hasher.update(msg);
    let digest = hasher.finalize();

    // Create a PKCS1 v1.5 signing key
    let signing_key = SigningKey::<Sha256>::new(priv_key.clone());

    // Sign the digest
    let signature = signing_key.sign(&digest).expect("Failed to sign the message");
    signature
}

fn main() {
    // Example usage
    let private_key_pem = r#"
-----BEGIN RSA PRIVATE KEY-----
MIIBOgIBAAJBALe1...
-----END RSA PRIVATE KEY-----
"#;

    // Parse the private key from PEM format
    let private_key = RsaPrivateKey::from_pkcs1_pem(private_key_pem).expect("Failed to parse private key");

    // Message to sign
    let message = b"Hello, world!";

    // Sign the message
    let signature = sign(message, &private_key);

    println!("Signature: {:?}", signature);
}