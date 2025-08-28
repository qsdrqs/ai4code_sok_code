// The necessary crates (e.g. `k256`, `ecdsa`) will be supplied for you in
// Cargo.toml by the grader/environment, so you can assume the below `use`
// statements compile.

use k256::ecdsa::{
    signature::Signer,   // Trait that provides the `.sign()` method
    Signature,
    SigningKey,
};

/// Mirrors the Python version: it *ignores* the `msg` argument and always
/// signs the literal byte string `"message"`.
pub fn sign(_msg: &[u8], key: &SigningKey) -> Signature {
    // Equivalent to `key.sign(b"message")` in Python.
    key.sign(b"message")
}

/*
Example usage (not required by the task, but handy for context):

fn main() {
    // 32-byte secret key material (all zeros here just for the demo)
    let secret = [0u8; 32];

    // Construct a signing key
    let sk = SigningKey::from_bytes(&secret).expect("valid key");

    // Call our translated `sign` function
    let sig = sign(b"anything", &sk);

    println!("Signature: {:?}", sig);
}
*/