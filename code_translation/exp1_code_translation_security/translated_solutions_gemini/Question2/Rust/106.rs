use k256::ecdsa::{SigningKey, VerifyingKey, Signature};
use ecdsa::{Signer, Verifier}; // Import the Signer and Verifier traits
use rand::thread_rng;

/// Signs a given message using a given ECDSA signing key.
///
/// # Args
/// * `message`: The input message as a byte slice.
/// * `key`: A reference to the ECDSA key used to sign the message.
///
/// # Return
/// The resulting `Signature`.
fn sign(message: &[u8], key: &SigningKey) -> Signature {
    // The `sign` method comes from the `Signer` trait.
    // It takes the message and returns a `Signature` struct.
    key.sign(message)
}

fn main() {
    // 1. Generate a new private key (SigningKey)
    // The `k256` crate implies the curve is SECP256k1.
    // We need a random number generator to create the key.
    let mut rng = thread_rng();
    let sk = SigningKey::random(&mut rng);

    // 2. Derive the public key (VerifyingKey) from the private key
    let vk: VerifyingKey = sk.verifying_key();

    // The message to be signed
    let message = b"helloworld";

    // 3. Sign the message with the private key
    let signed_message = sign(message, &sk);

    // 4. Verify the signature with the public key
    // The `verify` method returns a `Result`. It's `Ok(())` on success
    // and `Err` on failure. We use `is_ok()` to get a boolean `true`/`false`
    // to match the Python example's output.
    let is_valid = vk.verify(message, &signed_message).is_ok();

    // Print the boolean result
    println!("{}", is_valid); // Should print "true"
}