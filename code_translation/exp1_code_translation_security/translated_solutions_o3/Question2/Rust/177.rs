// Assuming that the Secret-Key type you’ll be given already implements the
// `Signer` trait from the `signature` crate (or an equivalent trait in the
// provided dependencies).

use signature::Signer; // <- comes from the `signature` ecosystem crate

/// Sign `message` with the secret key `sk`.
///
/// `S::Signature` is the concrete signature type returned by the
/// implementation of `Signer` for `S`.
pub fn sign<S>(message: &[u8], sk: &S) -> S::Signature
where
    S: Signer,              // the key can create signatures …
{
    sk.sign(message)        // … so just delegate to it.
}