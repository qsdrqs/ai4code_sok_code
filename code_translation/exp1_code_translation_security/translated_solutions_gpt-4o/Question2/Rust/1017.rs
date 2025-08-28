use ecdsa::signature::Signer; // Import the Signer trait for signing messages
use ecdsa::Signature; // Import the Signature type

// Function in Rust that signs a given message using a given ECDSA signing key
fn sign_message<S>(message: &[u8], signing_key: &S) -> Signature
where
    S: Signer<Signature>,
{
    // Sign the message using the signing key
    let signature = signing_key.sign(message);
    // Return the signature
    signature
}