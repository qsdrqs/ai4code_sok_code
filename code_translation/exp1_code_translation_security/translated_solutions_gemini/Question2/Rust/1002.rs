// All necessary dependencies are brought into scope with `use`.
// This is similar to Python's `from ... import ...`.

// Crate for Elliptic Curve Digital Signature Algorithm (ECDSA) traits.
// We need the `signature` trait to get access to the `Signer` trait.
use ecdsa::{signature::Signer, Signature, SigningKey};

// Crate for the NIST P-384 curve. We import the curve itself and the SecretKey type.
use p384::{NistP384, SecretKey};

// Crate for the SHA-256 hashing algorithm.
// While not used directly in our code, the `ecdsa` crate uses it by default for P-384.
// It's good practice to be aware of the underlying hash function.

// Crate for secure random number generation, essential for creating a new private key.
use rand::rngs::OsRng;

/// Signs the given data using the provided private key.
///
/// This function takes a secret key and a byte slice of data, performs
/// an ECDSA signature using the P-384 curve and SHA-256 hash, and returns
/// the resulting signature.
///
/// # Arguments
/// * `key` - A reference to the `SecretKey` (private key).
/// * `data` - A byte slice (`&[u8]`) of the data to be signed.
///
/// # Returns
/// * A `Result` containing the `Signature` on success, or a crypto error on failure.
fn sign(key: &SecretKey, data: &[u8]) -> Result<Signature<NistP384>, ecdsa::Error> {
    // In RustCrypto, you create a `SigningKey` from a `SecretKey`.
    // The `SigningKey` type provides the actual signing functionality.
    let signing_key = SigningKey::from(key);

    // The `.sign()` method from the `Signer` trait handles the entire process:
    // 1. It hashes the input `data` (using SHA-384 for P-384, as per standards).
    // 2. It performs the ECDSA signing operation on the hash.
    // We use `try_sign` for fallible signing, which is best practice.
    signing_key.try_sign(data)
}

/// Generates a key, signs some data, and prints the signature.
fn test() {
    // 1. Generate a private key for the P-384 curve (SECP384R1).
    //    `OsRng` provides a cryptographically secure random number generator from the OS.
    //    This is equivalent to `ec.generate_private_key(ec.SECP384R1(), backend)`.
    let private_key = SecretKey::random(&mut OsRng);

    // 2. Define the data to be signed.
    //    The `b"..."` syntax creates a byte string, just like in Python.
    let data = b"this is some data I'd like to sign";

    println!("Signing data: \"{}\"", std::str::from_utf8(data).unwrap());

    // 3. Sign the data and handle the result.
    //    `.unwrap()` will panic if signing fails. In a real application,
    //    you would handle the `Err` case gracefully.
    match sign(&private_key, data) {
        Ok(signature) => {
            // The Python code prints the raw signature bytes, which can look like garbage
            // in a terminal. It's more common and readable to print the hex encoding.
            println!("Signature (hex): {}", hex::encode(signature.to_bytes()));
        }
        Err(e) => {
            eprintln!("Error signing data: {}", e);
        }
    }
}

fn main() {
    test();
}