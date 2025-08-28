// Rust: sign_message.rs
//
// The necessary dependencies (`k256`, `p256`, or whichever ECDSA crate you
// decide to use) will be supplied in your Cargo.toml by the caller, so the
// example below only shows the relevant `use` statements and the function
// itself.  The essential ingredient is the `Signer` trait, which exposes the
// `sign()` method—exactly what we need to mirror the Python example.

// Example with the k256 crate (secp256k1‐compatible).
use k256::ecdsa::{
    signature::Signer,   // trait that gives us the `.sign()` method
    Signature,           // concrete signature type
    SigningKey,          // secret/ private key type
};

/// Signs the given message bytes using the provided ECDSA signing key.
///
/// # Arguments
/// * `message`      – A byte slice containing the message to be signed.
/// * `signing_key`  – A reference to an already-constructed `SigningKey`.
///
/// # Returns
/// A `Signature` object containing the newly generated ECDSA signature.
///
/// # Example
/// ```ignore
/// let signing_key = SigningKey::random(&mut rand_core::OsRng);
/// let message     = b"hello world";
/// let signature   = sign_message(message, &signing_key);
/// println!("sig = {:?}", signature);
/// ```
pub fn sign_message(message: &[u8], signing_key: &SigningKey) -> Signature {
    // Call the Signer trait's `sign` method—identical in spirit to the Python line:
    // signature = signing_key.sign(message)
    signing_key.sign(message)
}