// Import the necessary traits and structs.
// The `Signer` and `Verifier` traits provide the .sign() and .verify() methods.
use ecdsa::signature::{Signer, Verifier};
// These are the concrete types for SECP256k1 keys and signatures.
use k256::ecdsa::{SigningKey, Signature, VerifyingKey};
// OsRng is a cryptographically secure random number generator.
use rand::rngs::OsRng;

/// Signs a message with the given private key and verifies it with the public key.
///
/// In idiomatic Rust, it's better to pass all dependencies as arguments
/// rather than relying on a variable from an outer scope like the Python example does.
///
/// # Arguments
/// * `sk` - A reference to the signing key (private key).
/// * `message` - The byte slice of the message to sign.
///
/// # Returns
/// * `bool` - `true` if the signature is valid, `false` otherwise.
fn sign_message(sk: &SigningKey, message: &[u8]) -> bool {
    // In Rust, we derive the verifying key directly from the signing key when needed.
    let vk: VerifyingKey = sk.verifying_key();

    // Python: sig = sk.sign(message)
    // The `sign` method returns a `Signature` object.
    let signature: Signature = sk.sign(message);

    // Python: return vk.verify(sig, message)
    // The `verify` method returns a `Result`. We check if it's `Ok` (successful)
    // to get a boolean value, which matches the Python `True`/`False` logic.
    // A failed verification returns an `Err`, so `.is_ok()` will be `false`.
    vk.verify(message, &signature).is_ok()
}

fn main() {
    // Python: sk = ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1)
    // We need a random number generator to create a secure private key.
    let mut rng = OsRng;
    let sk = SigningKey::random(&mut rng);

    // The Python code implicitly uses the `vk` from the outer scope.
    // Our Rust function is self-contained, taking the signing key and message.
    let message = b".."; // In Rust, string literals can be made into byte slices with `b""`.

    // Python: print(sign_message(sk,".."))
    let is_signature_valid = sign_message(&sk, message);
    
    println!("{}", is_signature_valid); // Will print "true"
}