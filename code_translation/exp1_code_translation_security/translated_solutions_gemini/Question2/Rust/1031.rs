use rsa::{pkcs1v15::SigningKey, RsaPrivateKey};
use rsa::signature::{SignatureEncoding, Signer};
use sha2::{Sha256, Digest};

/// Signs a message using an RSA private key with PKCS#1 v1.5 padding and SHA-256.
///
/// # Arguments
/// * `msg` - The message to sign as a byte slice.
/// * `priv_key` - A reference to the RSA private key.
///
/// # Returns
/// A `Vec<u8>` containing the signature.
///
/// # Panics
/// This function will panic if the signing process fails. In a real-world
/// application, you should handle the `Result` returned by the signing function.
fn sign(msg: &[u8], priv_key: &RsaPrivateKey) -> Vec<u8> {
    // In Rust, the signing key is created by combining the private key
    // with the desired padding and hash scheme.
    // SigningKey<Sha256> signifies that we will be signing a SHA-256 hash.
    let signing_key = SigningKey::<Sha256>::new_with_prefix(priv_key.clone());

    // The `sign` method from the `Signer` trait handles hashing the message
    // and then signing the digest in one step.
    let signature = signing_key.sign(msg);

    // Return the signature as a raw byte vector.
    signature.to_vec()
}

fn main() {
    // 1. Generate a new RSA private key for demonstration purposes.
    // In a real application, you would load this from a file or a secure store.
    let mut rng = rand::thread_rng();
    let bits = 2048;
    let priv_key = RsaPrivateKey::new(&mut rng, bits).expect("failed to generate a key");

    // 2. The message to be signed.
    let msg = b"This is the message to be signed.";

    // 3. Sign the message.
    let signature = sign(msg, &priv_key);

    // 4. Print the results.
    println!("Message: \"{}\"", std::str::from_utf8(msg).unwrap());
    println!("Signature (hex): {}", hex::encode(&signature));
    println!("Signature length: {} bytes", signature.len());

    // (Optional) Verify the signature with the public key to confirm it's correct.
    use rsa::pkcs1v15::VerifyingKey;
    use rsa::signature::Verifier;

    let pub_key = priv_key.to_public_key();
    let verifying_key = VerifyingKey::<Sha256>::new_with_prefix(pub_key);
    
    match verifying_key.verify(msg, &rsa::pkcs1v15::Signature::try_from(signature.as_slice()).unwrap()) {
        Ok(_) => println!("\nSignature is valid!"),
        Err(e) => println!("\nSignature verification failed: {}", e),
    }
}