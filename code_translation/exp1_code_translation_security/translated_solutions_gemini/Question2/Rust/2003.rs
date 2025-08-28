// All dependencies are brought into scope with `use` statements.
use k256::ecdsa::{SigningKey, Signature};
use signature::Signer; // Provides the sign() method
use rand::rngs::OsRng; // A cryptographically secure random number generator

/// Signs an input string with the given signing key.
///
/// # Arguments
/// * `inp` - The input string slice to sign.
/// * `key` - A reference to the ECDSA SigningKey.
///
/// # Returns
/// The resulting ECDSA signature.
fn sign(inp: &str, key: &SigningKey) -> Signature {
    // The `sign` method from the `Signer` trait takes a byte slice.
    // In Rust, we can get a byte slice view of a string with `.as_bytes()`.
    // This is equivalent to Python's `inp.encode('utf-8')`.
    key.sign(inp.as_bytes())
}

fn main() {
    // 1. Generate a new private key.
    // This is equivalent to Python's `SigningKey.generate()`.
    // We use a cryptographically secure random number generator from the OS.
    let sk = SigningKey::random(&mut OsRng);

    // 2. Sign the message "Hello world" with the generated key.
    let message = "Hello world";
    let signature = sign(message, &sk);

    // 3. Print the signature.
    // The signature type has a built-in formatter that prints it as a hexadecimal string.
    println!("Original Message: {}", message);
    println!("Signature: {}", signature);
}